# 一、Bean 的作用域
Bean，是指 Spring IOC 容器中管理的对象，称为 Bean 对象。
Bean 的作用域，是指 Spring IOC 容器中管理 Bean 对象的作用范围。

Spring 支持五种作用域，后三种在 web 环境才生效：
- `singleton`：IOC 容器内 同名称 的 bean 只有一个实例(即单例) --- 默认！

- `prototype`：每次使用该 bean 时会创建新的实例(非单例)

- `request`：每个请求范围内会创建新的实例(web环境中，了解)

- `session`：每个会话范围内会创建新的实例(web环境中，了解)

- `application`：每个应用范围内会创建新的实例(web环境中，了解)


`@Scope` 注解，用于修改 Bean 作用域（一般不会修改）：

```java
@Scope("prototype")    // @Scope 注解用于修改 bean 对象作用域
@RequestMapping("/depts")
@RestController
public class DeptController {

}
```

# 二、设置、操作 Bean 对象
`ApplicationContext`(容器对象) 也是一个 Bean 对象。程序启动时，容器对象优先创建好，然后创建其它 Bean 对象。
可以使用 `ApplicationContext` 通过如下3种方式获取 bean 对象：

```java
@Autowired
private ApplicationContext applicationContext;

@Test
void testGetBean() {
    // 方式1、根据 bean 的名称获取
    DeptController bean1 = (DeptController) applicationContext.getBean("deptController");
    System.out.println("------ bean1 = " + bean1);

    // 方式2、根据 bean 的类型获取
    DeptController bean2 = applicationContext.getBean(DeptController.class);
    System.out.println("------ bean2 = " + bean2);

    // 方式3、根据 bean 的名称 及 类型获取
    DeptController bean3 = applicationContext.getBean("deptController", DeptController.class);
    System.out.println("------ bean3 = " + bean2);
}
```

> 代码实现：`src/test/.../Project16SpringbootBeanApplicationTests.java` > `void testGetBean()`

## 2.1、验证默认声明的 Bean 是否是 单例的
代码实现：`src/test/.../Project16SpringbootBeanApplicationTests.java` > ` void testScope()`


## 2.2、@Lazy 延迟 默认 Bean 的创建
- 默认 singleton Bean 对象什么时间被创建的？
    默认情况下，Spring 项目启动时，会把默认的 singleton bean 都创建好存入 IOC 容器中。

- `@Lazy` 注解
    由于默认 singleton Bean 的创建会影响项目启动时间，可以使用 `@Lazy` 注解来延迟初始化，延迟到第一次使用的时候再来创建 bean。
    特点：节约资源，提升性能。  
    代码实现：
    ```java
    @Lazy   // 延迟加载
    @RestController
    public class DeptController {
        
    }
    ```

## 2.3、bean 的作用域
### 单例的 bean （singleton）
- 如果声明的 Bean 为无状态的 Bean，则可以声明为 `singleton`。
  - 无状态是指对象中是否保存数据，如果对象中保存数据则是有状态的 Bean ，否则是无状态的 Bean。
- singleton bean 中不会保存状态信息，不存在数据共享，所以是线程安全的。 
- 实际开发当中，绝大部分的 `Bean` 是 单例 的，也就是说绝大部分 Bean 不需要配置 @Scope。

### 多例的 bean（prototype）
prototype 的 bean，每一次使用该 bean 的时候都会创建一个新的实例。
- 有状态对象 可声明为 prototype 的 Bean。 
- prototype bean 内部会保存状态信息，多线程同时操作该bean时，可能会出现数据不一致的问题，这样的 bean 是线程不安全的。






# 三、循环依赖
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



<https://www.bilibili.com/video/BV1m84y1w7Tb?spm_id_from=333.788.videopod.episodes&vd_source=f97692c2f656607aeb97ee92b4310d9e&p=184>
<https://www.bilibili.com/video/BV1yGydYEE3H?spm_id_from=333.788.player.switch&vd_source=f97692c2f656607aeb97ee92b4310d9e&p=142>