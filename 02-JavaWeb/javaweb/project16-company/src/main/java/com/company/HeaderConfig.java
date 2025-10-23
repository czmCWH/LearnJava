package com.company;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类，用于封装需要的 Bean 对象
 */

/**
 * 如果使用了 @Import(HeaderConfig.class) 方式导入，则不需要  @Configuration 注解
 * 如果使用了 @Configuration 注解，则需要通过 @ComponentScan({"com.czm", "com.company"}) 指定扫描包的方式导入
 */

@Configuration
public class HeaderConfig {

    @Bean
//    @ConditionalOnClass(name = "io.jsonwebtoken.Jwts")      // 判断环境中是否有 Jwts 字节码文件，如果有则将 HeaderParser 交给 `I0C` 容器管理
//    @ConditionalOnMissingBean       // 判断环境中是否有 HeaderParser 对应的 Bean，如果没有则创建该类型的 Bean
//    @ConditionalOnProperty(name = "myName", havingValue = "czm")   // 判断环境配置文件(application.properties)中，是否有属性 myName 的值等于 czm，如果有则将 HeaderParser 交给 `I0C` 容器管理
    public HeaderParser headerParser() {
        return new HeaderParser();
    }

    @Bean
    public HeaderGenerator headerGenerator() {
        return new HeaderGenerator();
    }
}