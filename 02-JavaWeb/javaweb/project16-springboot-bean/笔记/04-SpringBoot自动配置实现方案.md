# 一、Spring Boot 自动配置的实现方案

自动配置是指在 Spring Boot 项目中引入第三方依赖后，第三方依赖中的 Bean 就自动在当前项目生效了，在项目中可以使用 `@Autowired` 直接使用这些 Bean。
那么 Spring Boot 自动配置的是如何实现、方案有哪些？

- 案例准备：
    1、定义 `project16-company` 的 spring boot 模块，其包名为 `com.company`，该模块内包含：
        1个 `@Component` 注解的类 `TokenParser`;
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

- 测试分析：测试在当前项目直接访问 TokenParser、HeaderParser、HeaderGenerator 的 Bean 对象，代码实现如下：
    ```java
    // 代码实现：`/test/.../TestAutoConfig.java`
    public class TestAutoConfig {
        @Autowired
        private ApplicationContext appContext;
    
        @Autowired
        private TokenParser tokenParser;
    
        @Test
        public void testGetTokenParser() {
            // 如下访问 TokenParser 均报错：org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type...
            System.out.println("--- tokenParser = " + tokenParser);
            TokenParser tokenParser1 = appContext.getBean(TokenParser.class);
            System.out.println("--- tokenParser1 = " + tokenParser1);
        }
        // ...
    }
    ```
    结果：当前项目中均无法访问 TokenParser、HeaderParser、HeaderGenerator 的 Bean 对象

- 解决：Spring Boot 自动配置的实现方案：`@ComponentScan`、`@Import`。


## 1.1、Spring Boot 自动配置 方案1 -  @ComponentScan （不推荐！）

虽然 project16-company 依赖中 TokenParser 使用了 @Component，但是在当前项目的 Spring 环境无法获取 TokenParser 的 bean 对象。
这是因为当前项目启动类启动时默认不会扫描 project16-company 依赖中的 Bean，导致 project16-company 依赖中声明的 Bean 在被使用的项目中无法完成自动装配。

- `@SpringBootApplication` 注解，它包含了 @ComponentScan 注解，具备组件的扫描功能，默认扫描范围是启动类所在包及其子包。
  - 一旦指定了扫描的包，@SpringBootApplication 的默认的扫描包将不生效。

- `@ComponentScan` 注解，作用是启用组件扫描机制，从指定的包路径下开始扫描带有 `@Component` 及其衍生注解的类，并将它们自动注册为 Spring Bean。
    - @ComponentScan 注解是 Spring 框架中用于‌ `自动发现 和 注册Bean` ‌的核心注解。

如下代码所示，修改当前项目的扫描包范围包含第三方依赖的包，这样在当前项目中 自动装配 第三方的依赖中声明的 Bean。
```java
// 修改本地项目扫描包的范围，
// @ComponentScan(basePackages = {"本项目启动类所在的包", "第三方依赖包1", ...})
@ComponentScan(basePackages = {"com.czm", "com.company"})
@SpringBootApplication  
public class Project16SpringbootBeanApplication {
    // ...
}
```

此时，可以访问到第三方依赖中的 `TokenParser` 类型的 `bean` 对象了。 而 `HeaderParser、HeaderGenerator` 普通类还是无法访问，
这需要在第三方依赖中 `project16-company` 中自定义 `@Configuration` 配置类，就可以被访问到了。

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

> @ComponentScan 缺点：项目中通常需要用到许多依赖，如果用此方案，则 使用繁琐、性能低。


## 1.2、Spring Boot 自动配置 方案2 - @Import 导入
- `@Import` 注解，可以使用 @Import 导入的第三方依赖中的类，这些类会被 `Spring` 加载到本地项目 `IOC` 容器中成为 Bean 对象。

`@Import` 注解 导入形式主要有以下几种：

### 1.2.1、导入普通类：
普通类：即可以不需要任何注解（如：@Component、@Configuration 等注解）修饰的类。

```java
@Import({HeaderParser.class, HeaderGenerator.class, TokenParser.class})
@SpringBootApplication
public class Project16SpringbootBeanApplication {
    // ... 
}
```

### 1.2.2、导入配置类：
配置类：即被 @Configuration 注解修饰的类。
```java
/**
 * 在第三方依赖中实现配置类
 */
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
如下代码所示：导入 HeaderConfig 配置类后，此配置类 及其内部 @Bean 注解的方法返回对象，都会自动装配到 IOC 容器中。
```java
// 本地项目中导入第三方依赖的配置类
@Import(HeaderConfig.class)     // 或者多个配置类：@Import({HeaderConfig.class, ...})
@SpringBootApplication
public class Project16SpringbootBeanApplication {
    // ... 
}
```

### 1.2.3、导入 `ImportSelector` 接口实现类 - 批量导入
如下代码所示，在第三方依赖中实现自定义 ImportSelector 接口：
```java
public class MyImportSelector implements ImportSelector {
    /**
     * selectImports 返回的元素为 需要导入到IOC容器内的类全类名 数组，数组中所有元素都会被IOC容器加载
     */
    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[] {"com.company.HeaderConfig"};
    }
}
```

本地项目中导入 ImportSelector 接口实现类，从而实现批量导入：
```java
@Import(MyImportSelector.class)     
@SpringBootApplication
public class Project16SpringbootBeanApplication {
    // ... 
}
```

### 1.2.4、@Import 的优化 - 自定义 `@EnableXxx` 注解 ⚠️
如上 @Import 的三种方式可以将第三方包中的类对象注入到项目的 IOC 容器中，但是这样存在一个问题，就是开发者在使用第三方依赖时，必须明确的知道
第三方包哪些类需要主动配置到 IOC 容器中，这样非常不友好！

对于第三方依赖的提供者来说，是清楚知道在依赖被使用时哪些类需要`自动配置`的，因此通常在第三方依赖中会把 `@Import` 封装到一个 自定义 `@EnableXxx` 注解中。
之后对于使用第三方依赖的开发者，只需要在启动类上声明 `@EnableXxx` 注解，从而完成第三方依赖的 `自动配置`。

1、在第三方依赖中自定注解类，封装 @Import 的注解，声明第三方依赖中需要被自动装配的类
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Import(MyImportSelector.class)
public @interface EnableHeaderConfig {
    
}
```

2、在本地项目中使用第三方自定义注解，使得第三方依赖中的 Bean 生效
```java
@EnableHeaderConfig     
@SpringBootApplication
public class Project16SpringbootBeanApplication {
    // .... 
}
```

> 优点：方便、优雅！ --- 开发中推荐！



# 二、回顾总结
1、为什么第三方依赖中使用 @Component 及其衍生注解声明 bean 不生效？
  基于 @Component 及其衍生注解声明的 bean 要想生效，需要被组件扫描注解扫描。

2、有哪些方案可以使其生效呢?
a. 通过 @ComponentScan 注解扫描指定的包
b. 通过 @Import 注解将其导入到 IOC 容器中(四种常见方式)： 普通类、配置类、ImportSelector实现类、@EnableXxx
