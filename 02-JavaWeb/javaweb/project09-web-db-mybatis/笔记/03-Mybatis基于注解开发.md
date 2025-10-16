# 一、SpringBoot + Mybatis 三层架构职责
`浏览器 -> Controller -> Service -> Mapper -> DB Server`

1、`Controller` 层
* 接收请求参数；
* 调用 Service 层的方法；
* 响应结果

2、`Service` 层
* 补全基础属性； 
* 调用 `Mapper` 接口方法

3、`Mapper` 层
* 执行 `sql` 语句； 
* Mybatis 常用注解 Mapper 层：
  @Insert("insert into ...") 增
  @Delete("delete from ...") 删
  @Update("update dept set ...") 改
  @Select("select * from ...") 查
  @Results({@Result(...), ... }) 封装返回数据映射

# 二、MyBatis 基于注解开发
使用 MyBatis 在 Mapper 层定义的接口方法时，可以基于 Mybatis 的注解（`@Select/@Insert/@Update/@Delete`）定义SQL语句，即在注解中来指定要执行的 SQL语句。
可以在 sql 语句中使用 参数占位符(`#{} 或 ${}`) 来引入接口方法中的形参，sql 语句执行的结果会封装到接口方法的返回值中。

## 2.1、MyBatis 参数占位符 #{}、${}

- `#{占位符名}`，预编译占位符  --- 常用
作用：执行 sql 语句时会把 #{} 替换为 ?，生成`预编译Sql语句`。
应用场景：方法的参数值传递；
优点：安全（防止SQL注入攻击）、性能高；

- `${占位符名}`，文本拼接替换
作用：执行时，直接将参数拼接在SQL语句中，存在SQL注入问题；
应用场景：表名、字段名动态设置时使用；例如：`@Select("selct id,name,score from ${tableName}") order by ${sortField}`
优缺点：不安全、性能低；

## 2.2、接口方法形参使用 @Param 注解

### 接口方法有多个形参须使用 @Param
由于 Java 文件 编译为 .class 字节码文件后，方法的形参名称并不会保留，那么在 sql 语句中引入的形参名将无法使用。
因此需要使用 `@Param` 注解为参数起名字，可以在 sql 语句中使用该形参名引入该形参，如下代码所示：

```java
@Select("select * from user where username=#{username} and password=#{password}")
public User findByUsernameAndpassword(@Param("username") String var1, @Param("password") String var2);
```

- @Param 注解，作用是为接口的方法形参起名字的。
- 基于官方骨架创建的 springboot 项目中，接口编译时会保留方法形参名称，@Param注解可以省略(#{形参名})。

### 接口方法只有一个形参不必使用 @Param
```java
// 只有一个普通类型形参时，#{……}里面的属性名可以随便写，如下所示：
@Delete("delete from dept where id = #{value}")
Integer delete(Integer id);

// 如果形参只有一个，并且是一个对象，那么在 sql 语句中可以直接引用对象的属性名，如下代码所示：
@Insert("insert into dept(name, create_time, update_time) values (#{name}, #{createTime}, #{updateTime})")
void insert(Dept dept);
```

## 2.3、Mybatis 执行 sql 返回值封装
执行 DML 语句时，可以返回一个int类型的返回值，表示该DML执行影响的记录数，用于判断SQL是否执行成功。注意：一般不必接收，而是使用void忽略！！！如下代码所示：
```java
 @Delete("delete from dept where id = #{id}")
Integer delete(Integer id);
// void delete(Integer id);
```

Mybatis 查询数据库返回的结果会封装到对应的实体类，当 实体类属性名 和 数据库表查询返回的字段名 一致时，mybatis 会自动封装。
如果 实体类属性名 和 数据库表查询返回的字段名不一致时，不能自动封装。这时需要对查询结果进行处理，有如下2种方式：

实现案例查看 `src/main/java/com/czm/mapper/DeptMapper.java`

### 方式1、手动结果映射：通过 @Results 及 @Result 进行手动结果映射。

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

### 方式2、起别名：在 SQL 语句中，对不一样的列名起别名，别名和实体类属性名一样。

```java
@Mapper
public interface DeptMapper {
    //查询部门列表
    @Select("select id, name, update_time updateTime from dept")
    public List<Dept> list();
}
```

### 方式3、开启驼峰命名规则映射：如果字段名 与 属性名符合驼峰命名规则，mybatis 会自动通过驼峰命名规则映射。--- 在项目中进行全局配置！

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
