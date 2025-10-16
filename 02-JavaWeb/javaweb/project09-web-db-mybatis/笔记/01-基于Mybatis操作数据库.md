`MyBatis` 是一款优秀的 持久层(数据访问层 `dao层`) 框架，用于简化 `JDBC` 的开发。
`MyBatis` 本是 `Apache` 的一个开源项目 `iBatis`，2010年这个项目由 `apache` 迁移到了 `google code`，并且改名为 `MyBatis`。2013年11月迁移到 `Github`。
官网：<https://mybatis.org/mybatis-3/>

# 一、MyBatis 基本使用
案例：创建一个 `Spring Boot` 模块，基于 Mybatis 依赖操作数据库，使用 Mybatis 注解来操作数据库。

具体实现 `src/.../mapper/UserMapper.java` 中查看。
通过单元测试来实现 `src/test/java/com/czm/UserMapperTest.java` 访问数据库。

## 步骤1，准备工作：
1. 创建 SpringBoot 工程、引入 `Lombok、Mybatis、Mysql Driver` 相关依赖：；
2. 准备数据库表 user、实体类 User；
3. 配置 Mybatis (即，在 `application.properties` 中配置数据库连接信息)
    ```
    spring.datasource.url=jdbc:mysql://localhost:3306/czm_db01
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
    spring.datasource.username=root
    spring.datasource.password=123
    ```

## 步骤2，编写 Mybatis 程序：编写 Mybatis 的持久层接口，定义SQL(注解/XML)
1. JavaWeb 三层架构中的 数据访问层 采用 `MyBatis` 依赖的 `@Mapper` 注解，因此数据访问层由 `Dao层` 替换为 `Mapper层` 表示。
2. Mybatis 的持久层接口命名规范为 XxxMapper，也称为 Mapper 接口。
3. `@Mapper` 注解是 Mybatis 提供的。
    作用：程序运行时，会自动为 @Mapper 注解修饰的接口创建一个实现类对象（即动态代理对象），并自动将该实现类对象交由 IOC 容器管理，成为 bean对象。

## 步骤3，实现 SpringBoot 单元测试
@SpringBootTest 修饰类，表示一个 SpringBoot 单元测试类，该类中单元测试方法在运行时都会加载 SpringBoot 项目环境，拿到 IOC容器。
注意：SpringBoot 单元测试类所在的包必须 和 启动类所在的包名一致（或者在其子包下），如果不一致运行会报错！！！，但也可以指定来启动完整类名来解决。


# 二、配置 MyBatis

- 配置1、配置 `MyBatis` 环境下sql语句提示，见：`/img/02-配置Mybatis的sql语句提示`

- 配置2、配置 `MyBatis` 环境下 IDEA 连接的数据库，见：`/img/03-为Mybatis配置IDEA连接的数据库`

- 配置3、使用 `Mybatis`后，Sql 语句执行时，终端是不会显示 sql 执行日志的。需要在 `application.properties` 添加日志查看：
```
mybatis.configuration.log-impl=org.apache.ibatis.logging.stdout.StdOutImpl
```

# 三、JDBC VS Mybatis
    JDBC 存在的问题                          Mybatis 结局方案
1. 数据库的配置信息 - 需要硬编码；   --->     Mybatis 在配置文件中配置，如：application.properties
2. 结果集的解析、封装 - 非常繁琐；   --->   Mybatis 会自动解析结果集，然后把数据封装到定义的实体类中
3. 频繁的获取/释放数据库连接对象 - 资源浪费，性能降低；   --->  Mybatis 使用 数据库连接池 管理 数据库连接对象
