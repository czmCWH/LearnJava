# 一、发送者 publisher 可靠性
`RabbitMQ` 提供了 2 种机制来保证发送者消息的可靠性，确保消息能发送到MQ：

> 代码实现：`/day07/01-mq-demo(RabbitMQ 高级功能)/publisher/`

## 1、发送者重连机制
当 发送者 publisher 给 MQ 服务器发送消息时，需先与它建立连接。有的时候由于网络波动，可能会出现 发送者 连接 MQ 失败导致发送消息丢失的情况。
在 publisher 中添加如下配置可以开启连接失败后的重连机制：

```application.yml 
spring:
  rabbitmq:
    # 发送者连接 RabbitMQ 失败重连配置
    connection-timeout: 1s  # 设置MQ连接超时时间，即与MQ建立连接超过1s，则认为连接失败
    template:
      retry:
        enabled: true   # 开启超时重试连接机制
        initial-interval: 1000ms    # 连接失败后的初始等待时间，过了此时间再重试连接
        multiplier: 1   # 失败后下次的等待时长倍数，下次等待时长 = initial-interval * multiplier
        max-attempts: 3   # 最大重试次数
```

当网络不稳定的时候，利用重试机制可以有效提高消息发送的成功率。
不过 `SpringAMQP` 提供的重试机制是 `阻塞式的重试`，也就是说多次重试等待的过程中，`当前线程是被阻塞的`，会影响业务性能。
即：当执行到 `rabbitTemplate.convertAndSend()` 发送消息连接MQ失败时，会根据配置等待重连，阻塞当前代码执行。

> ⚠️ 如果对于业务性能有要求，建议`禁用重试机制`。 如果一定要使用，请合理配置等待时长和重试次数，当然也可以考虑使用`异步线程来执行发送消息的代码`。


## 2、发送者确认机制
使用 发送者重连机制 来确保 `publisher` 与 `MQ` 之间连接的可靠性，连接建立成功后发送消息也可能失败。
为了确保 发送消息 到 MQ 这个过程的可靠性，就需要使用 发送者确认机制。

`SpringAMQP` 提供了 `Publisher Confirm` 和 `Publisher Return` 两种确认机制。开启确机制认后，当 publisher 发送消息给 MQ 后， MQ 会返回确认结果给 publisher。
MQ 返回的确认结果有以下几种情况：
1. 消息投递到了 MQ，但是路由失败（如没给交换机绑定队列）。此时会通过 `PublisherReturn` 返回路由异常原因；然后通过 `Publisher Confirm` 返回结果`ACK`，告知投递成功。
2. 临时消息（即不会写入到磁盘中的消息，而是保存在内存中）投递到了 MQ，并且入队成功；通过 `Publisher Confirm` 返回`ACK`，告知投递成功。
3. 持久消息（即会写入到磁盘中的消息）投递到了 MQ，并且入队完成持久化，通过 `Publisher Confirm` 返回`ACK`，告知投递成功。
4. 其它情况都会返回 `NACK`，告知投递失败需要重发消息。

> 即只要收到 MQ 返回结果为 NACK 都需要重发消息；

使用 `SpringAMQP` 实现发送者确认机制：

### 步骤1，在 publisher 这个微服务的 application.yml 中添加配置，开启发送者确认机制
```application.yml
spring:
  rabbitmq:
    # 发送者开启确认机制
    publisher-confirm-type: correlated  # 开启 Publisher Confirm 机制，并配设置类型。通常使用 none 或 correlated  机制。
    publisher-returns: true   # 开启 Publisher Return 机制
```

`publisher-confirm-type` 有三种模式可选：
1. `none`：关闭 Publisher Confirm 机制。
2. `simple`：同步阻塞等待MQ的回执消息。即在发送消息的代码处阻塞等待 MQ 确认返回结果。
3. `correlated`：MQ异步回调方式返回回执消息。即在发送消息时设置回调函数异步接收 MQ 确认返回结果。


### 步骤2，在 publisher 微服务中编写 `Publisher Return` 处理消息投递时路由异常
每个 `RabbitTemplate` 只能配置一个 `ReturnCallback`, 因此需要在项目启动过程中配置：
```java
@Slf4j
@RequiredArgsConstructor
@Configuration
public class MqConfig {
    private final RabbitTemplate rabbitTemplate;

    // @PostConstruct 注解表示 RabbitTemplate Bean 调用完毕后，就会执行 init 初始化函数，并且只会执行一次
    @PostConstruct
    public void init() {
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            log.error("监听到了消息 return callback");
            log.debug("交换机: {}", returnedMessage.getExchange());
            log.debug("路由 routingKey: {}", returnedMessage.getRoutingKey());
            log.debug("失败原因 Message: {}", returnedMessage.getMessage());
            log.debug("replyCode: {}", returnedMessage.getReplyCode());
            log.debug("replyText: {}", returnedMessage.getReplyText());
        });
    }
}
``` 

### 步骤3，在 publisher 微服务中编写 `Publisher Confirm` 处理消息投递到队列的结果
在每个发送消息的代码地方，都需指定消息ID、消息 ConfirmCallback：
```java
@Test
public void testConfirmCallback() throws InterruptedException {
    // 0、根据 唯一消息ID 创建 CorrelationData
    CorrelationData cd = new CorrelationData(UUID.randomUUID().toString());
    // 给 Future 添加 ConfirmeCallback
    cd.getFuture().addCallback(new ListenableFutureCallback<CorrelationData.Confirm>() {
        
        @Override
        public void onSuccess(CorrelationData.Confirm result) {
            // Future 接收到回执的处理逻辑，参数中的 result 就是回执内容
            if (result.isAck()) {   // true，代表 ack 回执；false：代表 nack 回执
                log.debug("收到 ConfirmCallback ack，消息发送成功！");
            } else {
                // TODO 需要准备重新发送
                log.error("收到 ConfirmCallback nack，消息发送失败！reason：{}", result.getReason());
            }
        }

        @Override
        public void onFailure(Throwable ex) {
            // Future 发生异常时的处理逻辑，基本不会触发
            log.error("spring amqp 处理确认结果异常", ex);
        }
    });

    // 1、交换机名称
    String exchangeName = "hmall.direct";
    // 2、消息
    String message = "测试发送者消息确认机制！";
    // 3、发送消息
    rabbitTemplate.convertAndSend(exchangeName, "blue", message, cd);

    // 延迟等待 cd 回调
    Thread.sleep(20000);
}
```

### 注意
`发送者确认机制` 由于需要和 MQ 进行通讯确认，会影响消息发送的效率，通常情况下不建议开启。因为大多数情况下，消息发送失败的情况概率极低。
就算开启了，也要限定重试的次数，而不要无限次去重试，避免对性能造成较大影响。
