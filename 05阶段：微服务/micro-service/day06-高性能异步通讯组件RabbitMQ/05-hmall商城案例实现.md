# hmall 商城案例实现

> 需求：改造余额支付功能，不再同步调用交易服务的 0penFeign 接口，而是采用 异步MQ通知 交易服务 更新 订单状态。

## 1、发送消息和接收消息端都需引入依赖 ：
```pom
<!--AMQP依赖，包含RabbitMQ-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency> 
```

## 2、同时都需配置 `RabbitMQ` 服务器连接信息：
```application.yaml
spring:
  rabbitmq:
    host: localhost   # RabbitMQ 服务器的地址
    port: 5672  # 端口
    virtual-host: /hmall  # RabbitMQ 服务器上创建的虚拟主机名
    username: hmall  # 登录 RabbitMQ 服务器的用户名
    password: 123     # 登录 RabbitMQ 服务器的密码
```

## 3、`pay-service` 支付服务中发送消息，支付成功给订单服务发送消息 修改订单状态

> 代码实现：`/service/impl/PayOrderServiceImpl.java`

```java
 public void tryPayOrderByBalance(PayOrderFormDTO payOrderFormDTO) {
    // TODO 5.修改订单状态
    // 1、基于 OpenFeign 同步远程调用
    //        tradeClient.markOrderPaySuccess(po.getBizOrderNo());
    
    // 2、更改为 异步通讯组件-RabbitMQ 发送消息
        try {
            rabbitTemplate.convertAndSend("pay.direct", "pay.success", po.getBizOrderNo());
        } catch (Exception e) {
            log.error("--- 发送支付状态通知失败，订单ID={}", po.getBizOrderNo(), e);
        }
 }
```

## 4、`trade-service` 订单服务中接收消息，需声明交换机和队列信息

> 代码实现：`/listener/PayStatusListener.java`

```java
/**
 * 异步通讯组件-RabbitMQ 实现： 支付状态监听器
 */

@Component
@RequiredArgsConstructor
public class PayStatusListener {

    private final IOrderService orderService;

    /*
    监听 支付成功队列消息
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "trade.pay.success.queue", durable = "true"),
            exchange = @Exchange(value = "pay.direct", type = ExchangeTypes.DIRECT),
            key = {"pay.success"}
    ))
    public void listenPaySuccess(Long orderId) {
        System.out.println("czm ---- 收到 MQ 组件消息 = " + orderId);
        orderService.markOrderPaySuccess(orderId);
    }
}
```

## 5、`hm-common` 公共模块中配置消息转换器
```java
/**
 * 配置 RabbitMQ 组件的消息转换器
 */

@Configuration
public class MqConfig {
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
```
> ⚠️⚠️⚠️，此处声明的 Bean 其它服务中无法扫描到，因为其它服务和 hm-common 模块不在同一个包下，所以需要配置 `/resources/META-INF/spring.factories` 文件

## 6、访问 <http://localhost:18080> 前端页面，进入购物购车提交订单测试。