package com.czm;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 * 1、Java 中操作数据库的基本使用
 */

public class JDBCTest {

    /**
     * JDBC 入门程序
     * 基于 JDBC 执行 DML语句
     */
    @Test
    public void testUpdate() throws Exception {
        // 1、注册 JDBC 驱动 --- 固定步骤
        // 注册驱动是指，将指定的驱动类（如，mysql驱动类）加载到内存。
        // 如下以反射的方式，根据 全类名 加载指定的类。
        // 可以省略不写，因为 JDK 的 SPI 机制会扫描在驱动包的 META-INF 目录下的配置文件中配置了驱动类的全类名进行自动加载。
        Class.forName("com.mysql.cj.jdbc.Driver");

        // 2、获取数据库连接对象
        /**
         * url：连接数据库的 url
         * 语法：jdbc:msql://数据库服务器的ip地址(或域名):端口号/数据库名称?参数键值对1&参数键值对2
         * 如果连接的是本机的默认端口的mysql，url可以简写为:
         *      jdbc:mysql:///数据库名?参数键值对...
         * user：登录数据库的用户名
         * password：密码
         */
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/czm_db01", "root", "123");
//        Connection connection = DriverManager.getConnection("jdbc:mysql:///czm_db01", "root", "123"); // 简写不推荐

        // 3、获取SQL语句的执行对象，用来执行sql语句
        Statement statement = connection.createStatement();

        // 4、执行SQL语句
        // 修改User表中，ID为1的数据
        int i = statement.executeUpdate("update user set name = '王八1' where id = 1");
        System.out.println("--- SQL语句执行完毕，影响(修改)的记录数为：" + i);

        // 5、释放资源
        statement.close();
        connection.close();
    }
}
