> 通过 Java 程序操作数据库的技术有很多，最底层的是 JDBC。

# 一、JDBC
`JDBC`，(`Java DataBase Connectivity`)，就是使用 `Java` 语言操作关系型数据库的一套 `API`。它是 `Java EE` 的13项规范之一。
`JDBC` 偏底层，操作很繁琐，基本上不用。开发中通常使用基于 `JDBC` 封装的高级框架，简化代码开发。

* `Java` 中操作数据库框架：`MyBatis`(最常用)、`MyBatisPlus`(教新)、`Hibernate`(早期)、`SpringData JPA`(早期)

## JDBC 的本质
为什么 `JDBC` 是一套 API，而不是实现类可以直接使用？
因为 关系型数据库 有很多产品，都有各自的特点。为了方便开发者操作，由数据库软件厂商根据 `JDBC` 来提供具体实现。

`JDBC` 的本质是 `sun` 公司官方定义的一套操作所有关系型数据库的规范，即接口。各个数据库厂商去实现这套接口，即提供数据库`驱动jar包`。
开发者使用这套接口 (`JDBC`)编程，而真正执行的代码是 `驱动jar包` 中的实现类。


# 二、在 Java 程序中操作 MySql
案例：在 Java 程序中 以 junit 单元测试的方式执行 update 语句 更新数据库中 用户表的数据。
代码实现：`src/test/.../JDBCTest.java` 和 `笔记/sql/user.sql`

## 实现步骤
* 步骤1、项目准备
创建普通的 `Maven` 项目，引入 `mysql的驱动`，以及 `junit依赖`；

```xml
<!-- mysql驱动 -->
<dependency>
    <groupId>mysql</groupId>
    <artifactId>mysql-connector-java</artifactId>
    <version>8.0.33</version>
</dependency>
<!-- junit单元测试 -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.11.0</version>
    <scope>test</scope>
</dependency>
<!-- lombok 依赖 -->
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.38</version>
</dependency>
```

* 步骤2、代码实现：编写 JDBC 程序，操作数据库。
  注册驱动；
  获取数据库连接对象 `Connection`；
  获取 `SQL` 语句执行对象 `Statement`；
  执行 `SQL` 语句；
  释放资源；


> Java 终端报错
> 1、查看顺序从下往上看，即直接拉到报错信息最后；
> 2、查看 Exception 后面的异常信息；


# 三、JDBC API 详解
## 1、`DriverManager` 驱动管理器 的作用：
* 注册驱动
* 获取数据库连接

```java
package com.mysql.cj.jdbc;
public class Driver {
    static {
        try {
            DriverManager.registerDriver(new Driver());
        } catch (SQLException var1) {
            throw new RuntimeException("Can't register driver!");
        }
    }
}
```
当加载 `Driver` 驱动类时，会自动运行静态初始化块中注册驱动的代码，因此 `Class.forName` 这步操作可以省略。可以省略是因为 JDK 自带的 SPI 机制。
SPI机制：`Service Provider Interface`，JDK 内置的一种服务提供发现机制，可以轻松的扩展你的程序(切换实现)，实现接口与实现类之间的解耦。

## 2、`Connection` 数据库连接对象

1、`Connection` 的作用：获取执行 SQL 的对象，有如下2种方式：
    
`Statement` 执行静态SQL对象：`connection.createStatement();`。
`PreparedStatement` 执行预编译SQL对象：`connection.prepareStatement()`，真实开发中使用。

  PreparedStatement 的优势：
    a、可以防止SQL注入问题，更安全；
    b、执行性能更高；

  SQL注入：通过控制输入来修改事先定义好的SQL语句，以达到执行代码对服务器进行攻击的方法。 
  ⚠️ 详细见：`src/test/.../JDBCLoginTest.java` 和 `笔记/img/02-预编译sql的优势.jpg`

2、`Statement` 执行 SQL 的 API： 
执行 `DDL、DML` 语句：`statement.executeUpdate(sql)`，如果是执行 `DML` 语句完毕，会返回int值，代表 `DML` 语句影响的行数。
执行 `DQL` 语句：`statement.executeQuery(sql)`，返回值为 `ResultSet`，里面封装了查询结果。