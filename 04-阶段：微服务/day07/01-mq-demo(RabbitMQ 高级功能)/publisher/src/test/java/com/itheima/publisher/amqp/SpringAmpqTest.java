package com.itheima.publisher.amqp;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author pm
 * date: 2024/6/11 14:49
 * Description:
 */
@Slf4j
@SpringBootTest
public class SpringAmpqTest {

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


    /*
    测试 work queue；
    向队列发送大量消息，模拟消息堆积
    发送消息，每20毫秒发送一次，相当于每秒发送50条消息
     */
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
        rabbitTemplate.convertAndSend(exchangeName,"",message);
    }


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


    @Test
    public void testConfirmCallback() throws InterruptedException {
        // 0、创建 CorrelationData
        CorrelationData cd = new CorrelationData(UUID.randomUUID().toString());
        cd.getFuture().addCallback(new ListenableFutureCallback<CorrelationData.Confirm>() {

            @Override
            public void onSuccess(CorrelationData.Confirm result) {
                // 判断消息是否发送成功
                if (result.isAck()) {
                    log.debug("收到 ConfirmCallback ack，消息发送成功！");
                } else {
                    // TODO 需要准备重新发送
                    log.error("收到 ConfirmCallback nack，消息发送失败！reason：{}", result.getReason());
                }
            }

            @Override
            public void onFailure(Throwable ex) {
                log.error("spring amqp 处理确认结果异常", ex);
            }
        });

        // 1、交换机名称
        String exchangeName = "hmall.direct";
        // 2、消息
        String message = "测试发送者消息确认机制！";
        // 3、发送消息
        rabbitTemplate.convertAndSend(exchangeName, "blue", message, cd);

        // 因为是单元测试中，延迟等待 cd 回调
        Thread.sleep(20000);
    }

    @Test
    void testSendDelayMessage() {
        rabbitTemplate.convertAndSend("normal.direct", "hi", "hello，你好！", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setExpiration("10000");  // 设置消息过期时间 10秒
                return message;
            }
        });
    }

    // 使用 RabbitMQ 插件发送延迟消息
    @Test
    void testSendDelayMessageByPlugin() {
        // 使用消息后置处理器 MessagePostProcessor 添加消息头
        rabbitTemplate.convertAndSend("delay.direct", "hi", "你好！插件延迟消息", new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().setDelay(10000);  // 发送延迟消息，setDelay 设置消息延迟 10 秒
                return message;
            }
        });
    }
}
