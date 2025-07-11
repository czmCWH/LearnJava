package com.czm.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * 配置类，配置 Redis 为 第三方 Bean

 * 当前配置类不是必须的，因为 Spring Boot 框架会自动装配 RedisTemplate 对象，但是默认的key序列化器为
 * JdkSerializationRedisSerializer，导致我们存到Redis中后的数据和原始数据有差别，故设置为 StringRedisSerializer序列化器。
 */

@Slf4j
@Configuration
public class RedisConfiguration {
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("--- 开始创建 redis 模版类");
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        // 设置 key 的序列化器，默认为 JdkSerializationRedisSerializer 会导致 Redis 图形化工具内查看乱码
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        // ⚠️ 不推荐修改 value 的序列化器，因为会导致存储不同类型数据时无法自动类型转换
        // 通过 Redis 的工厂创建对象
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }
}
