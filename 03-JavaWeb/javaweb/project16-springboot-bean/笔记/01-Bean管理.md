# 一、手动获取 Bean 对象

默认情况下，Spring 项目启动时，会把 bean 都创建好放在 IOC 容器中。如果要获取到容器对象，可以通过如下依赖注入的方式：

```java
@Autowired
private ApplicationContext applicationContext;
```

`ApplicationContext`(容器对象) 也是一个 Bean 对象。程序启动时，容器对象优先创建好，然后创建其它 Bean 对象。

如果想要主动获取这些 bean，可以通过如下方式：

1、根据 name 获取 bean：`Object getBean(String name)`

2、根据类型获取 bean：`<T> T getBean(Class<T> requiredType)`

3、根据 name 获取 bean(带类型转换)：`<T> T getBean(String name, Class<T> requiredType)`

代码实现：`src/test/.../Project16SpringbootBeanApplicationTests.java` > `void testGetBean()`

# 二、Bean 作用域
`Spring` 支持五种作用域，后三种在 web 环境才生效：

* `singleton`：容器内 同名称 的 bean 只有一个实例(即单例) (默认采用此作用域)

* `prototype`：每次使用该 bean 时会创建新的实例(非单例)

* `request`：每个请求范围内会创建新的实例(web环境中，了解)

* `session`：每个会话范围内会创建新的实例(web环境中，了解)

* `application`：每个应用范围内会创建新的实例(web环境中，了解)

如下代码所示，修改 Bean 作用域（一般不会修改）：

```java
@Scope("prototype")    // @Scope 注解用于修改 bean 对象作用域
@RestController
public class DeptController {
    
}
```
代码实现：`src/test/.../Project16SpringbootBeanApplicationTests.java` > ` void testScope()`

> 默认 `singleton` 的 `bean`，在容器启动时被创建，这样会影响项目启动时间，可以使用 `@Lazy` 注解来延迟初始化(延迟到第一次使用时)。
> 
> prototype 的 bean，每一次使用该 bean 的时候都会创建一个新的实例。
> 
> 实际开发当中，绝大部分的Bean是 单例 的，也就是说绝大部分 Bean 不需要配置 scope 属性。

## 循环依赖
1、如下代码所示，在 ServiceA 和 ServiceB 进行相互依赖注入，造成循环依赖，程序启动报错：

```java
@Service
public class ServiceA {
    @Autowired
    private ServiceB serviceB;

    public void addA() {
        System.out.println("------ addA------");
    }

    public void addB() {
        serviceB.addB();
    }
}

@Service
public class ServiceB {
    @Autowired
    private ServiceA serviceA;

    public void addA() {
        serviceA.addA();
    }

    public void addB() {
        System.out.println("------ addB------");
    }
}
```

2、解决循环依赖方式1，配置 `application.properties`

```properties
#spring.main.allow-circular-references=true
```

3、解决循环依赖方式2，在 ServiceA 和 ServiceB 类中任意一方依赖注入添加 `@Lazy` 注解：
```java

@Service
public class ServiceA {
    @Lazy   // 解决循环依赖的问题，方式2
    @Autowired
    private ServiceB serviceB;
    //.....
}
```

如上解决循环依赖问题的底层原理依然是：`延迟加载`。

产生循环依赖的原因是：

    程序启动时，会扫描到 `@Service` 注解此时会自动加载 `ServiceA` 实例化。
    而进行 `ServiceA` 初始化时，检查到 `@Autowired` 进行依赖注入，进而初始化 `ServiceB`。
    此时，`ServiceB` 会执行和 `ServiceA` 一样的操作，造成 `ServiceB` 和 `ServiceA` 循环等待、死锁！
    加了 `@Lazy` 后，`ServiceA` 实例化时，会延迟加载 `ServiceB`，这样就不用相互等待，解决了循环依赖。


# 三、第三方 Bean
* 如果要管理的 `bean对象` 来自于第三方框架(不是 Spring框架内 或 开发者自定义的)，是无法用 `@Component` 及衍生注解声明 `bean` 的，就需要用到 `@Bean` 注解。
* 若要管理的第三方 bean 对象，建议对这些 bean 进行集中分类配置，可以通过 `@Configuration` 注解声明一个配置类。

如下案例：把 `dom4j` 框架中的 `SAXReader` 配置为 Bean 对象。
代码实现：`src/test/.../Project16SpringbootBeanApplicationTests.java` > `void testThirdBean()`

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
}

// 在其它任何地方就可以使用 依赖注入 获取第三方 Bean 对象了
@Autowired
private SAXReader reader;
```

## 方式二 @Configuration 配置类（推荐）

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

2、如果第三方 `bean` 需要依赖其它 `bean` 对象，直接在 `bean` 定义方法中设置形参即可，容器会根据类型自动装配。
