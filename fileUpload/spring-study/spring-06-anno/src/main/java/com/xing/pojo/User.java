package com.xing.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

// 等价于<bean>注册
// <bean id="user" class="com.xing.pojo.User">
// @Component 组件
@Component
public class User {

    public String name;

    // 等价于
    // <property name="name" value="xing" />
    @Value("quxing")
    public void setName(String name) {
        this.name = name;
    }
}
