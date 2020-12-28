package dao;

import service.UserService;

public class UserDaoMysqlImpl implements UserDao {
    public void getUser() {
        System.out.println("mysql获取用户数据");
    }
}
