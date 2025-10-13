package com.czm;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 1、Java 中操作数据库的基本使用
 */

public class JDBCTest {

    @Test
    public void testUpdate() throws Exception {
        // 1、注册驱动 --- 固定步骤
        // 以反射的方式，根据 全类名 加载指定的类。
        // 可以省略不写，因为 JDK 的 SPI 机制会扫描在驱动包的 META-INF 目录下的配置文件中配置了驱动类的全类名进行自动加载。
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2、获取数据库连接对象
        /**
         * url：数据库连接 url
         * 语法：jdbc:msql://ip地址(或域名):端口号/数据库名称?参数键值对1&参数键值对2
         * 如果连接的是本机的默认端口的mysql，url可以简写为:
         *      jdbc:mysql:///数据库名?参数键值对...
         * user：登录数据库的用户名
         * password：密码
         */
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/czm_db01", "root", "123");
//        Connection connection = DriverManager.getConnection("jdbc:mysql:///czm_db01", "root", "123"); // 简写不推荐

        // 3、获取SQL语句的执行对象
        Statement statement = connection.createStatement();

        // 4、执行SQL语句
        // 修改User表中，ID为1的数据
        int i = statement.executeUpdate("update user set name = '王八1' where id = 1");
        System.out.println("--- 影响（修改）了" + i + "行");

        // 5、释放资源
        statement.close();
        connection.close();

    }
}
