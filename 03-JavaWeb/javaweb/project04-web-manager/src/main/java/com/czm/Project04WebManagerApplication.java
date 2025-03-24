package com.czm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类，必须位于 package 第1级，才能访问下级中的文件
 */

@SpringBootApplication
public class Project04WebManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project04WebManagerApplication.class, args);
	}

}
