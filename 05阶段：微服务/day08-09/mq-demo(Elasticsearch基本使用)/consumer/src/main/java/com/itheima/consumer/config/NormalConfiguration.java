package com.itheima.consumer.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class NormalConfiguration {
    // 声明处理 error 的交换机
    @Bean
    public DirectExchange normalExchange() {
        return new DirectExchange("normal.direct");
    }

    // 声明绑定死信交换机的队列
    @Bean
    public Queue normalQueue() {
//        return new Queue("normal.queue");
        return QueueBuilder
                .durable("normal.queue")
                .deadLetterExchange("dlx.direct")
                .build();
    }

    // 声明队列与交换机的绑定
    @Bean
    public Binding normalExchangeBinding(Queue normalQueue, DirectExchange normalExchange) {
        return BindingBuilder.bind(normalQueue).to(normalExchange).with("hi");
    }
}
