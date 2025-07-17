package com.czm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.czm.mapper")	// 避免每个 Mapper 都需添加 @Mapper 注解
@SpringBootApplication
public class Day01MybatisPlusApplication {

	public static void main(String[] args) {
		SpringApplication.run(Day01MybatisPlusApplication.class, args);
	}

}
