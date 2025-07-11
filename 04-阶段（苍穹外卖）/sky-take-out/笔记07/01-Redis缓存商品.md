# Redis 案例 - 缓存商品

## 1、为什么要做缓存？

用户端小程序展示的菜品数据都是通过查询数据库获得，即需要对硬盘进行 `IO 操作`。 如果用户端访问量比较大，数据库访问压力随之增大。
1. 结果：系统响应慢、用户体验差。 
2. 解决：通过 Redis 来缓存菜品数据，减少数据库查询操作。
3. 实现：
```
查询菜品 -> 从 `Redis` 查询是否存在 -> 缓存中存在，直接返回 
                                -> 缓存中不存在，查询数据库 -> 把查到的数据载入 Redis 缓存（供后续使用）
```

# 一、缓存 菜品 

> 通过 `Spring Data Redis` 来实现。

实现思路：

### 步骤1、缓存菜品数据

每个分类下的菜品保存一份缓存数据，把 分类ID 作为 Key 来区分。

客户端 代码实现：`/user/DishController.java` -> `list`

### 步骤2、清理 Redis 缓存
数据库中菜品数据有变更时，需要及时清理 Redis 缓存。

修改管理端接口 `/admin/DishController.java` 的相关方法，加入清理缓存的逻辑，需要改造的方法:

```
新增菜品 
修改菜品
批量删除菜品 
起售、停售菜品
```

> 兜底方案，设置缓存数据过期时间，避免忘记清理 Redis 缓存。


# 二、Spring Cache 框架
`Spring Cache` 是 `Spring` 提供的一个缓存框架，实现了基于 `注解` 的缓存功能，只需要简单地加一个注解，就能实现缓存功能。

`Spring Cache` 提供了一层抽象，底层可以切换不同的缓存实现：例如：`EHCache`、`Caffeine`、`Redis`。

> 通常使用 `Spring Cache` 结合 `Redis` 来实现缓存。

### 1、使用步骤 

1、导入依赖

```xml
 <!-- Spring Data Redis 起步依赖  -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
<!-- Spring Cache 缓存 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-cache</artifactId>
</dependency>
```

2、在启动类上加入 `@EnableCaching` 注解，开启缓存注解功能。

3、在需要的业务方法上添加 `Spring Cache` 注解。

### 2、Spring Cache 常用注解

* `@EnableCaching`，开启缓存注解功能，通常加在启动类上。

* `@Cacheable`，在方法执行前先查询缓存中是否有数据，如果有数据，则直接返回缓存数据；如果没有缓存数据，调用方法并将方法返回值放到缓存中。

* `@CachePut`，将方法的返回值放到缓存中。（例如新增时，但新增一般不返回值，所以不怎么用。）

* `@CacheEvict`，将一条 或 多条数据从缓存中删除。（一般用于新增、修改操作中）

#### Spring Cache 常用注解的参数

````java
/*
@Cacheable 注解属性介绍
    cacheNames|value：定义缓存的名称
    key：其键值的书写形式：
        #方法参数名        取方法中指定名字的参数值
        #root.args[0]    取方法中第一个参数的值;
        #p0              取方法中第一个参数的值;
        #a0              取方法中第一个参数的值;
*/
@Cacheable(cacheNames = "user", key = "#root.args[0]")
````

> 最终生成的 key 等于 `cacheNames::key`

````java
/*
@Cacheable 注解属性介绍
    key：其键值的书写形式:
        @Cacheable 能使用的，它都可以;
        #result.xx     取方法返回值作为key，如果result是对象，可以通过 result.xx 获取属性值;
*/
@CachePut(cacheNames = "user", key = "#result.id")
````

```java
/*
@Cacheable 注解属性：
    key：键值，书写形式与 @Cacheable 一样，表示删除指定的key；
    allEntries：为 true 时，删除 cacheNames 下的全部数据；
*/
@CacheEvict(cacheNames = "user", allEntries = true)
```

# 三、缓存 套餐

* 1、在用户端接口 `/user/SetmealController.java` 的 list 方法上加入 `@Cacheable` 注解，`缓存套餐数据，降低数据库压力`。

* 2、在管理端接口 `/admin/SetmealController.java` 的 `save、delete、update、startOrStop` 等方法上加入 `@CacheEvict` 注解，清理缓存。



添加购物车

查看购物车

清空购物车
