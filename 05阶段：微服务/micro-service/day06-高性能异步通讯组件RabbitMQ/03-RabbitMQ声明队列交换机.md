前面我们创建队列+交换机都是在控制台手动完成的，这样很容易出现错误，并且与运维对接的过程中，很容易出现误差，导致项目无法正常运行。
实际开发中，是基于代码生成队列和交换机，这样就不容易出错。

# 一、基于 Java Bean 的方式声明队列和交换机

`SpringAMQP` 提供了几个类，用来声明队列、交换机及其绑定关系：
* `Queue`：用于声明队列，可以用工厂类 `QueueBuilder` 构建；
* `Exchange`：用于声明交换机，可以用工厂类 `ExchangeBuilder` 构建；
* `Binding`：用于声明队列和交换机的绑定关系，可以用工厂类 BindingBuilder 构建；

如下代码所示，基于 Java Bean 的方式声明 Fanout 类型的交换机，并创建队列与其绑定。
只要声明好 队列与交换机 绑定关系，Spring 应用一启动就会自动创建队列与交换机。

> ⚠️，这个过程的代码一般在 消费者 端写，因为消费者获取消息需要知道的信息比较多。

```java
@Configuration
public class FanoutConfig {

    // 声明交换机
    @Bean
    public FanoutExchange fanoutExchange(){
        // 声明交换机有 2 种方式
//        return new FanoutExchange("hmall.fanout");
        return ExchangeBuilder.fanoutExchange("hmall.fanout").build();
    }

    // 声明队列
    @Bean
    public Queue fanoutQueue1(){
        // 声明队列 也有 2 种方式
//        return new Queue("fanout.queue1");
        return QueueBuilder.durable("fanout.queue1").build();
    }

    // 声明绑定交换机 fanoutExchange 和 队列 fanoutQueue1
    @Bean
    public Binding fanoutQueue1Binding(Queue fanoutQueue1, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue1).to(fanoutExchange);
    }

    //声明队列
    @Bean
    public Queue fanoutQueue2(){
        return new Queue("fanout.queue2");
    }

    // 声明绑定交换机 fanoutExchange 和 队列 fanoutQueue2
    @Bean
    public Binding fanoutQueue2Binding(Queue fanoutQueue2, FanoutExchange fanoutExchange){
        return BindingBuilder.bind(fanoutQueue2).to(fanoutExchange);
    }
}
```

此方式的缺点：声明队列和交换机绑定关系的代码臃肿、复杂。

# 二、基于注解声明队列和交换机
SpringAMQP 还提供了基于 `@RabbitListener` 注解来声明队列和交换机的方式：

```java
@Slf4j
@Component
public class SpringRabbitListener {
    /*
    监听 direct.queue1 队列的消息
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("direct.queue1"),
            exchange = @Exchange(value = "hmall.direct", type = ExchangeTypes.DIRECT),
            key = {"red","blue"}
    ))
    public void listenDirectQueue1(String msg) {
        System.out.println("消费者1接收到消息: "+msg);
    }

    /*
    监听 direct.queue2 队列的消息
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue("direct.queue2"),    // 队列
            exchange = @Exchange(value = "hmall.direct", type = ExchangeTypes.DIRECT),  // 交换机
            key = {"yellow","red"}      // 绑定关系
    ))
//    @RabbitListener(queues = "direct.queue2")
    public void listenDirectQueue2(String message){
        System.out.println("消费者2接收到消息: "+message);
    }
}
```
