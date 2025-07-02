package com.company;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类，用于封装需要的 Bean 对象
 */

/**
 * 如果使用了 @Import(HeaderConfig.class) 方式导入，则不需要  @Configuration 注解
 * 如果使用了 @Configuration 注解，则需要通过 @ComponentScan({"com.czm", "com.company"}) 指定扫描包的方式导入
 */

//@Configuration
public class HeaderConfig {

    @Bean
    public HeaderParser headerParser() {
        return new HeaderParser();
    }

    @Bean
    public HeaderGenerator headerGenerator() {
        return new HeaderGenerator();
    }
}