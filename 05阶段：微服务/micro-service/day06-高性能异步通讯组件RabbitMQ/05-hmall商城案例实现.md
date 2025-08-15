# 一、hmall 商城案例实现
需求：改造`余额支付功能`，不再同步调用交易服务的 0penFeign 接口，而是采用 `异步MQ通知` 交易服务 更新 订单状态。

- 实现思路：在余额支付业务中，经过分析后，扣减余额 和 更新支付订单 属于核心业务，需要同步完成。而更新交易订单、通知服务、积分服务等业务属于边缘业务，
    使用 MQ 异步通知来完成，这样使得 余额支付业务 能快速完成，避免阻塞式等待。改造见图：`/img/06-基于MQ改造商城订单状态修改.jpg`。

- `pay-service` 支付服务：发送者；
- `trade-service` 交易服务：消费者；

## 步骤1、发送者和消费者都需引入依赖 和 `RabbitMQ`服务连接信息 ：
```pom
<!--AMQP依赖，包含RabbitMQ-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency> 
```

配置 `RabbitMQ` 服务器连接信息，推荐在 nacos 配置中心添加配置：
```application.yaml
spring:
  rabbitmq:
    host: localhost   # RabbitMQ 服务器的地址
    port: 5672  # 端口
    virtual-host: /hmall  # RabbitMQ 服务器上创建的虚拟主机名
    username: hmall  # 登录 RabbitMQ 服务器的用户名
    password: 123     # 登录 RabbitMQ 服务器的密码
```

## 步骤2、`hm-common` 公共模块中配置消息转换器
```xml
<!-- json 序列化依赖 - 用于 MQ 消息序列化 -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
</dependency>
```

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

## 步骤3、`trade-service` 订单服务 - 消息消费者，声明交换机、队列 及 绑定关系

> ⚠️ 在消费者端 声明队列、交换机及绑定关系!
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
    public void listenPaySuccess(Long orderId) {    // orderId 业务订单ID 作为消息内容
        System.out.println("czm ---- 收到 MQ 组件消息 = " + orderId);
        orderService.markOrderPaySuccess(orderId);
    }
}
```

## 步骤4、`pay-service` 支付服务 - 消息发送者，支付成功给订单服务发送消息 修改订单状态

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
        // 日志记录，如果消息发送失败 做其它处理！
        log.error("--- 发送支付状态通知失败，订单ID={}", po.getBizOrderNo(), e);
    }
 }
```

## 步骤5、重启微服务，访问 <http://localhost:18080> 前端页面，进入购物购车提交订单测试。