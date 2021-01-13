package com.xing.controller;

import com.xing.pojo.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class AjaxController {

    @RequestMapping("/t1")
    public String test(){
        return "hello";
    }

    @RequestMapping("/a1")
    public void a1(String name, HttpServletResponse reponse) throws IOException {
        System.out.println("a1:param=>"+name);
        if ("xingxing".equals(name)){
            reponse.getWriter().print("true");
        }else{
            reponse.getWriter().print("false");
        }
    }

    @RequestMapping("/a2")
    public List<User> a2(){
        List<User> userList = new ArrayList<User>();

        //添加数据
        userList.add(new User("xingxing说Fuzzing", 1, "男"));
        userList.add(new User("xingxing说你好", 1, "女"));

        return userList;
    }

    @RequestMapping("/a3")
    public String a3(String name, String pwd){
        String msg = "";
        if (name != null){
            //admin，这些数据应该在数据库中查
            if("admin".equals(name)){
                msg = "ok";
            }else {
                msg = "用户名有误";
            }
        }
        if (pwd != null){
            //admin，这些数据应该在数据库中查
            if("123456".equals(pwd)){
                msg = "ok";
            }else {
                msg = "密码有误";
            }
        }
        return msg;
    }


}
