# 一、Spring Boot 起步依赖
Spring Framework 是一个底层核心的框架，如果基于它进行项目的开发就会比较繁琐，即 项目依赖引入繁琐、项目配置繁琐。
例如：使用 `Spring Framework` 开发web 应用，需要的依赖配置如图：`img/01-基于SpringFramework开发的web程序依赖配置`

`Spring Boot` 是 `Spring Framework` 推出的框架，做了很多封装，可以简单、快捷的构建一个 Spring 应用。
Spring Boot 中提供了2大功能：`起步依赖`、`自动配置`。

Spring Boot 起步依赖是指 Spring Boot 内置了许多 `starter`，通过‌约定优于配置‌的理念，不需要写相关的配置，极大地简化了项目依赖管理和构建配置过程。
Spring Boot 起步依赖原理就是 Maven 的依赖传递。

- `Spring Boot`中，为什么引入了起步依赖，其他相关依赖都有了？
起步依赖的原理是 Maven 的依赖传递特性。只要项目中引入了起步依赖，其它项目所需要的依赖就都引入了。


# 二、Spring Boot 的自动配置
问题：`IOC` 容器内的 Bean 为什么能通过 `@Autowired` 注解直接获取使用？

`SpringBoot` 的自动配置就是当 `spring` 容器启动后，一些配置类、bean对象就自动存入到了 `IOC` 容器中，
不需要开发者手动去声明，从而简化了开发，省去了繁琐的配置操作。

在 IDEA 中查看 bean：`笔记/img/02-IDEA中查看Bean`

```xml
<!-- 监控依赖，用于查看 IOC 容器内的 Bean 对象 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```


学习视频：<https://www.bilibili.com/video/BV1yGydYEE3H/?spm_id_from=333.788.player.switch&vd_source=f97692c2f656607aeb97ee92b4310d9e&p=145>