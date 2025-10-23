
SpringBoot 自动配置 源码跟踪见图：`/img/02-SpringBoot自动配置源码跟踪`。

# 一、SpringBoot 自动配置 - @Conditional 有条件的注册 Bean
`@Conditional` 注解：
- 作用：按照一定的条件进行判断，在满足给定条件后才会注册对应的 `bean` 对象到 `Spring IOC` 容器中。
- 使用位置：
  - 方法上：即 @Bean 注解的方法，表示基于这个方法声明的 bean，只有满足条件时才会注册到 IOC 容器；
  - 类上：表示只有在满足条件的情况下，才会将类中所有的方法声明的 bean 注册到 IOC 容器；

`@Conditional` 本身是一个父注解，派生出大量的子注解，常见如下：
  - `@ConditionalOnClass`：判断环境中是否有对应字节码文件，才注册 `bean` 到 `IOC` 容器；
  - `@ConditionalOnMissingBean`：判断环境中没有对应的bean(类型 或 名称)，才注册bean到IOC容器；
  - `@ConditionalOnProperty`：判断配置文件中有对应属性和值，才注册 `bean` 到 `I0C` 容器；

代码实现：`/project16-company/.../HeaderConfig.java`、`/project16-springboot-bean/.../TestAutoConfig.java`

```java 
@Configuration
public class HeaderConfig {

    @Bean
//    @ConditionalOnClass(name = "io.jsonwebtoken.Jwts")      // 判断环境中是否有 Jwts 字节码文件，如果有则将 HeaderParser 交给 `I0C` 容器管理
//    @ConditionalOnMissingBean       // 判断环境中是否有 HeaderParser 对应的 Bean，如果没有则创建该类型的 Bean
//    @ConditionalOnProperty(name = "myName", havingValue = "czm")   // 判断环境配置文件(application.properties)中，是否有属性 myName 的值等于 czm，如果有则将 HeaderParser 交给 `I0C` 容器管理
    public HeaderParser headerParser() {
        return new HeaderParser();
    }
}

@SpringBootTest
public class TestAutoConfig {
    @Autowired
    private ApplicationContext appContext;

    @Test
    public void testGetHeaderParser() {
        HeaderParser headerParser = appContext.getBean(HeaderParser.class);
        System.out.println("--- headerParser ---" + headerParser);
    }
}
```



# 二、如何完成 SpringBoot 自动配置 ⚠️
步骤1、定义自动配置类； 
步骤2、将自动配置类配置在 `org.springframework.boot.autoconfigure.EnableAutoConfiguration.imports` 文件中，该配置文件的存放目录如下：
```
// 在 Spring Boot < 2.7：
META-INF/spring.factories/org.springframework.boot.autoconfigure.EnableAutoConfiguration.imports

// Spring Boot >= 2.7：
META-INF/spring/org.springframework.boot.autoconfigure.EnableAutoConfiguration.imports
```

通过启动类的核心注解 `@EnableAutoConfiguration` 的实现源码 `@Import({AutoConfigurationImportSelector.class})`，可知该配置文件的加载过程如下：
```java
public class AutoConfigurationImportSelector {
  protected List<String> getCandidateConfigurations(AnnotationMetadata metadata, AnnotationAttributes attributes) {
    ImportCandidates importCandidates = ImportCandidates.load(this.autoConfigurationAnnotation, this.getBeanClassLoader());
    List<String> configurations = importCandidates.getCandidates();
    Assert.notEmpty(configurations, "No auto configuration classes found in META-INF/spring/" + this.autoConfigurationAnnotation.getName() + ".imports. If you are using a custom packaging, make sure that file is correct.");
    return configurations;
  }
}
```