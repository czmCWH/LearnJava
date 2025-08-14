# 一、消息转换器
在使用 `rabbitTemplate.convertAndSend` 时，可以发送任意类型的消息到 RabbitMQ 服务器，这个过程是通过网络字节码传输的。
因此在进行传输时，需要使用 消息转换器 把任意类型的消息对象转换为字节，那么 消息转换器 是把对象转换为什么格式呢？

通过跟踪发送端的`rabbitTemplate.convertAndSend` 发送代码底层实现可知，`Spring` 的对消息对象的处理是由 `org.springframework.amqp.support.converter.MessageConverter`来处理的。
而默认实现是 `SimpleMessageConverter`，它是基于 JDK 的 `ObjectOutputStream` 完成序列化。此序列化存在如下问题：
* JDK 的序列化有安全风险；（JDK做代码反序列化时容易被代码注入）
* JDK 序列化的消息太大，占用空间过多；
* JDK 序列化的消息可读性差，消息以乱码显示；

> 因此，议采用 JSON 序列化代替默认的 JDK 序列化，需实现如下步骤。
> 发送方和接收方都需使用相同的 消息转换器。


## 步骤1，在 publisher 和 consumer 中都要引入 jackson 依赖：

```pom
<!-- json 序列化依赖 -->
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
</dependency>
```


## 步骤2，在 publisher 和 consumer 中都要配置 MessageConverter:

```java
/**
 * 引入消息转换器
 */
@Configuration
public class MessageConfig {
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
```


## 步骤3，测试发送自定义类型消息、接收自定义类型消息，然后在 RabbitMQ 控制台查看消息内容

```java
/*
发送map类型消息到 object.queue
 */
@Test
public void testMap(){
    // 1、准备消息
    String queueName = "object.queue";
    Map<String,Object> map = new HashMap<>();
    map.put("name","黑马333");
    map.put("age",18);
    // 2、发送消息
    rabbitTemplate.convertAndSend(queueName,map);
}
```

```java
/*
监听 object.queue 队列的消息。发送消息用的什么格式，接收消息就使用什么格式！
 */
@RabbitListener(queues = "object.queue")
public void listenObjectQueue(Map<String,Object> map){
    System.out.println("消费者接收到消息: "+map);
}
```
