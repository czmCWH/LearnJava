package com.czm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类，用于启动 SpringBootWeb 程序
 *
 * 启动完毕后，通过 http://localhost:8080/hello 访问项目
 */

@SpringBootApplication
public class Project03SpringbootWebStart01Application {

    public static void main(String[] args) {
        SpringApplication.run(Project03SpringbootWebStart01Application.class, args);
    }

}
