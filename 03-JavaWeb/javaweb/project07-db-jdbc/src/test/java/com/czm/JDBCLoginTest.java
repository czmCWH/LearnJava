package com.czm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.*;

/**
 * 2、JDBC案例，查询数据库，根据用户名和密码进行登录
 */

public class JDBCLoginTest {
    @Test
    public void testLogin() throws Exception {
        // 1、准备工作

        // 1.1、注册驱动 --- 固定步骤
        Class.forName("com.mysql.cj.jdbc.Driver");  // 可以省略不写，具体看源码

        // 1.2、获取数据库连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql:///czm_db01", "root", "123");

        // 1.3、获取SQL语句的执行对象
        Statement statement = connection.createStatement();

        // 2、执行SQL语句
        ResultSet resultSet = statement.executeQuery("select * from user where username='lisi' and password='123456'");

        /*
         * ResultSet 查询结果集对象，它封装了DQL查询语句查询的结果。
         *  next()，将光标从当前位置向前移动一行，并判断当前行是否为有效行，返回值为 boolean.
         *      true：有效行，即：当前行有数据。
         *      false：无效行，即：当前行没有数据。
         *
         *  getXxx()：获取行的数据，可以根据列编号(列编号从1开始)获取，也可以根据列名获取。
         */

        // 判断是否有值
        while (resultSet.next()) {

            // int id = resultSet.getInt(1);    // 下标从1开始
            int id = resultSet.getInt("id");

            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");

            User user = new User(id, username, password, name);
            System.out.println(user);

        }


        // 3、释放资源
        statement.close();
        connection.close();

    }

    // 参数化测试
    @ParameterizedTest
    @CsvSource(value = {"lisi, 123456", "zhangsan, 123456"})    // 多参数
    public void testLogin2(String uname, String pwd) throws Exception {
        // 1、准备工作

        // 1.1、注册驱动 --- 固定步骤
        Class.forName("com.mysql.cj.jdbc.Driver");  // 可以省略不写，具体看源码

        // 1.2、获取数据库连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql:///czm_db01", "root", "123");

        // 1.3、获取SQL语句的执行对象
        Statement statement = connection.createStatement();

        // 2、执行SQL语句
        ResultSet resultSet = statement.executeQuery("select * from user where username='" + uname + "' and password='" + pwd + "'");
        // 判断是否有值
        while (resultSet.next()) {

            // int id = resultSet.getInt(1);    // 下标从1开始
            int id = resultSet.getInt("id");

            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");

            User user = new User(id, username, password, name);
            System.out.println(user);

        }
        // 3、释放资源
        statement.close();
        connection.close();
    }


    // PreparedStatement 预编译sql，解决sql注入问题
    @ParameterizedTest
    @CsvSource(value = {"lisi, 123456", "zhangsan, ' or '1'='1", "zhangsan, 123456"})    // 多参数
    public void testLogin3(String uname, String pwd) throws Exception {
        // 1、准备工作

        // 1.1、注册驱动 --- 固定步骤
        Class.forName("com.mysql.cj.jdbc.Driver");  // 可以省略不写，具体看源码

        // 1.2、获取数据库连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql:///czm_db01", "root", "123");

        // 1.3、获取SQL语句的执行对象
//        Statement statement = connection.createStatement();
//        // 2、执行SQL语句
//        ResultSet resultSet = statement.executeQuery("select * from user where username='" + uname + "' and password='" + pwd + "'");

        // ⚠️⚠️⚠️ Statement 会存在 SQL注入的问题
        // 由于执行的sql语句是字符串拼接的，当参数为：xxx, ' or '1'='1 时，会出现任意用户都可以登录的情况？
        // 替换后的结果：select * from user where username='xxx' and password='' or '1'='1'
        // 这样任意用户都可以登录。
        // 预编译SQL语句并执行，可以防止SQL注入问题；

        // 1.3、获取SQL语句的执行对象，？代表占位符
        PreparedStatement ps = connection.prepareStatement("select * from user where username = ? and password = ?");
        // 设置 ? 占位符的参数值
        ps.setString(1, uname);
        ps.setString(2, pwd);

        ResultSet resultSet = ps.executeQuery();


        // 判断是否有值
        while (resultSet.next()) {

            // int id = resultSet.getInt(1);    // 下标从1开始
            int id = resultSet.getInt("id");

            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");

            User user = new User(id, username, password, name);
            System.out.println(user);

        }
        // 3、释放资源
//        statement.close();
        ps.close();
        connection.close();

    }
}
