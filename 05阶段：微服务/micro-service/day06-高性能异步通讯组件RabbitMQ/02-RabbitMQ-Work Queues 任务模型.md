# 一、Work Queues 任务模型
`Work queues`，任务模型。简单来说就是让多个消费者（即多个微服务）绑定到一个队列，共同消费队列中的消息。

> `Work queues` 应用场景：解决消息太多时，加快消息处理的速度，解决消息堆积问题。

如下使用的是2个方法模拟2个消费者，这是没有意义的，因为消耗的同一台机器的资源。
真实开发中，微服务中监听队列只写一个监听方法，微服务使用多个实例部署形成集群，这样就真正有多个消费者绑定到一个队列上。这就实现了 Work queues 任务模型。

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

## 2、消费者消息推送限制
默认情况下，`RabbitMQ 服务` 会将消息依次轮询投递给绑定在队列上的每一个消费者，均匀分配给每个消费者。但这并没有考虑到消费者是否已经处理完消息，可能出现消息堆积。
因此我们需要消费者段修改 application.yml, 设置 preFetch 值为 1，控制消费者预取消息的数量，使得其处理完一条消息再处理下一条：

```application.yml
spring:
  rabbitmq:
    listener:
      simple:
        prefetch: 1 # 每次只能获取一条消息，处理完成才能获取下一个消息。这样处理消息快的消费者分配的消息越多，处理慢的消费者分配越少。
```

以上代码采用简单的消息收发模型（消息发送者、队列、消息消费者），这与 RabbitMQ 完整消息模型相比缺少 交换机 模块。


- `Work queues`任务模型总结：
    1. 多个消费者绑定到同一个队列，可以加快消息处理速度；
    2. 同一条消息只会被其中一个消费者处理；
    3. 默认情况下，队列会把消息轮询平均分配给每一个消费者；可通过设置 prefetch 来控制消费者预取的消息数量，实现能者多处理。
