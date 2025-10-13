package com.hmall.api.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * 自定义 OpenFeign 的日志级别
 */

public class DefaultFeignConfig {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
