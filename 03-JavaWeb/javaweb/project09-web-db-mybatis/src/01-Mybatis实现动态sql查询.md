# 动态 SQL
随着用户的输入或外部条件的变化而变化的SQL语句，我们称为 动态SQL。
动态SQL 是通过 XML配置文件配置 来实现的。

<if>:用于判断条件是否成立。使用test属性进行条件判断，如果条件为true，则拼接SQL。

应用场景：更新时，根据值来更新部分SQL；查询时，根据给定的值来查询；

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



