# 一、在Java中操作Redis 【重点)

## 1、Redis 的 Java 客户端

* Jedis

* Lettuce

* Spring Data Redis

`Spring Data Redis` 是 `Spring` 的一部分，对 `Redis` 底层开发包进行了高度封装。
在 `Spring` 项目中，可以使用 `Spring Data Redis` 来简化操作。


## 2、Spring Data Redis 使用步骤

#### 1、导入 Spring Data Redis 的 maven 坐标；

```xml
<!-- Spring Data Redis  -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

#### 2、配置 `Redis` 数据源连接信息

在 `application-dev.yml` 中添加：
```yml
sky:
  redis:
    host: localhost
    port: 6379
    password: 123456
    database: 10
```
在 `application.yml` 中添加读取 `application-dev.yml` 中的相关 `Redis` 配置：
```yml
spring:
  data:
    redis:
      host: ${sky.redis.host}
      port: ${sky.redis.port}
      #      password: 123456   # 如果没有设置密码，则不写
      database: ${sky.redis.database}
```

#### 3、编写配置类，创建 RedisTemplate 对象

代码实现：`RedisConfiguration.java`

通过 RedisTemplate 对象操作 Redis：

```java
@Autowired
private RedisTemplate redisTemplate;

@Test
void contextLoads() {
    System.out.println(redisTemplate);
}
```

# 二、RedisTemplate 的基本使用

`RedisTemplate` 针对大了的 `API` 进行了归类封装，将同一数据类型的操作封装为对应的 `Operation` 接口，具体分类如下：

* `ValueOperations`：string数据操作

* `SetOperations`：set类型数据操作

* `ZSetOperations`：zset类型数据操作 

* `HashOperations`：hash类型的数据操作 

* `ListOperations`：list类型的数据操作

> 代码实现：`src/test/.../TestRedis.java`


# 三、案例 - 商家营业状态
代码实现：`/controller/admin/ShopController.java` + `/controller/user/ShopController.java`

