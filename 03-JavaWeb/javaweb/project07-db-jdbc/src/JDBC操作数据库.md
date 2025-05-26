# JDBC

JDBC，(Java DataBase Connectivity)，就是使用Java语言操作关系型数据库的一套API。它是Java EE 的13项规范之一。
JDBC 偏底层，基本上不用。

* Java 中操作数据库框架：MyBatis、MyBatisPlus、Hibernate、SpringData JPA

> JDBC 的本质:
>
> sun公司官方定义的一套操作所有关系型数据库的规范，即接口。
> 各个数据库厂商去实现这套接口，提供数据库**驱动jar包**。
> 我们可以使用这套接口 (JDBC)编程，**真正执行的代码是驱动jar包中的实现类**

## 1、使用 JDBC 的步骤

步骤1、项目准备
    创建项目，引入 mysql的驱动，以及 junit依赖；
    注册驱动；
    获取数据库连接对象 Connection；
    获取 SQL 语句执行对象 Statement；

步骤2、执行 SQL 语句

步骤3、释放资源

> Java 终端报错
> 1、查看顺序从下往上看；
> 2、查看 Exception 后面的异常信息；

## 2、JDBC API 详解

### `DriverManager` 驱动管理作用：

* 注册驱动；
* 获取数据库连接；

当加载 Driver 驱动类时，会自动运行静态代码块中注册驱动的代码，
因此 Class.forName 这步操作可以省略；

> SPI机制：Service Provider Interface，JDK 内置的一种服务提供发现机制，可以轻松的扩展你的程序(切换实现)，实现接口与实现类之间的解耦。

### `Connection` 数据库连接对象的作用

1、获取执行 SQL 的对象
`Statement` 执行普通SQL对象：`connection.createStatement();`
`PreparedStatement` 执行预编译SQL对象：`connection.prepareStatement()`

2、执行 SQL
执行 DDL、DML语句：statement.executeUpdate(sql)，如果是执行DML语句完毕，会返回int值，代表DML语句影响的行数。
执行 SQL 语句：statement.executeQuery(sql)，返回值为 ResultSet，里面封装了查询结果。

### PreparedStatement

PreparedStatement 的作用：预编译SQL语句并执行，可以防止SQL注入问题；
SQL注入：通过控制输入来修改事先定义好的SQL语句，以达到执行代码对服务器进行攻击的方法。

```
statement.executeQuery("select * from user where username='" + uname + "' and password='" + pwd + "'");
// uname 为 xxx(任意值)
// pwd 为 ' or '1'='1
// 拼接后的sql语句为
select * from user where username='xxx' and password='' or '1'='1'
```

预编译SQL的优势:
1. 安全(防止SQL注入)
2. 性能更高