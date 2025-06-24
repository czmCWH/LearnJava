# 一、Mybatis 返回数据封装

`Mybatis` 查询数据库返回的结果会封装到对应的实体类，当 `实体类属性名` 和 `数据库表查询返回的字段名` 一致时，`mybatis` 会自动封装。
如果 实体类属性名 和 数据库表查询返回的字段名不一致时，不能自动封装。这时需要对查询结果进行处理，有如下2种方式：

实现案例查看 `src/main/java/com/czm/mapper/DeptMapper.java`

## 方式1、手动结果映射：通过 @Results 及 @Result 进行手动结果映射。

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

## 方式2、起别名：在 SQL 语句中，对不一样的列名起别名，别名和实体类属性名一样。

```java
@Mapper
public interface DeptMapper {
    //查询部门列表
    @Select("select id, name, update_time updateTime from dept")
    public List<Dept> list();
}
```

## 方式3、开启驼峰命名规则映射：如果字段名 与 属性名符合驼峰命名规则，mybatis 会自动通过驼峰命名规则映射。--- 在项目中进行全局配置！

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
