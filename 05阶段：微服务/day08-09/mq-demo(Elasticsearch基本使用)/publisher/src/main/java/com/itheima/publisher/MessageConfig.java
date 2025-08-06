package com.itheima.publisher;


import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息转换器
 */
@Configuration
public class MessageConfig {

    @Bean
    public MessageConverter messageConverter() {
        Jackson2JsonMessageConverter messageConverter = new Jackson2JsonMessageConverter();
        messageConverter.setCreateMessageIds(true);
        return messageConverter;
    }
}
