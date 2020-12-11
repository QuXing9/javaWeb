package com.xing.dao;

import com.xing.pojo.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface UserMapper {

    List<User> getUserList();

    User getUserById(@Param("pid") int id);

    //插入一个用户
    int addUser(User user);

     //修改用户
    int updateUser(User user);

    //删除用户
    int deleteUser(int id);
}
