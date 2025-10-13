package com.czm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 的启动类必须位于 package 的顶级，才能访问下级中的文件
 */

@SpringBootApplication
public class Project04WebManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Project04WebManagerApplication.class, args);
    }

}
