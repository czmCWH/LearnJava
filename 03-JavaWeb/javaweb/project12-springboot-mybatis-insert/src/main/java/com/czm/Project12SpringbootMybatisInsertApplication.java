package com.czm;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class Project12SpringbootMybatisInsertApplication {

    public static void main(String[] args) {
        SpringApplication.run(Project12SpringbootMybatisInsertApplication.class, args);
        log.info("------ 项目启动成功！");
    }

}
