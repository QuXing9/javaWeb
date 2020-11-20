package com.xing.servlet.user;

import com.xing.pojo.User;
import com.xing.service.user.UserService;
import com.xing.service.user.UserServiceImpl;
import com.xing.util.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    //Servlet：控制层：调用业务层代码

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("LoginServlet--start……");
        //获取用户名和密码
        String userCode = req.getParameter("userCode");
        String password = req.getParameter("userPassword");

        // 和数据库中的代码进行对比，调用业务层
        UserServiceImpl userService = new UserServiceImpl();
        User user = userService.login(userCode, password);
        if (user != null){ //查有此人，可以登录
            //将用户信息放到session
            req.getSession().setAttribute(Constants.USER_SESSION, user);
            // 跳转到内部主页
            resp.sendRedirect("jsp/frame.jsp");
        }else { // 查无此人，无法登录
            //转发回登陆页面，顺便提示它，用户名或者密码错误
            req.setAttribute("error", "用户名或者密码不正确");
            req.getRequestDispatcher("login.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
