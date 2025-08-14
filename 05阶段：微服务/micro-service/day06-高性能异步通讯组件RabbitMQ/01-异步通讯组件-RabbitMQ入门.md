# 一、通讯方式
## 1、同步通讯
- 同步通讯：实时的通讯，需等待通讯的结果。如视频聊天。如：OpenFeign 远程调用就是同步通讯。
- 优点：时效性强，等待到结果后才返回；
- 问题：
  - 拓展性差；违反项目开发 `开闭原则`：面向修改关闭；面向拓展开发。
  - 性能下降；
  - 级联失败(一个服务崩溃导致雪崩)；

> 分析 hmall 商城余额支付，如果采用采用同步调用出现的问题：`/img/01-同步、异步通讯、MQ/03-同步调用分析.jpg`

## 2、异步通讯
- 异步通讯：非实时的通讯，不用等待通讯的结果。并发能力强，时效性差。如微信聊天。

异步调用通常是基于消息通知的方式，包含三个角色:
1. 消息发送者：投递消息的人，就是原来的 调用者。
2. 消息接收者：接收和处理消息的人，就是原来的 服务提供者。
3. 消息代理(`Broker`)：管理、暂存、转发消息，你可以把它理解成微信服务器。

- 优点： 
  - 解除耦合，拓展性强；解除了边缘业务的耦合。
  - 异步调用，无需等待，总耗时缩短，性能好； 
  - 故障隔离，下游服务故障不影响上游业务； 
  - 缓存消息，流量削峰填谷；
- 问题： 
  - 不能立即得到调用结果，时效性差； 
  - 不确定下游业务执行是否成功 ； 
  - 业务安全依赖于 Broker 的可靠性

> 异步调用分析案例：`/img/01-同步、异步通讯、MQ/04-异步调用分析.jpg`

# 二、异步通讯组件 - RabbitMQ
MQ（MessageQueue），消息队列，即存放消息的队列，指异步调用中的 `Broker`。
异步调用技术选型：`/img/01-同步、异步通讯、MQ/05-MQ技术选型.jpg`。

RabbitMQ 是基于 Erlang 语言开发的开源消息通信中间件，官网<https://www.rabbitmq.com/>。

`RabbitMQ` 的整体架构及核心概念，见图 `/img/02-RabbitMQ架构.jpg`。
* virtual-host：虚拟主机，起到数据隔离的作用；
* publisher：消息发送者；
* consumer：消息的消费者；
* queue：队列，存储消息；
* exchange：交换机，负责路由消息；（如果路由消息失败，消息会丢失）

> ⚠️：由于 RabbitMQ 的性能非常高，因此在企业中多个项目会共用一个 RabbitMQ 服务，为了使它们的 exchange、queue 之间隔离，这就需要使用 `virtual-host`。

## 1、使用 docker 部署 RabbitMQ

```shell
$ docker run \
-e RABBITMQ_DEFAULT_USER=admin \    # 设置环境变量，RabbitMQ 控制台账号密码
-e RABBITMQ_DEFAULT_PASS=admin \
-v mq-plugins:/plugins \ # 挂载数据卷
--name mq \
--hostname mq \
-p 15672:15672 \
-p 5672:5672 \
--network hm-net \  # 加入容器网络
-d \
rabbitmq:3.13.7-management
```

安装完成后，访问 <http://localhost:15672/#/> 控制台，输入账号密码 `admin`

## 2、使用 RabbitMQ 控制台收发消息

### 2.1、在 RabbitMQ 控制台发送消息
- 需求：
  1. 新建队列 hello.queue1 和 hello.queue2； 
  2. 向默认的 amp.fanout 交换机发送一条消息； （RabbitMQ 控制台 Exchanges 选项下默认有许多 exchange）
  3. 查看消息是否到达 hello.queue1 和 hello.queue2；

- 操作实现：`/img/03-RabbitMQ控制台发送消息`

- 总结：
  - 交换机 只负责转发消息，无法存储消息，如果路由消息失败，则消息丢失。
  - 交换机 只会路由消息给与其绑定的队列，因此队列与交换机绑定才能接收消息。

### 2.2、在 RabbitMQ 控制台实现数据隔离
- 需求：
  1. 新建一个用户 hmall；
  2. 为 hmall 用户创建一个 `virtual host`；
  3. 测试不同 virtual host 之间的数据隔离现象；

- 操作实现：`/img/04-RabbitMQ控制台数据隔离`

- 总结：通常我们为不同的项目或业务创建 独立的 RabbitMQ 用户、独立的虚拟主机 来实现数据隔离的效果，解决不同应用之间发送消息。


## 3、RabbitMQ Java 客户端收发消息 - Spring AMQP 快速入门
AMQP（Advanced Message Queuing Prototol） 协议 是用于在应用程序之间传递业务消息的开放标准，该协议与语言和平台无关，更符合微服务中独立性的要求。

Spring AMQP 是基于 AMQP 协议定义的一套 API 规范，提供了模版来发送和接收消息。包含两部分，其中 spirng-amqp 是基础抽象，spring-rabiit 是底层的默认实现。
官网：<https://spring.io/projects/spring-amqp>

> 由于 RabbitMQ 组件支持 AMQP 协议，所以我们可以使用 Spring AMQP 作为 RabbitMQ 客户端。 

### 1、案例需求说明
打开 `/day07/mq-demo` 工程 实现如下需求：
1. 利用 RabbitMQ 控制台创建队列 `simple.queue`；
2. 在 `publisher` 服务中，利用 `SpringAMQP` 直接向 `simple.queue` 发送消息；
3. 在 `consumer` 服务中，利用 `SpringAMQP` 编写消费者，监听 `simple.queue` 队列；

MQ收发消息的完整流程为：`publisher` -> `exchange` -> `queue` -> `consumer`。此处快速入门案例做了简化处理，剔除了交换机过程。

### 2、`mq-demo` 工程中引入 spring-amqp 依赖
在父工程中引入 `spring-amqp` 依赖，这样 `publisher` 和 `consumer` 服务都可以使用：
```pom
<!--AMQP依赖，包含RabbitMQ-->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-amqp</artifactId>
</dependency>
```

### 3、`mq-demo` 工程中配置 RabbitMQ 服务端信息建立连接
在每个微服务中引入 RabbitMQ 服务端信息，这样微服务才能连接到 RabbitMQ。
```application.yml
spring:
  rabbitmq:
    host: localhost   # RabbitMQ 服务器的地址
    port: 5672  # 端口
    virtual-host: /hmall  # RabbitMQ 服务器上创建的虚拟主机名
    username: hmall  # 登录 RabbitMQ 服务器的用户名
    password: 123     # 登录 RabbitMQ 服务器的密码
```

### 4、发送消息
`SpringAMQP` 提供了 `RabbitTemplate` 工具类，方便我们发送消息。实现代码如下：
```java
@Autowired
private RabbitTemplate rabbitTemplate;

@Test
public void testSimpleQueue(){
    //队列名称
    String queueName = "simple.queue";
    //消息
    String message = "hello,spring amqp!";
    //发送消息
    rabbitTemplate.convertAndSend(queueName,message);
}
```

### 5、接收消息
`SpringAMQP` 提供声明式的消息监听，我们只需要通过注解在方法上声明要监听的队列名称，将来 SpringAMQP 就会把消息传递给当前方法。

> ⚠️：发送消息与接收消息的类型保持一致，Spring 会自动把接收的消息做类型转换。

```java
@Slf4j
@Component
public class SpringRabbitListener {

    // @RabbitListener 注解消费者监听 simple.queue 队列
    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue(String msg) {
        System.out.println("SpringRabbitListener ListenSimpleQueueMessage 消费者接收到的消息:" + msg);
    }
}
```