package com.itheima.publisher.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class MqConfig {
    private final RabbitTemplate rabbitTemplate;

    // RabbitTemplate 调用完毕后，就会执行 init 初始化函数
    @PostConstruct
    public void init() {
        rabbitTemplate.setReturnsCallback(returnedMessage -> {
            log.error("监听到了消息 return callback");
            log.debug("交换机: {}", returnedMessage.getExchange());
            log.debug("路由 routingKey: {}", returnedMessage.getRoutingKey());
            log.debug("消息 Message: {}", returnedMessage.getMessage());
            log.debug("replyCode: {}", returnedMessage.getReplyCode());
            log.debug("replyText: {}", returnedMessage.getReplyText());
        });
    }
}
