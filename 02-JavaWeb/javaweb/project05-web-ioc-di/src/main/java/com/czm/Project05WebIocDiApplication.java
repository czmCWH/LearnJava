package com.czm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * SpringBoot 的启动类必须位于 package 的顶级，才能访问下级中的文件。
 * 如果不在同一级，可以通过 @ComponentScan 修改。
 */


@SpringBootApplication
//@ComponentScan({"com.czm.xxx", "指定被扫描的包名"})	// 设置扫描组件，扫描哪些包下面的内容
public class Project05WebIocDiApplication {

	public static void main(String[] args) {
		SpringApplication.run(Project05WebIocDiApplication.class, args);
	}

}
