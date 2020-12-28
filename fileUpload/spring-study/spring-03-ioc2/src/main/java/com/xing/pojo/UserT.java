package com.xing.pojo;

public class UserT {
    private String name;

    public UserT(String name){
        System.out.println("userT被创建了！");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void show(){
        System.out.println("name="+name);
    }
}
