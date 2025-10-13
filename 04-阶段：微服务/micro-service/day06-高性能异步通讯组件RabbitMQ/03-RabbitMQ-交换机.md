# 一、交换机
- 交换机的作用：
  1. 接收 publisher 发送者 发送的消息;
  2. 并将消息按照规则路由（规则与交换机类型有关）到与其绑定的队列;

- 常见交换机的类型有以下三种:
  1. Fanout：广播； 
  2. Direct：定向； 
  3. Topic：话题；

前面学习过程中，直接把消息发送给一个队列，虽然有多个消费者监听这个队列消息，但一个消息只能被一个消费者处理，无法实现让所有消费者来处理、无法传递定向消息。
而项目开发中，通常为每个微服务创建自己的队列，每个微服务作为消费者绑定到自己的队列上。然后将这些队列绑定到不同类型的交换机上，实现不同需求消息的发送与接收。

## 1、Fanout 交换机
`Fanout Exchange` 会将接收到的消息路由到每一个与其绑定的`queue`，从而实现一条消息被多个消费者处理，所以也叫 `广播模式`。

Fanout 交换机案例实现：
### 步骤1、在 RabbitMQ 控制台中，创建队列 fanout.queue1 和 fanout.queue2；

### 步骤2、在 RabbitMQ 控制台中，创建交换机 hmall.fanout，将两个队列与其绑定；
交换机的命名通常使用：名称+.+交换机类型。

### 步骤3、在 consumer 服务中，编写两个消费者方法，分别监听 fanout.queue1 和 fanout.queue2；
```java
@Slf4j
@Component
public class SpringRabbitListener {
	 /*
    监听 fanout.queue1 队列的消息
     */
    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg) {
        System.out.println("消费者1接收到消息: "+msg);
    }
    /*
    监听 fanout.queue2 队列的消息
     */
    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg) {
        System.out.println("消费者2接收到消息: "+msg);
    }
}
```
### 步骤4、在 publisher 中编写测试方法，向 hmall.fanout 发送消息；
```java
@Autowired
private RabbitTemplate rabbitTemplate;

/*
测试 fanout exchange；
向 hmall.fanout 交换机发送消息，消息内容为 hello everyone!，会发送到所有绑定到该交换机的队列
*/
@Test
public void testFanoutExchange1(){
    //交换机名字
    String exchangeName = "hmall.fanout";
    //发送内容
    String message = "hello fanout exchange!";
    //发送消息
    rabbitTemplate.convertAndSend(exchangeName, null, message);
}
```

## 2、Direct 交换机
Direct Exchange 会将接收到的消息 根据规则 路由到指定的 Queue，因此称为 `定向路由`。
1. 每一个 Queue 都与 Exchange 绑定时设置一个 BindingKey。每个 Queue 的 BindingKey 可以一样；
2. 发布者发送消息时，指定消息的 RoutingKey；
3. Exchange 将消息路由到 BindingKey 与消息 RoutingKey 一致的队列；

> 如果多个队列具有相同的 RoutingKey，则与 Fanout 功能类似。

Direct 交换机案例实现：

### 步骤1，在 RabbitMQ 控制台中，声明队列 direct.queue1 和 direct.queue2

### 步骤2，在 RabbitMQ 控制台中，声明交换机 hmall.direct，将两个队列与其绑定
* 绑定 ` direct.queue1` 分别使用的 `Routing key` 为 `red、blue`；
* 绑定 ` direct.queue2` 分别使用的 `Routing key` 为 `red、yellow`；

### 步骤3，在 consumer 服务中，编写两个消费者方法，分别监听 direct.queue1 和 direct.queue2
```java
@Slf4j
@Component
public class SpringRabbitListener {
    /*
    监听 direct.queue1 队列的消息
     */
    @RabbitListener(queues = "direct.queue2")
    public void listenDirectQueue1(String msg) {
        System.out.println("消费者1接收到消息: "+msg);
    }

    /*
    监听 direct.queue2 队列的消息
     */
    @RabbitListener(queues = "direct.queue2")  
    public void listenDirectQueue2(String message){
        System.out.println("消费者2接收到消息: "+message);
    }
}
```

### 步骤4，在 publisher 中编写测试方法，利用不同的 RoutingKey 向 hmall.direct 发送消息
```java
/*
测试 direct exchange；
向 hmall.direct 交换机以此测试发送消息，会根据路由key发送到所有绑定到该交换机的队列
 */
@Test
public void testDirectExchange1(){
    //交换机名字
    String exchangeName = "hmall.direct";
    //发送内容
    String message = "--blue--hello direct exchange!";
    //发送消息
    rabbitTemplate.convertAndSend(exchangeName,"blue",message);
    //发送内容
    message = "--red--hello direct exchange!";
    //发送消息
    rabbitTemplate.convertAndSend(exchangeName,"red",message);
    //发送内容
    message = "--yellow--hello direct exchange!";
    //发送消息
    rabbitTemplate.convertAndSend(exchangeName,"yellow",message);
}
```

## 3、Topic 交换机
`TopicExchange` 也是基于 `RoutingKey` 做消息路由，但是 `routingKey` 通常是多个单词的组合，并且以点 `.` 分割。

Queue 与 `TopicExchange` 绑定指定 BindingKey 时可以使用通配符：
1. `#`，代指0个或多个单词；--- 常用
2. `*`，代指一个单词；

> Topic 与 Direct 效果类似都是根据指定的 routingKey 给对应的 queue 发送消息，
> 不同的是 Topic 模式的 BindingKey 可以使用通配符，减少key的绑定，扩展性更强。

Topic 交换机案例实现：

### 步骤1，在 RabbitMQ 控制台中，声明队列 topic.queue1 和 topic.queue2；

### 步骤2，在 RabbitMQ 控制台中，声明交换机 hmall.topic，将两个队列与其绑定
绑定 topic.queue1 使用的key 为 `china.#`，表示 topic.queue1 只监听 `china` 开头的 routingKey 任意消息；
绑定  topic.queue2 使用的key 为 `#.news`，表示 topic.queue2 只监听 `news` 结尾的 routingKey 任意消息；

### 步骤3，在 consumer 服务中，编写两个消费者方法，分别监听 topic.queue1 和 topic.queue2
```java
/*
监听 topic.queue1 队列的消息
 */
@RabbitListener(queues = "topic.queue1")
public void listenTopicQueue1(String message){
    System.out.println("消费者1接收到消息: "+message);
}
/*
监听 topic.queue2 队列的消息
 */
@RabbitListener(queues = "topic.queue2")
public void listenTopicQueue2(String message){
    System.out.println("消费者2接收到消息: "+message);
}
```

### 步骤4，在 publisher 中编写测试方法，利用不同的 RoutingKey 向 hmall.topic 发送消息
```java
 /*
 测试 topic exchange；
 向 hmall.topic 交换机发送消息，路由key为china.news 的消息
  */
 @Test
 public void testTopicExchange1(){
     //交换机名字
     String exchangeName = "hmall.topic";
     
     //发送内容
     String message = "--【key = china.numberOne】--hello topic exchange!";
     //发送消息 routingkey = china.numberOne
     rabbitTemplate.convertAndSend(exchangeName,"china.numberOne",message);
     
     //发送内容
     message = "--【key = china.news】--hello topic exchange!";
     //发送消息 routingkey = china.news
     rabbitTemplate.convertAndSend(exchangeName,"china.news",message);
     
     //发送内容
     message = "--【key = social.news】--hello topic exchange!";
     //发送消息 routingkey = social.news
     rabbitTemplate.convertAndSend(exchangeName,"social.news",message);
 }
```