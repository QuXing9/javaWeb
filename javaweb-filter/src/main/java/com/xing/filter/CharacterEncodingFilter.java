package com.xing.filter;

import javax.servlet.*;
import java.io.IOException;

public class CharacterEncodingFilter implements Filter {
    // 初始化:web服务器启动，就已经初始化了，随时等待监听
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("characterEncodingFilter初始化");
    }

    //chain：链
    /*
    1. 过滤中的所有代码，在过滤特定请求的时候都会执行
    2. 必须让过滤器继续同行
     */
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("utf-8");
        servletResponse.setCharacterEncoding("utf-8");
        servletResponse.setContentType("text/html;charset=UTF-8");
        System.out.println("characterEncodingFilter执行前……");
        // 让我们的请求继续走，如果不写，程序到这里就停止了
        filterChain.doFilter(servletRequest,servletResponse);
        System.out.println("characterEncodingFilter执行后");
    }

    public void destroy() {
        System.out.println("CharacterEncodingFilter销毁");
    }
}
