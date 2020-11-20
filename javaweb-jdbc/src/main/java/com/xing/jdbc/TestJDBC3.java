package com.xing.jdbc;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TestJDBC3 {

    @Test
    public void test() throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String username = "root";
        String password = "123456";

        Connection connection = null;

        try {
            // 1. 加载驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2. 连接数据库，代表数据库
            connection = DriverManager.getConnection(url, username, password);

            //3. 通知数据库开启事务, false， 开启
            connection.setAutoCommit(false);

            String sql = "update account set money = money - 100 where name = 'A'";
            connection.prepareStatement(sql).executeUpdate();

            // 制造错误
            int i = 1 / 0;

            String sql2 = "update account set money = money + 100 where name = 'B'";
            connection.prepareStatement(sql2).executeUpdate();

            // 以上两条sql都执行成功了，就提交事务！
            connection.commit();
            System.out.println("success");
        }catch (Exception e){
            try {
                connection.rollback();
            }catch (SQLException e1){
                e1.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            connection.close();
        }
    }
}
