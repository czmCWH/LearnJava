package com.czm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * 引导类（启动类）
 */

@ServletComponentScan   // 由于 Filter 不属于 SpringBoot，所以需要加此注解用于支持 Servlet
@SpringBootApplication
public class Project14SpringbootLoginApplication {

    public static void main(String[] args) {
        SpringApplication.run(Project14SpringbootLoginApplication.class, args);
    }

}
