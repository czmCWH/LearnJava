package com.czm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBootWeb 入门，应用创建方式二（基于 Maven 创建）
 *  1、创建一个 Maven 项目
 *  2、pom文件中，继承springboot父工程，添加web依赖
 *  3、创建启动类，类上添加注解 @SpringBootApplication，在 main 方法里运行 SpringApplication.run
 *  4、定义请求处理类，需要在类上添加注解@RestController，方法加注解@RequestMapping("uri")
 *  5、运行启动类并测试

 * SpringBoot 启动类
 */
@SpringBootApplication
public class StartBoot {
    public static void main(String[] args) {
        SpringApplication.run(StartBoot.class, args);
        System.out.println("--- hello world ---");
    }
}
