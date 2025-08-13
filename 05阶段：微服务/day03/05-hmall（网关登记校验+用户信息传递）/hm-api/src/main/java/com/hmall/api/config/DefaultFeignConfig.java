package com.hmall.api.config;

import com.hmall.common.utils.UserContext;
import feign.Logger;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.context.annotation.Bean;

public class DefaultFeignConfig {
    /**
     * 自定义 OpenFeign 的日志级别
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 通过定义匿名内部类的方式，添加 OpenFeign 拦截器
     * 用于在 OpenFeign 发送请求时，请求头中添加用户信息
     */
    @Bean
    public RequestInterceptor userInfoRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                // 此处 UserContext 可以拿到 用户信息
                // 因为进入微服务的请求是经过网关过滤器添加了用户信息到 UserContext，在微服务处理业务逻辑时发送 Feign 请求，首先进入 Feign 的拦截器，所以可以从 UserContext 中获取用户信息
                Long userId = UserContext.getUser();
                if (userId != null) {
                    requestTemplate.header("user-info", userId.toString());
                }
            }
        };
    }
}
