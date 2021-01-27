package com.xing.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*放行：什么情况下登陆*/
        HttpSession session = request.getSession();

        //登陆页面也会放行
        if(request.getRequestURI().contains("goLogin")){
            return true;
        }

        if(request.getRequestURI().contains("login")){
            return true;
        }

        if(session.getAttribute("userLoginInfo") != null){
            return true;
        }

        /*判断什么情况下没有登陆*/
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
        return false;
    }
}
