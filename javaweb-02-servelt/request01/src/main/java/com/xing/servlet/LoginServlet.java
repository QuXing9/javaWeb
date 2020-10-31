package com.xing.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");

        req.getParameter("username");
        req.getParameter("password");
        String[] hobbys = req.getParameterValues("hobbys");

        System.out.println("====================");
        System.out.println(Arrays.toString(hobbys));
        // 通过请求转发
        // 这里的/代表当前得web应用
        req.getRequestDispatcher("/success.jsp").forward(req, resp);
        resp.setCharacterEncoding("utf-8");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
