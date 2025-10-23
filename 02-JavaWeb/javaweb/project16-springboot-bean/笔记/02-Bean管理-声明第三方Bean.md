# 一、第三方 Bean 的声明
如果要管理的 `bean对象` 来自于第三方框架(不是来自 Spring框架内 或 开发者自定义的)，是无法用 `@Component` 及衍生注解声明 `bean` 的，就需要用到 `@Bean` 注解。

案例1：在当前项目中定义 AliyunOSSOperator 模拟第三方中的类，实现配置为当前项目 Spring 环境的 Bean 对象。
案例2：把 `dom4j` 框架中的 `SAXReader` 配置为当前项目 Spring 环境的 Bean 对象。

## @Bean 注解 --- 声明第三方 Bean ⚠️⚠️⚠️
@Bean 作用是将 @Bean 注解的方法返回的对象交给 I0C 容器管理，成为 I0C 容器的 bean 对象。
  - @Bean 是方法级别的注解，只能作用于方法上；
  - 如果 @Bean 注解的方法返回的对象依赖其它 bean 对象，可以直接 @Bean 注解的方法中设置形参即可，Spring 容器会根据类型自动装配（即，形参必须是 IOC 容器中的 Bean）；
  - 通过 @Bean 注解的 name 或 value 属性可以声明 bean 的名称，如果不指定，默认第三方 bean 的名称就是方法名；
  - @Bean 注解的方法通常定义在 `@Configuration 类` 或 `@Component 类`；

### 方式1，在启动类中使用 @Bean
```java
@SpringBootApplication
public class Project16SpringbootBeanApplication {
   
    // 把 `dom4j` 框架中的 `SAXReader` 配置为当前项目 Spring 环境的 Bean 对象。
    @Bean
    //    @Bean(value = "saxReader")    // 指定 bean 的名字
    public SAXReader getSAXReader() {
        return new SAXReader();
    }
    
    // 引入第三方 Bean 依赖于其它 bean 对象，直接通过形参设置
    @Bean
    public AliyunOSSOperator aliyunosSOperator(AliyunOSSProperties ossProperties) {  // ⚠️ AliyunOSSProperties 必须是一个 Bean，此处会自动完成依赖注入
        return new AliyunOSSOperator(ossProperties);
    }
}
```

上面代码的配置第三方 Bean 后，就可以在其它任何地方就可以使用 依赖注入 获取第三方（SAXReader、AliyunOSSOperator）的 Bean 对象了。
```java
// 在其它任何地方就可以使用 依赖注入 获取第三方 Bean 对象了
@Autowired
private SAXReader reader;
```


### 方式2，在 @Configuration 配置类中使用 @Bean --- 推荐！
若要管理的第三方 `Bean` 对象，建议对这些 `Bean` 进行集中分类配置，可以通过 `@Configuration` 注解声明一个配置类。

⚠️ @Configuration 是 @Component 的衍生注解，用于声明该类是配置类，并交由IOC 容器管理。

```java
@Configuration
public class CommonConfig {
    @Bean 
    public SAXReader getSAXReader() {
        return new SAXReader();
    }
}
``` 
代码实现：`com/czm/config/CommonConfig.java`



# 二、总结
项目开发中，什么时候使用 `@Component` 声明 bean，什么时候使用 `@Bean` 注解 ?
1. 一般如果是项目中自定义的类，使用 `@component` 及其衍生注解；
2. 如果是引入第三方依赖中的类，使用 `@Bean` 注解；
