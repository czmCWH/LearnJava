
改造案例：具体查看 `/笔记/img/04-Mybatis基于XML开发` 配置，运行 `/src/test/java/com/czm/UserMapperTest.java` 查看效果。

# 一、Mybatis 基于XML开发
在 Mybatis 中，既可以通过 Mybatis 注解定义SQL语句，即 `基于注解开发`。也可以通过 XML配置文件配置SQL语句，即 `基于XML开发`。

- Mybatis 开发使用 注解 还是 XML ？
    使用 Mybatis 的注解，主要是来完成一些简单的 增删改查功能。如果需要实现复杂的 SQL功能，建议使用 XML来配置映射语句。
    官方说明: <https://mybatis.net.cn/getting-started.html>

- XML 映射配置默认规则：
  1. XML映射文件 的名称与 Mapper接口 名称一致，并且将 XML映射文件 和 Mapper接口 放置在相同包下（即，同包同名）。
  2. XML映射文件 的 namespace属性 为 Mapper接口全限定名（即，包名+接口名） 一致。 
  3. XML映射文件 中 sql语句的 id 与 Mapper接口中的方法名 一致，并保持返回类型一致。

定义XML映射文件操作步骤见图： `/img/04-Mybatis基于XML开发`，文件基本内容如下：
```xml
<!-- XML 文件头需要从 Mybatis 官网中获取：https://mybatis.org/mybatis-3/zh_CN/getting-started.html  -->
<!DOCTYPE mapper
        PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "https://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!--书写Sql语句-->
<mapper namespace="com.czm.mapper.UserMapper">
    <!-- 查询全部数据 -->
    <!-- resultType：查询返回的单条记录所封装的类型 -->
    <select id="findList" resultType="com.czm.pojo.User">
        select * from user
    </select>
</mapper>
```

## XML 映射文件 - 辅助配置
如果 XML映射文件 和 Mapper接口 不在同一包下，执行程序会报错。通过修改项目的 `application.properties` 文件 Mybatis 配置来解决，如下所示：
```
# 配置XML与Mapper接口的映射路径，即去 main/resources/mapper/ 目录下查找 xml 文件
mybatis.mapper-locations=classpath:mapper/*.xml

# 说明：classpath：是指类目录，即 classes 目录。java程序编译后 `src/main` 下面的 java、resources 目录都会放在 classes 目录下。 
```

## MybatisX 插件
`MybatisX` 是一款基于 `IDEA` 的快速开发 `Mybatis` 的插件，为效率而生。
安装方式：选中 IDEA 图标 > Settings > Plugins > 搜索 `MybatisX`，安装即可。

