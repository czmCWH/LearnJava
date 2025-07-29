如前面介绍进行 跨服务 获取数据时，特别麻烦，即先需要使用 `DiscoveryClient` 获取服务实例，再通过 `RestTemplate` 进行远程调用，
最后对返回结果进行解析。使用 `OpenFeign` 可以解决这个复杂的远程调用问题。

# 一、OpenFeign
`OpenFeign` 是一个声明式的 `http` 客户端，是 `SpringCloud` 在 `Eureka` 公司开源的 `Feign` 基础上改造而来。
官网 <https://github.com/OpenFeign/feign>
> 其作用就是基于 `SpringMVC` 的常见注解，帮我们优雅的实现 `http` 请求的发送。

## 一、OpenFeign 快速入门

`OpenFeign` 已经被 `SpringCloud`  自动装配，实现起来非常简单:

> `OpenFeign` 发起的网络请求，最终由对应微服务的 Controller 进行处理。

#### 步骤1、引入依赖，包括 `OpenFeign` 和 负载均衡组件 `SpringCloudLoadBalancer`

```pom
<!-- openfeign 一个 http 客户端 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-openfeign</artifactId>
</dependency>
<!-- loadbalancer 负载均衡器 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-loadbalancer</artifactId>
</dependency>
```

负载均衡早期使用的是 `SpringCloud` 中的 `Ribbon`。现在新版本使用的是 `LoadBalancer`。

#### 步骤2，通过 `@EnableFeignClients` 注解，启用 OpenFeign 功能

```java
// 在启动类上添加 @EnableFeignClients 注解，即启用 OpenFeign 功能。
@EnableFeignClients
@SpringBootApplication
public class CartApplication {
    public static void main(String[] args) {
        SpringApplication.run(CartApplication.class, args);
    }
}
```

#### 步骤3，自定义一个 FeignClient
```java
// @FeignClient 注解接收一个 服务名称 来标记此接口为 OpenFeign 客户端，底层会实现一个动态代理去实现接口中的方法。
// OpenFeign 客户端根据 服务名称 去注册中心拉取 服务实例列表信息。OpenFeign 通过负载均衡器从服务列表中挑选一个 服务实例，用于发送 http 请求。
@FeignClient(value = "item-service")
public interface ItemClient {
    /**
     * OpenFeign 客户端采用 Spring MVC 注解来发送 http 请求，当然 OpenFeign 也有自身的一套注解，这样写避免学习成本问题。
     * 如下定义远程调用接口：根据商品id字符串数组，查询商品列表
     */
    @GetMapping("/items")
    List<ItemDTO> queryItemByIds(@RequestParam("ids") List<Long> ids);
}
```

#### 步骤4，使用 FeignClient 实现远程调用
```java
// 通过 Openfeign 实现 远程服务调用的 网络请求
private final ItemClient itemClient;

List<ItemDTO> items = itemClient.queryItemByIds(itemIds);
```

# 二、OpenFeign 性能优化 - 连接池
`OpenFeign` 对 `http` 请求做了优雅的封装，不过其底层发起 `http` 请求，依赖于其它的框架。`OpenFeign` 对这些框架做了自动装配，开发者可以自己选择，包括以下三种：
1. `HttpURLConnection`：默认实现，不支持连接池（每次都要重新创建连接，效率低下）；
2. `Apache HttpClient`：支持连接池；
3. `OKHttp`：支持连接池； 

具体源码可以参考 `FeignBlockingLoadBalancerClient` 类中的 delegate 成员变量。

> http 请求使用 连接池 可以减少 创建/销毁连接 的开销，提高远程调用性能。

## OpenFeign 整合 OKHttp 

#### 步骤1、引入依赖
```pom
<dependency>
    <groupId>io.github.openfeign</groupId>
    <artifactId>feign-okhttp</artifactId>
</dependency>
```

#### 步骤2、开启连接池功能
```application.yaml
feign:
  okhttp:
    enabled: true
```

# 三、OpenFeign 最佳实践
前面我们在学习 `OpenFeign` 的过程中，需要在每个需要远程调用的微服务中自定义一个 `FeignClient` 来发送 `http` 请求。
这样写存在代码重复，当业务修改时每个服务的 `FeignClient` 也需要修改。

### 方式一

方式一：在每一个被调用者的服务中，把对外提供接口功能单独分离出来成新的模块，这些新模块由该被调用者开发维护，其它微服务中通过 Maven 坐标引入 进行调用。
* 缺点：原先微服务的项目结构变复杂； 
* 适用于：工程结构为 独立 Project 的微服务项目。

### 方式二

方式二：在 `Project` 工程中，创建一个新的模块作为通用模块，此模块包含整个项目所用用于远程调用的客户端 和 dto，其它需要使用的模块通过 Maven 坐标引入 进行调用。
* 缺点：这样做耦合度高，因为不同业务的暴露的接口需要放在同一个服务中开发。
* 适用于：工程结构为 Maven 聚合的微服务项目。

详细说明见图：`day03-微服务入门/img/07-OpenFeign最佳实践`；
采用方式二的代码实现见：笔记-day03

### 实践注意
当定义的 `FeignClient` 不在 `SpringBootApplication` 的扫描包范围时，这些 `FeignClient` 无法使用。有两种方式解决：

1. 方式一，指定在启动类上指定 FeignClient 所在的包，如：`@EnableFeignClients(basePackages = "com.hmall.api")`
2. 方式二，指定 FeignClient 字节码，如：`@EnableFeignClients(clients = {UserClient.class})`

> ⚠️⚠️⚠️ 上面 方式一、方式二 都创建了一个新的模块，因此在配置 `OpenFeign` 使用的地方需要配置扫描范围。

# 四、OpenFeign 日志输出
OpenFeign 只会在 FeignClient 所在包的日志级别为 Debug 时，才会输出日志。而且其日志级别有4级：
`NONE`：不记录任何日志信息，这是默认值。
`BASIC`：仅记录请求的方法，URL以及相应状态码和执行时间。
`HEADERS`：在 `BASIC` 的基础上，额外记录了请求和响应的头信息。
`FULL`：记录所有请求和响应的明细，包括头信息、请求体、元数据。

由于 Feign 默认的日志级别时 NONE，所以默认我们看不到请求日志。

* 1、要自定义 `OpenFeign` 日志级别需要声明一个类型为 `Logger.Level` 的 `Bean`，在其中定义日志级别：
```java
public class DefaultFeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
```

* 2、但此时这个 Bean 并未生效，要想配置某个 FeignClient 的日志，可以在 @FeignClient 注解中声明:
但此方式只是作用在某个 ItemCli ent 上，只对配置的 ItemClient 有效，其它的 Client 无效。
```java
@FeignClient(value = "item-service", configuration = DefaultFeignConfig.class)
public interface ItemClient {

}
```

* 3、如果想要全局配置，让所有 `FeignClient` 都按照这个日志配置，则需要在 `@EnableFeignClients` 注解中声明:
```java
@EnableFeignClients(basePackages = "com.hmall.api.clients", defaultConfiguration = DefaultFeignConfig.class)
```

> ⚠️，一般不需要配置 OpenFeign 的日志级别，仅在调试时需要配置，这样避免影响程序运行性能。