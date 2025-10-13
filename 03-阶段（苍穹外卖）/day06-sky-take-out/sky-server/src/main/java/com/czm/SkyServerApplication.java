package com.czm;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
//@MapperScan("com.czm.mapper")   // 指定扫描 Mapper，这样就不需要写 @Mapper 注解
@EnableTransactionManagement //开启注解方式的事务管理
@SpringBootApplication
public class SkyServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SkyServerApplication.class, args);
    }

}
