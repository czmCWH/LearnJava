# 一、JavaWeb 三层架构
1. Controller：控制层，接收前端发送的请求，对请求进行处理，并响应数据。即：接收请求、响应数据。 
2. Service：业务逻辑层，处理具体的业务逻辑。即：数据封装、逻辑处理等。（根据业务会比较复杂） 
3. Dao：数据访问层(Data Access 0bject)(也叫 持久层)，负责数据访问操作，包括数据的增、删、改、查。即：数据访问。

> JavaWeb 三层架构优点：
>    遵循单一职责原则，提高代码复用性，便于开发维护。

# 二、面向接口编程

虽然 遵循了 `JavaWeb 三层架构` 开发，代码复用性提高了，但是扩展性不强。
如果存在新的业务时，`Service` 层将会有多种实现，需要对 `Service` 层原本的类上进行修改，这样旧的逻辑也将不会存在了。**代码扩展性不强！**

* 面向接口编程，先写一个接口制定规范，再根据不同的业务写多个实现类，并以多态的方式使用实现类。
* 面向接口编程 是遵守 **开放封闭原则** 的，即：修改是关闭的，新增是开放的。
* 优点：增强程序的扩展性。

## 分层解耦
虽然 三层架构+面向接口编程 增强了程序的复用性、扩展性，但是存在 每次新业务调整时，会产生新的 实现类。 而使用原本实现类的的地方采用了多态接收，
也必须进行修改，这样各层之间依赖性强，存在强相关（即：耦合），如下代码所示页面调整时，需在各层修改实现类 （`DeptServiceImpl、DeptDaoImpl`）：

```java
private DeptService deptService = new DeptServiceImpl();

private DeptDao deptDao = new DeptDaoImpl();
```

### 三层架构+面向接口编程 如何解耦合 ？？？

* 耦合：衡量软件中各个层/模块之间的依赖、关联的程度。依赖关联程度越高，即耦合度越高！
* 内聚：软件中各个功能模块内部的功能的联系。
* 软件设计原则：高内聚低耦合。

> 解耦合解决办法：Spring IOC 思想

# 三、Spring IOC 思想

* 控制反转：`Inversion 0f Control`，简称 `IOC`。程序员不自己new对象，对象的创建控制权由程序自身移到外部(容器)，这种思想称为控制反转。又称 **IOC容器、Spring容器。**

* 依赖注入：`Dependency Injection`，简称 `DI`。容器为应用程序提供运行时，所依赖的资源，称之为依赖注入。

* `Bean 对象`：`IOC` 容器 中创建、管理的对象，称之为 **Bean**。

> `IOC` 是 `Spring` 框架中的第一大核心。

实现分层解耦的思路：
1. 将项目中的类交给IOC容器管理（IOC，控制反转）
2. 应用程序运行时需要什么对象，直接依赖容器为其提供(DI，依赖注入)

## 1、分层解耦实现 - IOC 和 DI

### 步骤1，将 `Dao` 及 `Service`层的实现类，交给 `I0C` 容器管理。

- 使用 `@Component` 注解实现类，标记一个类为 Spring 管理的组件（Bean）。将类交给 IOC 容器管理，IOC容器负责该类的对象创建； 
- 添加 `@Component` 注解到 `Dao` 和 `Service` 层的实现类。

### 步骤2，为 `Controller` 及 `Service` 注入运行时所依赖的对象。

- `@Autowired` 可注解于构造器、字段、setter方法或任意形参上，用于自动装配（Autowiring）其他 Bean 到当前类中。Spring容器会尝试找到合适的Bean来注入到被@Autowired注解标注的位置。
  - 注意，`@Autowired` 只能在 `Spring bean` 中声明，即 `@Component` 注解的类中。
- 在 `Controller` 和 `Service` 中接收 实现类的属性变量 上添加 `@Autowired` 注解；

> IOC 和 DI 是 通过 `@Component` 和 `@Autowired` 来实现的。

## 2、IOC 详解
要把某个对象交给 `IOC` 容器管理，需要在对应的类上加上如下注解之一。`@Component` 的衍生注解是便于三层架构分类，底层做了其它封装。
- `@Component`：声明 `bean` 的基础注解；不属于以下三类时，用此注解。 
- `@Controller`：@Component的衍生注解；标注在控制层上。常用 `@RestController`。 
- `@Service`：@Component的衍生注解；标注在业务层类上。 
- `@Repository`：@Component的衍生注解；标注在数据访问层类上（由于与 mybatis 整合，用的少）

### 2.1、IOC 容器如何区分每个 Bean ？
在 IOC 容器中每个 Bean 都有自己的名字。如果使用 `@Component` 声明实现类时没有指定名字，则默认为类名首字母小写。

可以指定 Bean 的名字，如下代码所示：
```java
@Service("DeptService")
public class DeptServiceImpl2 implements DeptService {
}
```
> 通常不会为 Bean 指定名字，采用默认值即可。


### 2.2、组件扫描注解 - @ComponentScan
使用如上4个注解来声明 `Bean` 后，要想其在 `SpringBoot` 包中生效，还需要被组件扫描注解 `@ComponentScan` 扫描到。
在 `@SpringBootApplication` 启动类声明注解中已经包含了 `@ComponentScan`，其默认扫描的范围是启动类所在 包及其子包，如果超过了此范围声明的 Bean 不会生效。

类被 `@Component` 标注时，当 Spring 启动时，`Spring` 的组件扫描机制（通过 `@ComponentScan`）会自动发现并实例化该类，将其注册为 Spring 容器中的 `Bean`。
`@Autowired` 是一个‌依赖注入注解‌，用于自动装配（`Autowiring`）其他 `Bean` 到当前类中。它通过 `Spring` 的依赖注入机制，解决组件间的依赖关系。

## 3、DI 详解

### 3.1、@Autowired 的3种注入方式
基于 `@Autowired` 进行依赖注入的常见方式有如下三种：

#### 方式一：基于属性注入（真实开发常用）
```java
@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;
}
```
- 优点：代码简洁、方便快速开发。 
- 缺点：隐藏了类之间的依赖关系、可能会破坏类的封装性。

#### 方式二：通过构造函数注入(Spring 推荐)
```java
@RestController
public class DeptController {
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

#### 方式三：通过 setter 注入
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

### 3.2、@Autowired 使用注意事项
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

#### 方案一：@Primary

```java
@Primary    // 指定该 Bean 的优先级最高
@Service()    
public class DeptServiceImpl2 implements DeptService {

}
```

#### 方案二：@Qualifier 

```java
@RestControllerpublic 
class DeptController {
    @Autowired
    @Qualifier("deptServiceImpl2")  // 根据 bean 的名字注入(有默认值，或者自定义设置)。注意 Bean 的名字默认为首字母小写。
    private DeptService deptService;
}
```

#### 方案三：@Resource，它是 JavaEE 规范注解

```java
@RestControllerpublic 
class DeptController {
    @Resource(name = "DeptServiceImpl2")    // 等价于  @Autowired + @Qualifier
    private DeptService deptService;
}
```


学习视频：<https://www.bilibili.com/video/BV1yGydYEE3H?spm_id_from=333.788.player.switch&vd_source=f97692c2f656607aeb97ee92b4310d9e&p=47>




