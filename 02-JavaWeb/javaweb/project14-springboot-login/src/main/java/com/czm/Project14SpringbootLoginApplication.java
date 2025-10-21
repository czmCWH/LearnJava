package com.czm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 引导类（启动类）
 */
// 由于 Filter 是属于传统 JavaWeb 的组件，而在 SpringBoot 项目中使用传统 JavaWeb 开发中的组件时，
// 就必须使用 @SpringBootApplication 注解开启对 Servlet 组件的支持。如果不加此注解，则过滤器不执行。
@ServletComponentScan
@SpringBootApplication
public class Project14SpringbootLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(Project14SpringbootLoginApplication.class, args);
    }

}
