# 一、Spring IOC 思想

* 控制反转：`Inversion 0f Control`，简称 `IOC`。对象的 创建控制权 由程序自身(即，开发者自己new对象) 转移 到外部(容器，又称 IOC容器、Spring容器)，这种思想称为 控制反转。

* 依赖注入：`Dependency Injection`，简称 `DI`。容器为应用程序提供运行时，所依赖的资源，称之为依赖注入。

* `Bean` 对象：IOC 容器 中创建、管理的对象，称之为 `Bean`。

> `IOC` 是 `Spring` 框架中的第一大核心。

对 JavaWeb 三层架构进行分层解耦的实现思路：
1. 将项目中的 实现类 交给IOC容器管理（IOC，控制反转）
2. 应用程序运行时需要什么对象，直接依赖容器为其提供(DI，依赖注入)

## 1、IOC & DI 的实现

IOC 和 DI 是 通过 `@Component` 和 `@Autowired` 来实现的。

### 步骤1，将 `Dao` 及 `Service`层的实现类，交给 `I0C` 容器管理。

- 给`Dao` 和 `Service` 层的 实现类 添加 `@Component` 注解。
- @Component 注解的作用，用于注解实现类，将类交给 IOC 容器管理，IOC容器负责该类的对象创建；标记类为 Spring 管理的组件（Bean）。

### 步骤2，为 `Controller` 及 `Service` 注入运行时所依赖的对象。

- 在 `Controller` 和 `Service` 中接收 实现类的属性变量 上添加 `@Autowired` 注解；
- @Autowired 注解的作用，可注解于构造器、字段、setter方法或任意形参上，用于自动装配（Autowiring）其他 Bean 到当前类中。Spring容器会尝试找到合适的Bean来注入到被@Autowired注解标注的位置。
  - 注意，`@Autowired` 只能在 `Spring bean` 中声明，即 `@Component` 注解的类中。



# 二、IOC 详解
要把某个对象交给 `IOC` 容器管理，需要在对应的类上加上如下注解之一。`@Component` 的衍生注解作用是便于标注 Bean 对象属于三层架构的那一层，底层做了其它封装。
- `@Component`：声明 `bean` 的基础注解；不属于以下三类时，用此注解。
- `@Controller`：@Component的衍生注解；标注在控制层上。常用 `@RestController`。
- `@Service`：@Component的衍生注解；标注在业务层类上。
- `@Repository`：@Component的衍生注解；标注在数据访问层类上（由于与 mybatis 整合，用的少）

在 SpringBoot 集成 web 开发中，声明控制器bean 只能使用 @Controller!
Spring 项目并不是所有的类都归属于 三层架构中，如一些工具类、配置类，如果这些类需要被 IOC 容器管理，那么就需要使用 @Component 注解。

### 2.1、IOC 容器如何区分每个 Bean ？
在 IOC 容器中每个 Bean 都有自己的名字。如果使用 `@Component` 声明实现类时没有指定名字，则默认为类名首字母小写。

可以指定 Bean 的名字，如下代码所示：
```java
@Service("DeptService")
public class DeptServiceImpl2 implements DeptService {
}
```
> 注意，通常不会为 Bean 指定名字，采用默认值即可。

### 2.2、组件扫描注解 - @ComponentScan
如上声明 Bean 的四大注解，要想生效，还需要被组件扫描注解 `@ComponentScan` 扫描到。
@ComponentScan 注解在项目中没有显示配置，但实际上已经包含在了启动类声明注解 `@SpringBootApplication`中，默认扫描的范围是启动类所在`包及其子包`，
如果超过了此范围声明的 Bean 不会生效。

类被 `@Component` 标注时，当 Spring 启动时，`Spring` 的组件扫描机制（通过 `@ComponentScan`）会自动发现并实例化该类，将其注册为 Spring 容器中的 `Bean`。
`@Autowired` 是一个‌依赖注入注解‌，用于自动装配（`Autowiring`）其他 `Bean` 到当前类中。它通过 `Spring` 的依赖注入机制，解决组件间的依赖关系。



# 三、DI 详解

## 3.1、@Autowired 的3种注入方式
基于 `@Autowired` 进行依赖注入的常见方式有如下三种：

### 方式一：基于属性注入 --- 真实开发常用！
```java
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;
}
```
- 优点：代码简洁、方便快速开发。
- 缺点：隐藏了类之间的依赖关系、可能会破坏类的封装性。

### 方式二：通过构造函数注入(Spring 推荐)
构造函数注入，是指通过构造函数注入程序运行时所需要依赖的资源。
```java
@RestController
public class DeptController {
    // final 修饰保证成员变量不可变，提高代码的安全性
    private final DeptService deptService;

    @Autowired  // 此处 @Autowired 可以省略，因为此处构造函数只有一个。
    public DeptController(DeptService deptService) {
        this.deptService = deptService;
    }
}
```
- 优点：能清晰地看到类的依赖关系、提高了代码的安全性；
- 缺点：代码繁琐、如果构造参数过多，可能会导致构造函数臃肿；
- 注意：如果此方式只有一个构造函数，`@Autowired` 注解可以省略；

### 方式三：通过 setter 注入
```java
@RestController
public class DeptController {
    private DeptService deptService;

    @Autowired
    void setDeptService(DeptService deptService) {
        this.deptService = deptService;
    }
}
```

- 优点：保持了类的封装性，依赖关系更清晰；
- 缺点：需要额外编写 `setter` 方法，增加了代码量。


## 3.2、@Autowired 使用注意事项
`@Autowired` 注解，默认是按照类型进行注入的。
如果存在多个相同类型的 `bean` (因为面向接口开发，使用接口来定义类型，而接口的实现类会有多个，因此造成报错)，将会启动报错如下所示:

```
Description:

Field deptService in com.czm.controller.DeptController required a single bean, but 2 were found:
	- deptServiceImpl: defined in file [/service/DeptServiceImpl.class]
	- DeptServiceImpl2: defined in file [service/DeptServiceImpl2.class]

This may be due to missing parameter name information

// 解决办法
Action:

Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed
```
使用 @Autowired 存在多个实现类报错时，有如下3种解决方案：

### 方案一：@Primary

```java
@Primary    // 指定该 Bean 的优先级最高，从而优先注入此对象
@Service()    
public class DeptServiceImpl2 implements DeptService {

}
```

### 方案二：@Qualifier
@Autowired 默认安装类型注入，@Qualifier默认按照指定的 Bean 名称（即，类型名称首字母小写）注入。

```java
@RestControllerpublic 
class DeptController {
    @Autowired
    @Qualifier("deptServiceImpl2")  // 根据 bean 的名字注入(有默认值，或者自定义设置)。注意 Bean 的名字默认为首字母小写。
    private DeptService deptService;
}
```

### 方案三：@Resource，它是 JavaEE 规范注解
@Autowired 是 Spring 框架提供的注解，而 @Resource 是 JavaEE 规范提供的。

```java
@RestControllerpublic 
class DeptController {
    @Resource(name = "DeptServiceImpl2")    // 等价于  @Autowired + @Qualifier
    private DeptService deptService;
}
```


学习视频：<https://www.bilibili.com/video/BV1yGydYEE3H?spm_id_from=333.788.player.switch&vd_source=f97692c2f656607aeb97ee92b4310d9e&p=47>
