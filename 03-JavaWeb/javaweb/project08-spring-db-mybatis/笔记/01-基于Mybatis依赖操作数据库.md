# MyBatis 框架访问数据库

`MyBatis` 是一款优秀的 持久层(数据访问层 `dao层`) 框架，用于简化 `JDBC` 的开发。
`MyBatis` 本是 `Apache` 的一个开源项目 `iBatis`，2010年这个项目由 `apache` 迁移到了 `google code`，并且改名为 `MyBatis`。2013年11月迁移到 `Github`。
官网：<https://mybatis.org/mybatis-3/>

> Java 三层架构中的 数据访问层采用 `MyBatis` 依赖的 `@Mapper` 注解，因此数据访问层由 `Dao层` 换为 `Mapper层` 表示。

# 一、MyBatis 的基本使用步骤

案例：创建一个 Spring Boot 模块，基于 Mybatis 依赖操作数据库，使用 Mybatis 注解来操作数据库。
具体实现 `src/main/java/com/czm/mapper/UserMapper.java` 中查看。
通过单元测试来实现 `src/test/java/com/czm/UserMapperTest.java` 访问数据库。

## 步骤1，准备工作：

* 创建 SpringBoot 工程、引入 `Mybatis、MySQL Driver` 相关依赖；
* 准备数据库表 user、实体类 User；
* 配置 Mybatis(在 `模块/src/main/resources/application.properties` 中数据库连接信息)，配置数据库连接信息如下所示：

```
spring.datasource.url=jdbc:mysql://localhost:3306/czm_db01
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=123
```

## 步骤2，编写 Mybatis 程序：编写 Mybatis 的持久层接口，定义SQL(注解/XML)

> `Mybatis` 的持久层接口命名规范为 `XxxMapper`，也称为 **Mapper 接口**。
> `@Mapper` 是 Mybatis 中的注解，会自动创建该接口的代理对象，并放入I0C容器，成为bean对象

## 步骤3，实现SpringBoot 单元测试
@SpringBootTest  注解作用：在单元测试运行时加载 Spring 环境，拿到 IOC容器。

> ⚠️注意：测试类所在的包必须和启动类所在的包名一致，如果不一致运行会报错！！！，但也可以指定来启动完整类名来解决。

# 二、MyBatis 的其他配置

配置1、配置 `MyBatis` 环境下sql语句提示，详细步骤查看 `/笔记/img/01-`；

配置2、配置 `MyBatis` 环境下 IDEA 连接的数据库，详细步骤查看 `/笔记/img/02-`；

配置3、使用 `Mybatis`后，Sql 语句执行时，终端是不会显示 sql 执行日志的。需要在 `模块/src/main/resources/application.properties` 添加日志查看：
`mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl`