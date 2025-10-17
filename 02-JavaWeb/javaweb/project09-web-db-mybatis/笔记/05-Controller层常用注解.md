# 一、Controller 层接收不同类型的参数

## 1、Controller 中接收普通参数  

### 1.1、URL查询请求参数：`/depts?id=1`

- 方式1，通过 Spring 提供的 @RequestParam 注解，将请求参数绑定给方法形参。--- 有时可能会用
  @RequestParam 注解的作用：绑定请求参数到形参、设置请求参数默认值、请求参数是否必传等
```java
// @RequestParam 注解的 required 属性默认为 true，代表 id 参数必须传递，如果不传递请求将报错：code=400。
// 如果参数可选，可以将 required 属性设置为 false 避免报错。
@DeleteMapping("/depts2")
public Result delete(@RequestParam(value = "id", required = false) Integer deptId) {
    System.out.println("--- depts2 删除ID = " + deptId);
    // ...
}
```

- 方式2，如果请求参数名 与 形参变量名相同，直接定义方法形参即可接收。(`省略@RequestParam`) --- 推荐！
```java
@DeleteMapping("/depts3")    // 简写方式
public Result delete3(Integer id) {
  System.out.println("--- depts3 删除ID = " + id);
  // ...
}
```

### 1.2、URL路径参数：`/dept1/{id}/{name}`
路径参数：通过请求URL直接传递参数，在uri资源中使用 {路径参数名} 来标识该路径参数，需要使用 @PathVariable 获取路径参数
如果路径参数的参数名 与 方法形参名 相同，可以省略  @PathVariable 中 value 属性值。
```java
@GetMapping("/depts1/{id}/{name}")    // {id} 是一个路径参数
public Result getById(@PathVariable Integer id, @PathVariable String name) {   // @PathVariable 注解获取路径参数
System.out.println("---接收到 路径参数 id = " + id + ", " + name);
return Result.success();
}
```

## 2、Controller 中接收 json 格式请求参数
接收json格式的请求参数：`POST /depts {"name":"教研部"}`

JSON 格式的参数，通常会使用一个实体对象进行接收。
规则：JSON数据的键名 必须与 方法形参对象的属性名相同，并需要使用 @RequestBody 注解标识。
json 格式参数适用场景：主要在 POST、PUT 请求的 请求体 中传递。

```java
@PostMapping("/depts")
public Result addDept(@RequestBody Dept dept) {
    System.out.println("--- post 请求参数 = " + dept);
    // ...
}
```

# 二、Controller 中请求映射注解
`@RequestMapping` 是 Spring MVC 框架中用于用于将 HTTP 请求映射到 Controller 的处理方法 上的核心注解，可以作用于 类、方法。

@RequestMapping 作用于类上时，会作为该类中所有方法的 公共路径前缀。
一个完整的请求路径，应该是类上的 @RequestMapping 的value属性 + 方法上的 @RequestMapping的value属性。

`@RequestMapping` 常用衍生注解：
  - `@GetMapping`	
  - `@PostMapping`
  - `@PutMapping`
  - `@DeleteMapping`
  - `@PatchMapping`
