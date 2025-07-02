package com.czm.config;

import org.dom4j.io.SAXReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类 - 用于管理第三方 Bean 对象
 */

@Configuration  // @Configuration 是 @Component 的衍生注解，用于声明该类是配置类，并交由IOC 容器管理。
public class CommonConfig {
//    @Bean(value = "saxReader")    // 指定 bean 的名字
    @Bean   // @Bean 注解只能作用于方法上。程序启动时会自动执行此方法，并把方法返回值交给 IOC 容器管理，成为 IOC 容器的 bean 对象。
    public SAXReader getSAXReader() {   // 第三方 Bean 对象的名字默认为 此方法的名字
        return new SAXReader();
    }
}
