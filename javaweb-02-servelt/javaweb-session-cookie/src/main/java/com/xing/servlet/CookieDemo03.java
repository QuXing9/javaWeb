package com.xing.servlet;


import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

// 中文数据传递
public class CookieDemo03 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        // Cookie，服务器端从客户端获取
        // 这里返回数组，说明cookie可能存在多个
        Cookie[] cookies = req.getCookies();

        PrintWriter out = resp.getWriter();
        // 判断cookie是否存在
        if(cookies != null){
            //如果存在怎么办
            out.write("你上一次访问的时间是:");
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                //获取cookie的名字
                if (cookie.getName().equals("name")){
                    //获取cookie中的值
                    out.write(URLDecoder.decode(cookie.getValue(), "UTF-8"));
                }
            }
        }else {
            out.write("这是您第一次访问");
        }

        Cookie cookie = new Cookie("name", URLEncoder.encode("瞿兴", "UTF-8"));
        resp.addCookie(cookie);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
