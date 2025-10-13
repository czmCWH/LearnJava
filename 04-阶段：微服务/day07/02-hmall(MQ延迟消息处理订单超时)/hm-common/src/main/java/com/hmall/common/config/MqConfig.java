package com.hmall.common.config;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置 RabbitMQ 组件的消息转换器
 */

@Configuration
@ConditionalOnClass(RabbitTemplate.class)       // 添加 Bean 自动装配条件，如果存在 RabbitTemplate 则 MqConfig 配置生效
public class MqConfig {
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}
