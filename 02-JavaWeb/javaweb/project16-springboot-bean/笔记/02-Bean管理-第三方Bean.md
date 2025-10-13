# 一、第三方 Bean
如果要管理的 `bean对象` 来自于第三方框架(不是 Spring框架内 或 开发者自定义的)，是无法用 `@Component` 及衍生注解声明 `bean` 的， 就需要用到 `@Bean` 注解。

案例：把 `dom4j` 框架中的 `SAXReader` 配置为 Bean 对象。

## 方式一 启动类中配置（不推荐）

> `@Bean` 注解只能作用于方法上。

在启动类中添加 Bean 对象，如下代码所示：

```java
@SpringBootApplication
public class Project16SpringbootBeanApplication {
    /**
     * @Bean 注解只能作用于方法上。程序启动时会自动执行此方法，并把方法返回值交给 IOC 容器管理，成为 IOC 容器的 bean 对象。
     */
    @Bean
    public SAXReader getSAXReader() {
        return new SAXReader();
    }

    @Bean  // 将方法返回值交给I0C容器管理,成为I0C容器的bean对象
    public AliyunosSOoperator aliyunosSOperator(AliyunOSSProperties ossProperties) {  // AliyunOSSProperties 必须是一个 Bean
        return new AliyurissOperator(ossProperties);
    }
}

// 在其它任何地方就可以使用 依赖注入 获取第三方 Bean 对象了
@Autowired
private SAXReader reader;
```

> ⚠️⚠️⚠️
> 通过 @Bean 注解的 name 或 value 属性可以声明 bean 的名称，如果不指定，默认第三方 bean 的名称就是方法名；
> 如果第三方 bean 需要依赖其它 bean 对象，直接在 bean 定义方法中设置形参即可，容器会根据类型自动装配；

## 方式二 @Configuration 配置类集中管理第三方 Bean 对象（推荐）
若要管理的第三方 `Bean` 对象，建议对这些 `Bean` 进行集中分类配置，可以通过 `@Configuration` 注解声明一个配置类。

```java
@Configuration  // @Configuration 是 @Component 的衍生注解，用于声明该类是配置类，并交由IOC 容器管理。
public class CommonConfig {
//    @Bean(value = "saxReader")    // 指定 bean 的名字
    @Bean   // @Bean 注解只能作用于方法上。程序启动时会自动执行此方法，并把方法返回值交给 IOC 容器管理，成为 IOC 容器的 bean 对象。
    public SAXReader getSAXReader() {
        return new SAXReader();
    }
}
``` 
代码实现：`com/czm/config/CommonConfig.java`

注意：
1、通过 `@Bean` 注解的 `name` 或 `value` 属性可以声明`bean`的名称，如果不指定，默认 `bean` 的名称就是方法名。
```java
 @Test
public void testBeanName() {
    Object bean1 = applicationContext.getBean("getSAXReader");
    System.out.println("------ 通过名字获取第三方 bean，验证 Bean 的名字 = " + bean1);
}
```
