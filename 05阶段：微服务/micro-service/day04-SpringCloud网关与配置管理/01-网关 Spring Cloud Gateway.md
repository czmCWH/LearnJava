# 一、微服务请求路由问题
通过前面学习，采用 微服务架构 拆分单体项目后，原来的一个服务变成了多个微服务，那么前端发送网络请求就会遇到一些问题：
1. 前端请求该访问哪一个微服务？
   - 每个微服务功能各不 相同，端口不一样；
   - 同一个微服务部署时可能会部署多个，存在多个端口；
   - 并且每个微服务的部署时IP可能会发生改变；
2. 用户微服务中完成了用户登录校验，其它微服务模块如何获取用户信息？

综上所述微服务项目面临2大问题：
1. 服务地址过多，将来可能还会变化，前端无法知道请求那个地址；
2. 每个服务可能都需要用户登录信息，每个服务各自做登录校验不仅麻烦，而且还有密钥泄漏的风险。

> 解决上述问题，需要用到 网关技术。

# 二、网关
网关：就是网络的关口，负责请求的路由、转发、身份校验。
- 网关也是一个微服务，它会从注册中心拉取所有微服务的地址。 
- 有了网关微服务的地址不需要暴露给前端，只需要将 `网关地址` 告诉前端，前端直接请求网关地址。
- 网关底层是没有业务逻辑的，它的作用是根据开发者在 application.yaml 中配置的路由规则，判断前端请求由那个微服务处理，最后将请求转发到对应的微服务。

官网：<https://spring.io/projects/spring-cloud-gateway>

1. 路由：网关根据前端请求地址判断那个微服务处理请求； 
2. 转发：网关将请求转发对应的微服务；
3. 身份校验：对请求身份校验（校验是否登录）；
4. 负载均衡：网关通过负载均衡算法从多实例服务中选择一个服务；

> ⚠️ 网关不属于 SpringMVC，因此在网关中不能引入 SpringMVC 中的内容。

## 在 SpringCloud 中 网关的实现包括两种：

* 1、`Spring Cloud Gateway`（吞吐能力强，主流的网关技术）
  - Spring 官方出品；
  - 基于 WebFlux 响应式编程；
  - 无需调优即可获得优异的性能；

* 2、`Netflix Zuul`
  - Netflix 出品；
  - 基于 Servlet 阻塞式编程；
  - 需要调优才能获得与 SpringCloudGateway 类似的性能；

# 三、网关-路由转发入门

## 步骤1，创建网关新模块
创建 `hm-gateway` 模块。

## 步骤2，引入网关依赖
```xml
<!--网关依赖，它是一个 starter 自动装配，只要配置好了路由规则，会自动完成实现-->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-gateway</artifactId>
</dependency>
<!-- nacos 服务注册发现 - 网关从注册中心拉取服务列表 -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
<!-- loadbalancer 负载均衡器 - 网关使用负载均衡从多实例服务中选择一个服务 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-loadbalancer</artifactId>
</dependency>
```

## 步骤3，编写启动类
```java
@SpringBootApplication
public class GatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(GatewayApplication.class, args);
    }
}
```

## 步骤4，配置路由规则（重点）
```application.yaml
server:
  port: 8080    # 网关服务的端口。建议配置为 8080，http请求默认端口就是 8080。
spring:
  application:
    name: gateway   # 网关服务名称
  cloud:
    nacos: # 配置 nacos 注册中心地址
      server-addr: localhost:8848
    # 网关路由配置
    gateway:
      routes:
        - id: user-service      # 路由规则ID，唯一。一般和微服务名称保持一致 
          uri: lb://user-service    # lb 表示使用 loadbalancer 负载均衡器；user-service 表示路由到达的目标微服务；
          predicates:       # 路由断言(规则)，判断请求是否符合规则，符合规则的请求路由到目标
            - Path=/users/**,/addresses/**     # 以请求路径做路由判断，表示以 users、addresses 开头符合要求的请求会路由到 user-service 微服务。
        - id: item-service
          uri: lb://item-service
          predicates:
            - Path=/items/**,/search/**
        - id: cart-service
          uri: lb://cart-service
          predicates:
            - Path=/carts/**
        - id: trade-service
          uri: lb://trade-service
          predicates:
            - Path=/orders/**
        - id: pay-service
          uri: lb://pay-service
          predicates:
            - Path=/pay-orders/**
```


# 四、网关-路由规则的属性
`application.yaml` 文件中的配置一般都有一个 Java 类来读取。网关路由 的 yaml 配置 对应的 java 类型是 `RouteDefinition`，其中常见的属性有:
1. `id`：路由唯一标示；
2. `uri`：路由目标服务地址；
3. `predicates`：路由断言(规则)，判断请求是否符合当前路由；
4. `filters`：路由过滤器，对 进入网关的请求 或 微服务处理完作出的响应 做特殊处理；

介绍可见图：`/img/02-网关路由属性`。

## 1、predicates 路由断言(规则)有多种
Spring 提供了12种基本的 `RoutePredicateFactory` 实现：

详情查看官网 `Spring Cloud Gateway` -> `Learn` -> `选择版本文档 Reference Doc` -> `Route Predicate Factories`：
<https://docs.spring.io/spring-cloud-gateway/docs/3.1.9/reference/html/#gateway-request-predicates-factories>

## 2、filters 微服务路由过滤器
网关中提供了33种路由过滤器，每种过滤器都有独特的作用。

详情查看官网 `Spring Cloud Gateway` -> `Learn` -> `选择版本文档 Reference Doc` -> `GatewayFilter Factories`：
<https://docs.spring.io/spring-cloud-gateway/docs/3.1.9/reference/html/#gatewayfilter-factories>


# 五、网关的 cors 跨域配置

