package com.czm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * SpringBoot 的启动类必须位于 package 的顶级，才能访问下级中的文件
 */


@SpringBootApplication
//@ComponentScan({"com.czm.xxx", ""})	// 设置扫描组件，扫描哪些包下面的内容
public class Project05WebIocDiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project05WebIocDiApplication.class, args);
	}

}
