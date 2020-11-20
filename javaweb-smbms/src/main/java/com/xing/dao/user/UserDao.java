package com.xing.dao.user;

import com.xing.pojo.User;

import java.sql.Connection;
import java.sql.SQLException;

public interface UserDao {
    //得到要登陆的用户
    public User getLoginUser(Connection connection, String userCode) throws SQLException;

    // 修改当前用户密码
    public int updatePwd(Connection connection, int id, String password) throws SQLException;

    // 查询用户总数
    public int getUserCount(Connection connection, String username, int userRole) throws SQLException;
}
