# 一、服务保护组件 - Sentinel
`Sentinel` 是阿里巴巴开源的一款微服务流量控制组件。官网地址：<https://sentinelguard.io/zh-cn/>

`Sentinel` 客户端（即核心库、jar包）里面包含了服务保护的各种功能，只要在微服务代码中引用了该jar包，就可以在微服务代码中配置 Sentinel 提供的功能；
或者使用 Sentinel 控制台与 Sentinel 客户端进行交互。可以通过 Sentinel 控制台 监控微服务内部接口运行的情况，以及方便的配置对微服务的保护配置。

# 二、Sentinel 入门

如下使用 Sentinel 客户端/控制台 的方式使用 Sentinel。

> 代码实现：`cart-service`、`hm-api`

## 1、在本机上启动 Sentinel 控制台
Sentinel 控制台：<https://github.com/alibaba/Sentinel/wiki/Dashboard>
在 <https://github.com/alibaba/Sentinel/releases> 上下载 `sentinel-dashboard-1.8.6.jar`，然后通过如下命令在本机上启动 sentinel-dashboard：

```shell
# 切换到 sentinel-dashboard 存放目录下
$ cd /user/sentinel

# 运行下载的 sentinel-dashboard release 包
$ java -Dserver.port=8090 \
-Dcsp.sentinel.dashboard.server=localhost:8090 \ 
-Dproject.name=sentinel-dashboard \
-jar sentinel-dashboard-1.8.6.jar \
```

启动成功后，访问 <http://localhost:8090>，输入账号密码：sentinel 登录 `sentinel-dashboard`。

## 2、在微服务中整合 Sentinel
1、在需要被监控的微服务项目添加 Sentinel 依赖
```pom
<!-- Sentinel 客户端 -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-sentinel</artifactId>
</dependency>
```

2、在需要被监控的微服务项目里配置连接 Sentinel 控制台信息
```application.yml
# 配置 sentinel 控制台连接信息
spring:
  cloud:
    sentinel:
      transport:
        dashboard: localhost:8090
      http-method-specify: true   # 开启请求方式前缀，即会把请求方式+请求路径作为 簇点资源名称
```

## 3、测试
多访问几次接入 sentinel 客户端的微服务的接口，即可在 <http://localhost:8090> 控制台 [簇点链路] 查看微服务信息。
访问过 SpringMVC 的接口都会在 Sentinel 控制台 [簇点链路] 中显示。 

> 实现监控效果见图 `/img/02-服务保护技术Sentinel/01-Sentinel控制台.jpg`

### 簇点链路
* 簇点链路，就是单机（即单个微服务内部）调用链路。是一次请求进入服务后经过的每一个 被Sentinel监控的资源链。
* 被 Sentinel 监控的资源叫做 簇点。
* 默认 Sentinel 会监控 SpringMVC 的每一个 Endpoint(http接口、或Controller接口)。
* ⚠️ 限流、熔断等都是针对 簇点链路 中的 资源 设置的。而 资源名 默认就是接口的请求路径。

由于 `Restful` 风格的API请求路径一般都相同，这会导致簇点资源名称重复。因此我们要修改 `Sentinel` 配置 `http-method-specify: true`，把 `请求方式+请求路径` 作为簇点资源名称。

 
# 三、请求限流-限制QPS
在 sentinel 控制台 <http://localhost:8090> 列表中，选择对应微服务的 【簇点链路】->【操作】->【流控】，即可对其做限流配置，然后在 【流控规则】中可以查看。
请求限流配置见：`/img/02-服务保护技术Sentinel/02-请求限流.jpg`

* QPS：QPS反映服务器在1秒内响应的请求数量，例如：QPS=100 表示每秒处理100次请求。
* 单线程QPS：单个线程每秒能处理的请求上限。例如：若线程处理一个请求需50ms，则理论最大QPS为1000ms/50ms=20。

> 当请求数量超过 配置的流控阈值时，请求报错，返回：code: 429, message: Blocked by Sentinel(flow limiting)。

# 四、线程隔离-限制并发线程数
当 `item-service`(商品服务) 出现阻塞或故障时（如：业务请求处理非常慢），调用 `item-service` 的 `cart-service`(购物车服务) 也需要等待 `item-service` 返回。
`cart-service` 因此被拖慢，如果此时 `GET/carts`查询购物车列表请求 并发比较高，那么所有的请求都需要等待，占用了Tomcat 连接和线程数，随着时间推移导致 Tomcat 资源耗尽。

为了解决这个问题，就必须限制 `cart-service` 中 `GET/carts` 业务 的可用线程数，实现线程隔离。

- 说明：线程隔离属于流控的一部分，隔离目的是为了限制簇点能使用的并发线程数量。
- 并发线程数：是指每秒能使用的线程资源数量。例如：单线程的QPS为2，那么5个线程的QPS为10。

线程隔离实现： 在 `sentinel` 控制台<http://localhost:8090> 列表中，选择 `GET/carts` 簇点，点击后面的`【操作】->【流控】`按钮，即可配置线程隔离。

## 测试
1、延迟 `item-service` 中 `GET/items` 业务：
```java
// 模拟业务延迟500ms，即1秒只能处理2个请求
ThreadUtil.sleep(500);
```

2、修改 `cart-service` 服务的默认请求配置，避免默认模式本机扛不住。
```application.yaml
server:
  port: 8082
  tomcat:
    threads:
      max: 50   # tomcat 的最大线程数为 200
    accept-count: 50  # tomcat 允许排队等待的线程数为 100
    max-connections: 100  # tomcat 默认允许最大连接数为 8192
```

3、使用 Apache JMeter 工具请求查询购物车列表 `GET/carts` 业务

# 五、线程隔离配合 Fallback 提高体验
查询购物车列表时，`cart-service`(购物车服务) 需要使用 Feign 客户端查询 `item-service`(商品服务) 中的商品信息。如果此时商品服务响应速度非常慢，
那么在并发较高的情况下耗尽 Tomcat 资源，导致整个 `cart-service` 被拖慢。

如上所示，通过 Sentinel 对 `cart-service` 中的 查询购物车列表业务做了线程隔离，避免这个业务耗尽资源。

问题：线程隔离虽然对 `cart-service` 起到了保护，但是存在一个问题，会导致 被隔离的业务（即查询购物车列表业务）完全不可用，接口请求报错。
为了使`GET/carts` 业务中查询商品业务异常时直接报错，可以使用 `Fallback` 来友好提示用户。

解决：对查询购物车列表业务中，只对 Feign 远程调用查询商品业务的逻辑添加 `fallback` 逻辑，当 Feign 请求失败时走 `fallback`，购物车服务不会受到影响。实现步骤如下：

## 步骤1，配置 `cart-service` 微服务 将 FeignClient 作为 Sentinel 的簇点资源
```application.yaml
feign:
  okhttp:
    enabled: true
  sentinel:
    enabled: true   # 在使用 FeignClient 服务里开启 Sentinel 监控，这样 Feign 发起的资源 Sentinel 控制台可以监控到
```

## 步骤2，在 hm-api 模块中，给 FeignClient 添加 fallback 逻辑

方式一，`FallbackClass`，无法对远程调用的异常做处理

方式二，`FallbackFactory`，可以对远程调用的异常做处理（通常使用这种！）

## 步骤3，在 hm-api 模块中，实现对 FeignClient 的 fallback 逻辑。

1、如下自定义 `FallbackFactory`，实现 ItemClient(商品查询客户端) 的 fallback 逻辑：
```java
/**
 * 给 ItemClient（商品FeignClient）添加 Fallback 逻辑
 */
@Slf4j
public class ItemClientFallbackFactory implements FallbackFactory<ItemClient> {
    @Override
    public ItemClient create(Throwable cause) {
        // 返回需要被 fallback 逻辑处理的 FeignClient。如果 Sentinel 监控的 Feign 资源异常了，就会走此 FeignClient 中的逻辑。
        return new ItemClient() {
            // 当 ItemClient 商品查询失败时，走 fallback 的逻辑(例如：友好提示、记录日志、null值等)
            @Override
            public List<ItemDTO> queryItemByIds(Collection<Long> ids) {
                // 记录日志
                log.error("--- 查询商品失败: ", cause);
                // 查询商品业务，直接返回空数据
                return CollUtils.emptyList();
            }

            // 当 ItemClient 扣减商品库存失败时，走 fallback 的逻辑
            @Override
            public void deductStock(List<OrderDetailDTO> items) {
                // 记录日志
                log.error("--- 扣减商品库存失败：: ", cause);
                // 
                throw new RuntimeException(cause);
            }
        };
    }
}
```

2、将自定义的 `FallbackFactory` 注册为一个 bean：
```java
public class DefaultFeignConfig {
    @Bean
    public ItemClientFallbackFactory itemClientFallbackFactory() {
        return new ItemClientFallbackFactory();
    }
}
```

3、在对应 FeignClient 接口中指定 fallbackFactory 为自定义的 FallbackFactory：
```java
@FeignClient(value = "item-service", fallbackFactory = ItemClientFallbackFactory.class)
public interface ItemClient {
    
    @GetMapping("/items")
    List<ItemDTO> queryItemByIds(@RequestParam("ids") Collection<Long> ids);

    @PutMapping("/items/stock/deduct")
    void deductStock(@RequestBody List<OrderDetailDTO> items);
}
```

## 步骤4，在 sentinel 控制台中，对 购物车服务中查询购物车列表业务 的 Feign 簇点链路添加 线程隔离，而不是对整个 购物车查询业务添加线程隔离。
> 实现见图：`/img/02-服务保护技术Sentinel/03-线程隔离1/2.jpg`


# 六、服务熔断
如上所示，我们对 `cart-service` 服务的 查询购物车列表业务中，对 Feign 查询商品列表做了线程隔离。这样做虽然限制了 `GET/carts` 查询业务使用服务的资源，
但是每次  `GET/carts` 业务中都会进行 `item-service/items` 查询，如果该查询一直响应慢或异常，虽然做了 Fallback 处理，该请求依然会走`item-service/items`逻辑，浪费服务器资源。

有没有什么办法监控 `GET/carts` 业务中的 `item-service/items` 查询业务，如果出现响应慢或异常情况时，直接熔断该请求，直接走 Fallback 逻辑。
这就需要使用 Sentinel 服务熔断配置了。

`熔断` 是解决雪崩问题的重要手段。思路是由 断路器 统计服务调用的异常比例、慢请求比例，如果超出阈值则会 熔断 该服务。
熔断该服务：是指拦截（拒绝）访问该服务的一切请求。而当服务恢复时，断路器 会放行访问该服务的请求。

实现：在 sentinel 控制台中 簇点资源后的 【熔断】按钮，配置熔断策略。