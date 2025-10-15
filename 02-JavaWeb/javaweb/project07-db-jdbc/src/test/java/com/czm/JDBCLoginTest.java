package com.czm;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.sql.*;

/**
 * 2、JDBC案例，查询数据库，根据用户名和密码进行登录
 * PreparedStatement 可以 防止 Sql 注入工具。
 */

public class JDBCLoginTest {
    /**
     * 案例：用户账号密码登录
     * 基于 JDBC 执行 DQL 语句
     */
    @Test
    public void testLogin() throws Exception {
        // 1、准备工作

        // 1.1、注册 JDBC 驱动 --- 固定步骤
        Class.forName("com.mysql.cj.jdbc.Driver");  // 可以省略不写

        // 1.2、获取数据库连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/czm_db01", "root", "123");

        // 1.3、获取SQL语句的执行对象
        Statement statement = connection.createStatement();

        // 2、执行SQL语句
        ResultSet resultSet = statement.executeQuery("select * from user where username='lisi' and password='123456'");

        /*
         * ResultSet 查询结果集对象，封装了DQL语句执行的结果。
         *  next()，将光标从当前位置向前移动一行，并判断当前行是否为有效行，返回值为 boolean.
         *      true：有效行，即：当前行有数据。
         *      false：无效行，即：当前行没有数据。
         *
         *  getXxx()：获取行的数据，可以根据列编号(列编号从1开始)获取，也可以根据列名获取（推荐）。
         */

        // 3、解析查询结果集
        while (resultSet.next()) {  // 判断是否有值

            // int id = resultSet.getInt(1);    // 通过列编号查询，下标从1开始。不推荐
            int id = resultSet.getInt("id");

            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");

            User user = new User(id, username, password, name);
            System.out.println("User : " + user);
        }

        // 3、释放资源
        resultSet.close();
        statement.close();
        connection.close();
    }


    /**
     * ParameterizedTest + @CsvSource 实现参数化测试
     */
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
        // 解析查询结果集
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
        resultSet.close();
        statement.close();
        connection.close();
    }


    /**
     * Statement 用于执行 静态SQL，会导致 sql 注入的问题
     *      静态SQL是指 直接将sql语句中使用参数值拼接到 sql 语句中，即参数硬编码。
     * SQL注入：通过控制输入来修改事先定义好的SQL语句，以达到执行代码对服务器进行攻击的方法。

     * PreparedStatement 用于执行预 编译sql，解决了sql注入问题，性能更高
     */
    @ParameterizedTest
    @CsvSource(value = {"lisi, 123456", "zhangsan, ' or '1'='1", "zhangsan, 123456"})    // 多参数
    public void testLogin3(String uname, String pwd) throws Exception {
        // 1、准备工作
        // 1.1、注册驱动 --- 固定步骤
        Class.forName("com.mysql.cj.jdbc.Driver");  // 可以省略不写，具体看源码

        // 1.2、获取数据库连接对象
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/czm_db01", "root", "123");

        // 1.3、获取 SQL语句的执行对象 Statement
//        Statement statement = connection.createStatement();
        /**
         * 2、执行SQL语句
         * ⚠️ Statement 会存在 SQL注入的问题
         *  当参数为：xxx, ' or '1'='1 时，会出现任意用户都可以登录的情况！
         *  把参数拼接到 sql 语句中后：select * from user where username='xxx' and password='' or '1'='1';
         *   or '1'='1' 恒成立，所以任意用户名、使用密码为 ' or '1'='1 时，都可以登录。
         */
//        ResultSet resultSet = statement.executeQuery("select * from user where username='" + uname + "' and password='" + pwd + "'");

        // 1.3、获取SQL语句的执行对象 PreparedStatement
        // PreparedStatement 的 SQL 语句中的参数值用 ？占位符替代
        PreparedStatement ps = connection.prepareStatement("select * from user where username = ? and password = ?");   // 预编译SQL语句
        // 设置 ? 占位符的参数值，是把参数作为一个整体进行替换占位符
        ps.setString(1, uname);     // 设置第1个?占位符的值
        ps.setString(2, pwd);       // 设置第2个?占位符的值

        // PreparedStatement 执行 预编译SQL语句
        ResultSet resultSet = ps.executeQuery();

        // ⚠️ 通过 PreparedStatement 执行预编译SQL语句，可以防止SQL注入问题，更安全；
        // 另外，预编译SQL 的执行性能更高！

        // 解析查询结果集
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String username = resultSet.getString("username");
            String password = resultSet.getString("password");
            String name = resultSet.getString("name");
            User user = new User(id, username, password, name);
            System.out.println(user);
        }
        // 3、释放资源
        resultSet.close();
//        statement.close();
        ps.close();
        connection.close();
    }
}
