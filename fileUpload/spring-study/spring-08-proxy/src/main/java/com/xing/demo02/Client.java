package com.xing.demo02;

public class Client {
    public static void main(String[] args) {
        UserServiceImpl service = new UserServiceImpl();
        UserServiceProxy proxy = new UserServiceProxy();

        proxy.setUserService(service);
        proxy.add();
    }
}
