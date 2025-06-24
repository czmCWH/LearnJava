
改造案例：具体查看 `/笔记/img/04-Mybatis基于XML开发` 配置，运行 `/src/test/java/com/czm/UserMapperTest.java` 查看效果。

# 一、Mybatis 的 XML映射配置

在 Mybatis 中，既可以通过 注解配置SQL语句，即 `基于注解开发`；
也可以通过 XML配置文件配置SQL语句，即 `基于XML开发`。

```java
@Mapper
public interface UserMapper {
    // Mybatis 中使用 @Select(sql语句的方式) 称为：基于注解开发
    @Select("select * from user")
    public List<User> list();
}
```

`XML` 配置文件配置 `SQL` 语句的规则：
1. `XML映射文件` 的名称与 `Mapper接口` 名称一致，并且将 `XML映射文件` 和 `Mapper接口` 放置在相同包下(同包同名)。
2. `XML映射文件` 的 `namespace` 属性为 `Mapper接口 全限定名(包名+接口名)` 一致。
3. `XML映射文件` 中 `sql`语句的 `id` 与 `Mapper接口中的方法名` 一致，并保持返回类型一致。

注意，如果 `XML映射文件` 和 `Mapper接口` 不在同一包下，可以通过在 Mybatis 配置文件 `src/main/resources/application.properties` 中，添加如下配置，
来指定 XML文件的 路径。
```
# 配置XML与Mapper接口的映射路径
mybatis.mapper-locations=classpath:mapper/*.xml
```

### MybatisX 插件

`MybatisX` 是一款基于 IDEA 的快速开发 `Mybatis` 的插件，为效率而生。
安装方式：选中 IDEA 图标 > Settings > Plugins > 搜索 `MybatisX`，安装即可。

## Mybatis 开发使用 注解 还是 XML ？

> 使用 Mvbatis 的注解，主要是来完成一些简单的增删改查功能。
> 如果需要实现复杂的SQL功能，建议使用 XML 来配置映射语句。

官方说明: <https://mybatis.net.cn/getting-started.html>