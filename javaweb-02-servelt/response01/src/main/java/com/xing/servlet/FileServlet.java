package com.xing.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;

public class FileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        □ 要获取下载文件的路径
        String realPath = "E:\\GithubProjects\\javaWeb\\javaweb-02-servelt\\response01\\target\\response01\\WEB-INF\\classes\\1.png";
        System.out.println("下载文件的路径:" + realPath);
//        □ 下载名是啥
        String fileName = realPath.substring(realPath.lastIndexOf("\\") + 1);
//        □ 设置想办法让浏览器能够支持(Content-disposition)下载我们需要的东西
        resp.setHeader("Content-disposition", "attachment;filename="+ URLEncoder.encode(fileName, "UTF-8"));
//        □ 获取下载文件的输入流
        FileInputStream in = new FileInputStream(realPath);
//        □ 创建缓冲区
        int len = 0;
        byte[] buffer = new byte[1024];
//        □ 获取outputstream对象
        ServletOutputStream out = resp.getOutputStream();
//        □ 将FileOutputStream流写入到buffer缓冲区 □ 使用outputstream将缓冲区中的数据输出到客户端
        while((len = in.read(buffer)) > 0){
            out.write(buffer,0,len);
        }
        in.close();
        out.close();


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
