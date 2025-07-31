# 一、Work Queues 任务模型
`Work queues`，任务模型。简单来说就是让多个消费者绑定到一个队列，共同消费队列中的消息

> `Work queues` 应用场景：解决消息太多时，加快消息处理的速度，解决消息堆积问题。

如下使用的是2个方法模拟2个消费者，这是没有意义的，因为消耗的同一台机器的资源。
真实开发中只写一个消费者监听方法，部署多个实例形成集群，这样就真正有多个消费者绑定到一个队列上。这就实现了 Work queues 任务模型。

## 1、模拟多个消费者监听同一个队列

* 步骤1、在 RabbitMQ 的控制台创建一个队列，名为 work.queue；

* 步骤2、在 publisher 服务中定义测试方法，发送 50 条消息到 work.queue； 
```java
@Test
public void testWorkQueue() throws InterruptedException {
    //队列名称
    String queueName = "work.queue";
    //发送的内容
    String message = "hello,workQueue";
    //发送信息
    for (int i = 0; i < 50; i++) {
        rabbitTemplate.convertAndSend(queueName,message+i);
        // 发送消息，每20毫秒发送一次，相当于每秒发送50条消息
        Thread.sleep(20);
    }
}
``` 

* 步骤3、在 consumer 服务中定义两个消息监听者，都监听 work.queue 队列；
```java
@Slf4j
@Component
public class SpringRabbitListener {
	abbitListener(queues = "work.queue")
    public void listenWorkQueue1(String msg) {
        System.out.println("消费者111接收到消息: "+ msg + "，" + LocalTime.now());
        // 消费后沉睡 20毫秒；
        try {
            Thread.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @RabbitListener(queues = "work.queue")
    public void listenWorkQueue2(String msg) {
        System.out.println("-----消费者2222接收到消息: "+ msg + "，" + LocalTime.now());
        try {
            // 消息消费后沉睡200毫秒；
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
```
> `Work queues`任务模型总结：
> 
> 1、多个消费者绑定到同一个队列，可以加快消息处理速度；
> 
> 2、同一条消息只会被其中一个消费者处理；
> 
> 3、默认情况下，队列会把消息轮询平均分配给每一个消费者；

## 2、消费者消息推送限制
默认情况下，`RabbitMQ 服务` 会将消息依次轮询投递给绑定在队列上的每一个消费者。但这并没有考虑到消费者是否已经处理完消息，可能出现消息堆积。
因此我们需要消费者段修改 application.yml, 设置 preFetch 值为 1，控制消费者预取消息的数量，处理完一条消息再处理下一条：

```application.yml
spring:
  rabbitmq:
    listener:
      simple:
        prefetch: 1 # 每次只能获取一条消息，处理完成才能获取下一个消息。这样处理消息快的消费者分配的消息越多，处理慢的消费者分配越少。
```

以上代码采用简单的消息收发模型（消息发送者、队列、消息消费者），这与 RabbitMQ 完整消息模型相比缺少 交换机 模块。

# 二、交换机
交换机的作用主要是接收 publisher 发送者 发送的消息，并将消息按照规则路由到与其绑定的队列。

常见交换机的类型有以下三种:
* Fanout：广播
* Direct：定向
* Topic：话题

## 1、Fanout 交换机
`Fanout Exchange` 会将接收到的消息路由到每一个与其绑定的`queue`，所以也叫 `广播模式`。

案例实现：
### 步骤1、在 RabbitMQ 控制台中，声明队列 fanout.queue1 和 fanout.queue2；

### 步骤2、在 RabbitMQ 控制台中，声明交换机 hmall.fanout，将两个队列与其绑定；

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
1. 每一个 Queue 都与 Exchange 绑定时设置一个 BindingKey。多个 Queue 的 BindingKey 可以一样； 
2. 发布者发送消息时，指定消息的 RoutingKey； 
3. Exchange 将消息路由到 BindingKey 与消息 RoutingKey 一致的队列；

> 如果多个队列具有相同的 RoutingKey，则与 Fanout 功能类似。

案例实现：

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
向 hmall.direct 交换机发送消息，会根据路由key发送到所有绑定到该交换机的队列
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
TopicExchange 也是基于 RoutingKey 做消息路由，但是 routingKey 通常是多个单词的组合，并且以 `.` 分割。

Queue 与 Exchange 绑定指定 BindingKey 时可以使用通配符：
1. `#`，代指0个或多个单词； 
2. `*`，代指一个单词；

> Topic 与 Direct 效果类似都是根据指定的 routingKey 给对应的 queue 发送消息，
> 不同的是 Topic 模式的 BindingKey 可以使用通配符，使用更方便，不用绑定很多个 key，扩展性更强。

案例实现：

### 步骤1，在 RabbitMQ 控制台中，声明队列 topic.queue1 和 topic.queue2；

### 步骤2，在 RabbitMQ 控制台中，声明交换机 hmall.topic，将两个队列与其绑定
绑定 topic.queue1 使用的key 为 `china.#`；
绑定  topic.queue2 使用的key 为 `#.news`；

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
     //发送消息
     rabbitTemplate.convertAndSend(exchangeName,"china.numberOne",message);
     //发送内容
     message = "--【key = china.news】--hello topic exchange!";
     //发送消息 
     rabbitTemplate.convertAndSend(exchangeName,"china.news",message);
     //发送内容
     message = "--【key = social.news】--hello topic exchange!";
     //发送消息
     rabbitTemplate.convertAndSend(exchangeName,"social.news",message);
 }
```