# 一、JavaWeb 三层架构

* Controller：控制层，接收前端发送的请求，对请求进行处理，并响应数据。即：接受请求、响应数据。

* Service：业务逻辑层，处理具体的业务逻辑。即：数据封装、逻辑处理等。（根据业务会比较复杂）

* Dao：数据访问层(Data Access 0bject)(也叫 持久层)，负责数据访问操作，包括数据的增、删、改、查。即：数据访问。

> JavaWeb 三层架构优点：
>    遵循单一职责原则，提高代码复用性，便于开发维护。

# 二、面向接口编程

虽然 遵循了 JavaWeb 三层架构开发，代码复用性提高了，但是扩展性不强。
如果存在新的业务是，就需要对 Service 层原本的类上进行修改，这样旧的逻辑也将不会存在了。**代码扩展性不强！**

* 面向接口编程，先写一个接口制定规范，再根据不同的业务写多个实现类，并以多态的方式使用实现类。 
* 面向接口编程 是遵守 **开放封闭原则** 的，即：修改是关闭的，新增是开放的。 
* 优点：增强程序的扩展性。

## 分层解耦

耦合：衡量软件中各个层/模块之间的依赖、关联的程度。
内聚：软件中各个功能模块内部的功能联系。
软件设计原则：高内聚低耦合。

* 虽然面向接口编程增强了程序的扩展性，但是存在 每次新业务调整时，会产生新的 实现类。而使用原本实现类的的地方采用了多态接收，也必须进行修改，这样各层之间依赖性强，存在强相关（即：耦合）。**如何解耦合？**

### Spring IOC 思想

* 控制反转：Inversion 0f Control，简称 IOC。不自己new对象，对象的创建控制权由程序自身移到外部(容器)，这种思想称为控制反转。又称 **IOC容器、Spring容器。**

* 依赖注入：Dependency Injection，简称 DI。容器为应用程序提供运行时，所依赖的资源，称之为依赖注入。

* Bean 对象：IOC 容器 中创建、管理的对象，称之为**Bean**。

### IOC 和 DI 的实现

* 将 Dao 及 Service 层的实现类，交给 IOC 容器管理。使用 `@Component` 注解来实现
* 为 Controller 及 Service 注入运行时所依赖的对象。使用 `@Autowired` 注解来实现


## IOC 详解

要把某个对象交给 IOC 容器管理，需要在对应的类上加上如下注解之一：

1. @Component：声明bean的基础注解；不属于以下三类时，用此注解。⚠️
2. @Controller：@Component的衍生注解；标注在控制层上。
3. @Service：@Component的衍生注解；标注在业务层类上。⚠️
4. @Repository：@Component的衍生注解；标注在数据访问层类上（由于与mybatis集合，用的少）

注意：声明 bean 的时候，可以通过注解的 value 属性指定 bean 的名字，如果没有指定，默认为类名首字母小写。

### 声明 Bean 注解的注意事项

前面声明 bean 的四大注解，要想生效，还需要被组件扫描注解 @ComponentScan 扫描。

该注解虽然没有显式配置，但是实际上已经包含在了启动类声明注解 @SpringBootApplication 中，默认扫描的范围是启动类所在包及其子包。

## DI 详解

@Autowired 注解，**默认是按照类型注入的**，如果存在多个相同类型的bean(因为面向接口开发，使用接口来定义类型，而接口的实现类会有多个，因此造成报错)，将会报出如下错误:

```
// 问题
Description:

Field deptService in com.czm.controller.DeptController required a single bean, but 2 were found:
	- deptServiceImpl: defined in file [/Users/CZM/Git/github/LearnJava/03-JavaWeb/javaweb/project05-web-ioc-di/target/classes/com/czm/service/DeptServiceImpl.class]
	- DeptServiceImpl2: defined in file [/Users/CZM/Git/github/LearnJava/03-JavaWeb/javaweb/project05-web-ioc-di/target/classes/com/czm/service/DeptServiceImpl2.class]

This may be due to missing parameter name information

// 解决办法
Action:

Consider marking one of the beans as @Primary, updating the consumer to accept multiple beans, or using @Qualifier to identify the bean that should be consumed

```

方案一：@Primary

```java
@Primary
@Service()    // value 属性指定 bean 的名字
public class DeptServiceImpl2 implements DeptService {

}
```

方案二：@Qualifier 

```java
@RestControllerpublic 
class DeptController {
    @Autowired
    @Qualifier("deptServiceImpl2")  // 根据bean的名字注入(有默认值，或者自定义设置)
    private DeptService deptService;
}
```

方案三：@Resource，它是JaveEE注解

```java
@RestControllerpublic 
class DeptController {
    @Resource(name = "DeptServiceImpl2")    // 等价于  @Autowired + @Qualifier
    private DeptService deptService;
}
```







