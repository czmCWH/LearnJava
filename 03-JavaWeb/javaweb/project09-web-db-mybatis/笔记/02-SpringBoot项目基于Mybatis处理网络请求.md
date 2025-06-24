# 一、三层架构职责

`浏览器 -> Controller -> Service -> Mapper -> DB Server`

**Controller：**
1、接收请求参数
2、调用 Service 层的方法
3、响应结果

**Service：**
1、补全基础属性
2、调用 Mapper 接口方法

**Mapper：**
1、执行 sql 语句

# 二、Spring boot + Mybatis 接收网络请求，响应数据

## 1、Controller 中接收  URL 查询请求参数

案例实现 `DELETE 请求，/depts?id=123`，根据ID删除数据，具体实现查看 `src/main/java/com/czm/controller/Controller01.java`。


## 2、Mapper 中接收 Controller 的参数

案例实现，实现根据 ID 删除部门操作:

```java
 @Delete("delete from dept where id = #{id}")
Integer delete(Integer id);
// void delete(Integer id);
```
> 执行 DML 语句时，可以返回一个int类型的返回值，表示该DML执行影响的记录数。用于判断SQL是否执行成功。一般不用！！！
> 如果 mapper 接口方法形参只有一个普通类型的参数，#{……}里面的属性名可以随便写，如: #{id}、#{value}。

### Mybatis 中的 #号 与 $号:

`#{...}`：
执行时，会将#{.}替换为?，生成预编译SQL，并自动设置参数值；
参数值传递；
安全、性能高 (推荐)；

`${...}`：
执行时，直接将参数拼接在SQL语句中，存在SQL注入问题；
表名、字段名动态设置时使用；
不安全、性能低；

## 3、Controller 中接收 json 格式请求参数

接收json格式的请求参数：`POST /depts {"name":"教研部"}`

> JSON格式的参数，通常会使用一个实体对象进行接收。
> 规则：需要使用 `@RequestBody` 注解方法参数，并且请求JSON数据的键名 与 方法形参对象的属性名 相同。如果不同则获取参数为 null。

```java
@PostMapping("/depts")
public Result addDept(@RequestBody Dept dept) {
    System.out.println("--- post 请求参数 = " + dept);
    return Result.success();
}
```

## Controller 中接收 Path参数(路径参数)

接收请求参数（路径参数）：`GET /depts/(1、2或者n)`

* Path参数(路径参数)：通过请求URL直接传递参数，使用 `{...}` 来标识该路径参数，需要使用 `@PathVariable` 注解来获取路径参数。

```java
 @GetMapping("/depts/{id}")     // {id} 标识路径参数
public Result getById(@PathVariable Integer id) {   // @PathVariable 注解获取路径参数
    System.out.println("---接收到 路径参数 id = " + id);
    // 调用 Service 的方法
    Dept dept = deptService.getById(id);
    return Result.success(dept);
}
```

## Controller 中公共请求路径抽取

> 一个完整的请求路径，应该是类上的 @RequestMapping 的value属性 + 方法上的 @RequestMapping的value属性。


# 一、Mybatis 实现动态 SQL
SQL语句不是固定的，而是 随着用户的输入 或 外部条件的变化而变化的 SQL语句，称为 `动态SQL`。

* 动态SQL 是通过 XML配置文件配置 来实现的。
* XML 中动态SQL常用标签：
`<if>`: 用于判断条件是否成立。使用test属性进行条件判断，如果条件为true，则拼接 SQL。
`<set>`：用于 `update` 语句中，替换其 `set` 关键字，可去除多余的逗号。

实现案例可查看 `src/main/resources/com/czm/mapper/DeptMapper.xml`。

> 动态 Sql 的应用场景： 
> 更新时，根据值来更新部分SQL； 
> 查询时，根据变化的筛选条件来查询；


