package com.xing.dao;

import com.xing.pojo.User;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    List<User> getUserLike(String value);

    List<User> getUserList();

    User getUserById(int id);

    //插入一个用户
    int addUser(User user);

    int addUser2(Map<String, Object> map);

     //修改用户
    int updateUser(User user);

    //删除用户
    int deleteUser(int id);
}
