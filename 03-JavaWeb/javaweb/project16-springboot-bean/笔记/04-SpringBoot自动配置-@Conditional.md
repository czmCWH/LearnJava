# 一、自动配置 - @Conditional
`@Conditional` 注解：
- 作用：按照一定的条件进行判断，在满足给定条件后才会注册对应的 `bean` 对象到 `Spring IOC` 容器中。
- 使用位置：
  - 方法上：针对于当前这个方法声明的 bean；
  - 类上：针对于这个类中所有的方法声明的 bean；
- `@Conditional` 本身是一个父注解，派生出大量的子注解：
  - `@ConditionalOnClass`：判断环境中是否有对应字节码文件，才注册 `bean` 到 `IOC` 容器；
  - `@ConditionalonMissingBean`：判断环境中没有对应的bean(类型 或 名称)，才注册bean到IOC容器；
  - `@ConditionalonProperty`：判断配置文件中有对应属性和值，才注册 `bean` 到 `I0C` 容器；

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

# 二、如何完成 SpringBoot 自动配置
1. 定义自动配置类； 
2. 将自动配置类配置在 `META-INF/spring/" + this.autoConfigurationAnnotation.getName() + ".imports` 文件中


