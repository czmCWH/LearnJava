package com.czm;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 1、JDBC 的基本使用
 */

public class JDBCTest {

    @Test
    public void testUpdate() throws Exception {
        // 1、准备工作

        // 1.1、注册驱动 --- 固定步骤
        Class.forName("com.mysql.cj.jdbc.Driver");  // 可以省略不写，具体看源码

        // 1.2、获取数据库连接对象
        // jdbc:mysql://，连接数据库使用的协议
        // localhost:3306，数据库服务器的地址
        // /czm_db01，数据库名称
        // 如果连接的是本机的默认端口的mysql，url可以简写为:jdbc:mysql:///数据库名?参数键值对...
//        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/czm_db01", "root", "123");
        Connection connection = DriverManager.getConnection("jdbc:mysql:///czm_db01", "root", "123");

        // 1.3、获取SQL语句的执行对象
        Statement statement = connection.createStatement();

        // 2、执行SQL语句
        // 修改User表中，ID为1的数据
        int i = statement.executeUpdate("update user set name = '王八' where id = 1");
        System.out.println("--- 影响（修改）了" + i + "行");

        // 3、释放资源
        statement.close();
        connection.close();

    }
}
