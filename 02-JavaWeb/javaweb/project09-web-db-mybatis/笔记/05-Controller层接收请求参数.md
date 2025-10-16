# 三、Controller 层接收请求参数的方式
案例：实现 `DELETE 请求，/depts?id=123`，根据ID删除数据。

## 1、Controller 中接收  URL 查询请求参数

代码见：`src/.../Controller01.java` 中的示例。

## 2、Controller 中接收 json 格式请求参数

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

一个完整的请求路径，应该是类上的 `@RequestMapping` 的 `value`属性 + 方法上的 `@RequestMapping` 的 value属性。
实体类上的 `@RequestMapping` 修饰放在 `@RestController` 之前。

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
