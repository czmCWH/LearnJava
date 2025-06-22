package com.czm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类，用于启动 SpringBootWeb 程序。必须位于 package 的顶级，才能访问下级中的文件。
 *
 * 启动完毕后，通过 http://localhost:8080/hello 访问项目
 */

@SpringBootApplication
public class Project03SpringbootWebStart01Application {

    public static void main(String[] args) {
        SpringApplication.run(Project03SpringbootWebStart01Application.class, args);
    }

}
