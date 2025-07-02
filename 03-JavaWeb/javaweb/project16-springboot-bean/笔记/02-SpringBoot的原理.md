# 一、Spring Boot 起步依赖

Spring Framework 是一个底层核心的框架，而日常开发中不直接使用它，因为使用它项目中需要对依赖各种繁琐的配置。
例如：使用 `Spring Framework` 开发web 应用，需要的依赖配置如图：`img/01-基于SpringFramework开发的web程序依赖配置`

`Spring Boot` 是 `Spring Framework` 推出的框架，做了很多封装。约定大于配置，很多东西都约定好了，而不需要写相关的配置，这些是基于 `Spring Boot` 的起步依赖。

问：`Spring Boot`中，为什么引入了起步依赖，其他相关依赖都有了？
答：起步依赖的原理是 Maven 的依赖传递特性！

# 二、Spring Boot 的自动配置

问题：`IOC` 容器内的 Bean 为什么能通过 `@Autowired` 注解直接获取使用？

`SpringBoot` 的自动配置就是当 `spring` 容器 启动后，一些配置类、bean对象就自动存入到了 `IOC` 容器中，
不需要我们手动去声明，从而简化了开发，省去了繁琐的配置操作。

在 IDEA 中查看 bean：`笔记/img/02-IDEA中查看Bean`

```xml
<!-- 监控依赖，用于查看 IOC 容器内的 Bean 对象 -->
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

# 三、Spring Boot 自动配置的实现
1、案例准备：
定义一个 `project16-company` 的 spring boot 模块，其包名为 `com.company`；
该模块内包含：
    1个 @Component 注解的类 `TokenParser`;
    2个普通类：`HeaderParser、HeaderGenerator`;

2、如下在本项目中引入 `project16-company` 依赖，并分析 bean 对象。

```xml
<!-- 引入自定义模块 -->
<dependency>
    <groupId>com.company</groupId>
    <artifactId>project16-company</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

3、代码实现：`/test/.../TestAutoConfig.java`

4、问题：如何在项目中访问 `project16-company` 第三方依赖中的 bean，如：`TokenParser、HeaderParser、HeaderGenerator`

## 1、方案一 使用 @ComponentScan 注解指定扫描的包
使用了 `@ComponentScan` 后，会打破启动类默认的扫描路径（即启动类所在的包或者子包），因此使用 `@ComponentScan` 时也要添加启动类所在的包。

```java
@ComponentScan({"com.czm", "com.company"})
@SpringBootApplication
public class Project16SpringbootBeanApplication {
    // ...
}
```

此时，可以访问到 `TokenParser` 类型的 `bean` 对象了。 而 `HeaderParser、HeaderGenerator` 还是无法访问，
这需要在 `project16-company` 定义 `@Configuration` 配置类，就可以被访问到了。
```java
@Configuration
public class HeaderConfig {
    @Bean
    public HeaderParser headerParser() {
        return new HeaderParser();
    }
    @Bean
    public HeaderGenerator headerGenerator() {
        return new HeaderGenerator();
    }
}
```

> 方案一缺点：项目中通常需要用到许多依赖，如果用此方案，则使用繁琐、性能低。

## 2、方案二 @Import 导入
使用 `@Import` 导入的类会被 `Spring` 加载到 `IOC` 容器中，导入形式主要有以下几种：

### 2.1、导入普通类：

```java
@Import({HeaderParser.class, HeaderGenerator.class, TokenParser.class})
@SpringBootApplication
public class Project16SpringbootBeanApplication {
    // ... 
}
```


### 2.2、导入配置类：

注意：可以不使用 `@Configuration` 注解配置类

```java
public class HeaderConfig {
    @Bean
    public HeaderParser headerParser() {
        return new HeaderParser();
    }

    @Bean
    public HeaderGenerator headerGenerator() {
        return new HeaderGenerator();
    }
}
```

```java
@Import(HeaderConfig.class)     // 或者多个配置类：@Import({HeaderConfig.class, ...})
@SpringBootApplication
public class Project16SpringbootBeanApplication {
    // ... 
}
```

### 2.3、导入 ImportSelector 接口实现类
```java
@Import(MyImportSelector.class)     // 导入接口实现类
@SpringBootApplication
public class Project16SpringbootBeanApplication {
    // ... 
}
```

### 2.4、使用 自定义 @EnableXxx 注解 来封装 @Import 注解（推荐）
```java
/**
 * 自定注解类
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyImportSelector.class)
public @interface EnableHeaderConfig {
}
```

```java
@EnableHeaderConfig     // 使用封装 @Import 注解
@SpringBootApplication
public class Project16SpringbootBeanApplication {
    // .... 
}
```