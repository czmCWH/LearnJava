# Spring
Spring 官网：<https://spring.io/>
Spring 是 Java 最流行的框架（类似Vue）。

Spring发展到今天已经形成了一种开发生态圈，Spring提供了若干个子项目（又称：Spring全家桶），每个项目用于完成特定的功能。

Spring 的子项目都基于 Spring Framework。由于 Spring Framework 使用配置繁琐、入门难度大，所以推出了许多子项目。这些子项目简化配置、能快速开发---约定大于配置。


## Spring Boot
Spring Boot 可以帮助我们非常快速的构建应用程序、简化开发、提高工作效率。
从 Spring Boot 开始，贯穿始终。


### SpringBootWeb 入门程序
1、需求：基于 SpringBoot 开发一个 web应用，浏览器发起请求 /hello 之后，给浏览器返回一个字符串"Hello World ~"

2、实现步骤：
创建 SpringBoot 工程，并勾选web开发相关依赖。
定义 HelloController类，添加方法 hello，并添加注解。
运行项目并测试。


## 创建 SpringBootWeb 程序的方式

方式一、Spring 初始化器方式
直接通过 IDEA 创建模块，选择【Spring Boot】方式创建
创建项目注意点：
	1、Package name，直接用 Group 名称，这样简短一点。
	2、Java 的版本：17、21、24，最低只有17。
	3、Packaging：选择Jar。


方式二、通过 Maven 创建Java项目

### 修改端口
选中模块 > src > main > resources > application.properties，添加如下内容
```properties
spring.application.name=project03-springboot-web-start01

#设置端口
#server.port=9999
```

## SpringBootWeb 程序解析

1、为什么一个 main 方法就将 web应用启动了?
因为创建的 SpringBoot 项目添加了起步依赖:
	spring-boot-starter-web：包含了web应用开发所需要的常见依赖，如：tomcat。
	spring-boot-starter-test：包含了单元测试所需要的常见依赖。
官方提供的starter：


2、Tomcat是一个Servlet容器，为什么可以运行我们编写的 HelloController?

由于在 SpringBoot 进行 web 程序开发时，底层提供了一个非常核心的 DispatcherServlet，请求到达 DispatchServlet 之后，会
根据请求路径，将请求转给我们定义的 Controller 程序，最终由 Controller 程序进行逻辑的处理。




