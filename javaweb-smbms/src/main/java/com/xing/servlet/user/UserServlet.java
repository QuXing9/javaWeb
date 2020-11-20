package com.xing.servlet.user;
import com.alibaba.fastjson.JSONArray;
import com.mysql.cj.util.StringUtils;
import com.mysql.cj.xdevapi.JsonArray;
import com.xing.pojo.Role;
import com.xing.pojo.User;
import com.xing.service.role.RoleService;
import com.xing.service.role.RoleServiceImpl;
import com.xing.service.user.UserService;
import com.xing.service.user.UserServiceImpl;
import com.xing.util.Constants;
import com.xing.util.PageSupport;

import javax.imageio.IIOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//实现servlet复用
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if(method.equals("savepwd") && method != null){
            this.updatePwd(req, resp);
        }else if (method.equals("pwdmodify") && method != null){
            this.pwdModify(req, resp);
        }else if(method.equals("query") && method != null){
            this.query(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    public void updatePwd(HttpServletRequest req, HttpServletResponse resp){
        //从session里面拿ID;
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String newpassword = req.getParameter("newpassword");
        boolean flag = false;
        if(o != null && !StringUtils.isNullOrEmpty(newpassword)){
            UserService userService = new UserServiceImpl();
            flag = userService.updatePwd(((User)o).getId(), newpassword);
            if(flag){
                req.setAttribute("message", "修改密码成功，请退出，使用新密码登录！");
                // 密码修改成功，移除当前session
                req.getSession().removeAttribute(Constants.USER_SESSION);
            }else {
                req.setAttribute("message", "密码修改失败");
            }
        }else {
            req.setAttribute("message", "新密码有问题");
        }
        try {
            req.getRequestDispatcher("pwdmodify.jsp").forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //验证旧密码,session中有用户的密码
    public void pwdModify(HttpServletRequest req, HttpServletResponse resp){
        Object o = req.getSession().getAttribute(Constants.USER_SESSION);
        String oldpassword = req.getParameter("oldpassword");

        //万能的Map：结果集
        Map<String, String> resultMap = new HashMap<String, String>();

        if(o == null){ //session失效了，session过期了
            resultMap.put("result", "sessionerror");
        }else if(StringUtils.isNullOrEmpty(oldpassword)) { //输入的密码为空
            resultMap.put("result", "error");
        }else{
            String userPassword = ((User) o).getUserPassword();
            if(oldpassword.equals(userPassword)){
                resultMap.put("result", "true");
            }else {
                resultMap.put("result", "false");
            }
        }

        try {
            resp.setContentType("application/json");
            PrintWriter writer = resp.getWriter();
            //JSONArray 阿里巴巴的json工具类, 转换格式
            writer.write(JSONArray.toJSONString(resultMap));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void query(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        //查询用户列表
        //从前端获取数据
        String queryUserName = request.getParameter("queryName");
        String temp = request.getParameter("queryUserRole");
        String pageIndex = request.getParameter("pageIndex");
        int queryUserRole = 0;

        //获取用户列表
        UserServiceImpl userService = new UserServiceImpl();

        //第一次走页面一定是第一页，页面大小是固定的
        List<User> userList = null;
        //设置页面容量
        int pageSize = Constants.pageSize;
        //当前页码
        int currentPageNo = 1;
        System.out.println("queryUserName servlet--------"+queryUserName);
        System.out.println("queryUserRole servlet--------"+queryUserRole);
        System.out.println("query pageIndex--------- > " + pageIndex);
        if(queryUserName == null){
            queryUserName = "";
        }
        if(temp != null && !temp.equals("")){
            queryUserRole = Integer.parseInt(temp);
        }

        if(pageIndex != null){
            currentPageNo = Integer.valueOf(pageIndex);
        }

        //总数量
        int totalCount = userService.getUserCount(queryUserName, queryUserRole);
        //总页数
        PageSupport pages = new PageSupport();

        pages.setCurrentPageNo(currentPageNo);

        pages.setPageSize(pageSize);

        pages.setTotalCount(totalCount);

        int totalPageCount = pages.getTotalPageCount();

        //控制首页和尾页
        if(currentPageNo < 1){
            currentPageNo = 1;
        }else if(currentPageNo > totalCount){
            currentPageNo = totalPageCount;
        }

        userList = userService.getUserList(queryUserName, queryUserRole, currentPageNo, pageSize);
        System.out.println(userList);
        request.setAttribute("userList", userList);
        List<Role> roleList = null;
        RoleService roleService = new RoleServiceImpl();
        roleList = roleService.getRoleList();
        request.setAttribute("roleList", roleList);
        request.setAttribute("queryUserName", queryUserName);
        request.setAttribute("queryUserRole", queryUserRole);
        request.setAttribute("totalPageCount", totalPageCount);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("currentPageNo", currentPageNo);
        //请求转发，由下一个servlet完成响应体
        request.getRequestDispatcher("userlist.jsp").forward(request, response);
    }
}
