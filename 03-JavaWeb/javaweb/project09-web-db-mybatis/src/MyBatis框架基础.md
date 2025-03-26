# MyBatis 框架访问数据库

MyBatis 是一款优秀的 持久层(数据访问层) 框架，用于简化 `JDBC` 的开发。
MyBatis 本是 Apache 的一个开源项目 iBatis，2010年这个项目由 apache 迁移到了 google code，并且改名为 MyBatis。2013年11月迁移到 Github。
官网：<https://mybatis.org/mybatis-3/>

## 一、使用 MyBatis 基本步骤

步骤1，准备工作：

* 创建 SpringBoot 工程、引入 `Mybatis、MySQL Driver` 相关依赖；

* 准备数据库表 user、实体类 User；

* 配置 Mybatis(在 `模块/src/main/resources/application.properties` 中数据库连接信息)，配置数据库连接信息如下所示：

```
spring.datasource.url=jdbc:mysql://localhost:3306/czm_db01
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=123
```

步骤2，编写 Mybatis 程序：编写 Mybatis 的持久层接口，定义SQL(注解/XML)

> `Mybatis` 的持久层接口命名规范为 `XxxMapper`，也称为 **Mapper 接口**。
>
> `@Mapper` 注解，会自动创建该接口的代理对象，并放入I0C容器，成为bean对象


## 二、数据库连接池

### 什么是数据库连接池

* 数据库连接池是个容器、负责分配、管理数据库连接(Connection)。

* 它允许应用程序重复使用一个现有的数据库连接，而不是再重新建立一个。

* 释放空闲时间超过最大空闲时间的连接，来避免因为没有释放连接而引起的数据库连接遗漏

* 优势：资源重用；提升系统响应速度；避免数据库连接遗漏

### 数据库连接池的接口产品

* 数据库连接池的标准接口：`DataSource`
  官方(sun)提供的数据库连接池接口，由第三方组织实现此接口；
  功能：获取连接，`Connection getConnection()throws SQLException;`

* 常见产品：C3P0、DBCP、Druid、Hikari(Spring boot 默认)

Druid(德鲁伊)
Druid连接池是阿里巴巴开源的数据库连接池项目。功能强大，性能优秀，是Java语言最好的数据库连接池之一。

### 切换数据库连接池

步骤1、导入依赖 pom.xml
```xml
<!-- Druid 数据库连接池 -->
<dependency>
	<groupId>com.alibaba</groupId>
	<artifactId>druid-spring-boot-starter</artifactId>
	<version>1.2.20</version>
</dependency>
```
步骤2、application.properties 中配置数据库的连接信息

```
# 声明数据库连接池为 Druid(德鲁伊)，否则默认为 SpringBoot 的Hikari
spring.datasource.type=com.alibaba.druid.pool.DruidDataSource
spring.datasource.url=jdbc:mysql://localhost:3306/czm_db01
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=123
```

## 三、Mybatis 的 XML映射配置

在 Mybatis 中，既可以通过 注解配置SQL语句，也可以通过 XML配置文件配置SQL语句。

XML配置文件配置SQL语句的规则：
1. XML映射文件 的名称与 Mapper 接口名称一致，并且将 XML映射文件 和 Mapper 接口放置在相同包下(同包同名)。
2. XML映射文件 的 namespace 属性为 Mapper接口 全限定名一致。
3. XML映射文件 中 sql语句的 id 与 Mapper 接口中的方法名一致，并保持返回类型一致。

### XML映射文件

`MybatisX` 是一款基于 IDEA 的快速开发 `Mybatis` 的插件，为效率而生。

安装方式：选中 IDEA 图标 > Settings > Plugins > 搜索 `MybatisX`，安装即可。

### Mybatis 开发使用 注解 还是 XML ？

使用 Mvbatis 的注解，主要是来完成一些简单的增删改查功能。
如果需要实现复杂的SQL功能，建议使用 XML 来配置映射语句。

官方说明: <https://mybatis.net.cn/getting-started.html>

## 四、Mybatis 数据封装

当 实体类属性名 和 数据库表查询返回的字段名一致时，mybatis 会自动封装。

如果 实体类属性名 和 数据库表查询返回的字段名不一致时，不能自动封装。

### 手动结果映射

方式1、手动结果映射：通过 @Results 及 @Result 进行手动结果映射。
```java
@Mapper
public interface DeptMapper {
    //查询部门列表
    @Results({
//            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("select * from dept")
    public List<Dept> list();
}
```

方式2、起别名：在 SQL 语句中，对不一样的列名起别名，别名和实体类属性名一样。

```java
@Mapper
public interface DeptMapper {
    //查询部门列表
    @Select("select id, name, update_time updateTime from dept")
    public List<Dept> list();
}
```

方式3、开启驼峰命名规则映射：如果字段名 与 属性名符合驼峰命名规则，mybatis 会自动通过驼峰命名规则映射。--- 在项目中进行全局配置！

在 `/src/main/resources/application.properties` 文件中添加如下配置：
```
# 开启 Mybatis 数据封装时，采用驼峰配，如：create_time > createTime
mybatis.configuration.map-underscore-to-camel-case=true
```
```java
@Mapper
public interface DeptMapper {
    @Select("select * from dept")
    public List<Dept> list();
}
```



