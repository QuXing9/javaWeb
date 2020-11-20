package com.xing.jdbc;

import java.sql.*;

public class TestJdbc2 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbc?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
        String username = "root";
        String password = "123456";

        // 1. 加载驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
        // 2. 连接数据库，代表数据库
        Connection connection = DriverManager.getConnection(url, username, password);

        // 3. 编写SQL
        String sql = "insert into users(id, name, password, email, birthday) values (?,?,?,?,?)";

        // 4. 预编译
        PreparedStatement preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setInt(1 ,5);
        preparedStatement.setString(2, "xingxing在编程");
        preparedStatement.setString(3, "123456");
        preparedStatement.setString(4, "1059252359@qq.com");
        preparedStatement.setDate(5, new Date(new java.util.Date().getTime()));

        // 5. 执行sql
        int i = preparedStatement.executeUpdate();

        if(i>0){
            System.out.println("插入成功");
        }

        // 6. 关闭连接，释放资源 先开后关
        preparedStatement.close();
        connection.close();


    }
}
