package com.qiaoshuo;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JdbcTest {
    @Test
    public void testUpdate() throws ClassNotFoundException, SQLException {
    //注册驱动
        Class.forName("com.mysql.cj.jdbc.Driver");
    //获取数据库连接
        String url = "jdbc:mysql://localhost:3306/qiaoblog";
        String username = "root";
        String password = "qsq217324";
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        int i = statement.executeUpdate("update users set age = 25 where id = 1");
        System.out.println(i);
        statement.close();
        connection.close();

    }
}
