# 一、AOP 面向方法编程

> Spring 两大核心思想：IOC、AOP。

`AOP`：`Aspect Oriented Programming` (面向切面编程、面向方面编程)，其实就是面向特定方法编程。
场景：案例中部分业务方法运行较慢，定位执行耗时较长的接口，此时需要统计每一个业务方法的执行耗时。
优势：
1、减少重复代码
2、代码无侵入
3、提高开发效率
4、维护方便

> AOP是一种思想，而在 Spring 框架中对这种思想进行的实现，那我们要学习的就是 Spring AOP。
> 
> Spring AOP 底层会生成一个代理对象，来执行公共的方法。

### SpringAOP 程序开发步骤
1、导入 AOP 依赖
```xml
<!-- AOP 起步依赖 -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-aop</artifactId>
</dependency>
```

2、编写AOP的程序(即：公共的逻辑代码)
具体实现：`/aspect/DemoAspect.java`

### SpringAOP 的应用场景 ?
* 统计耗时；
* 记录系统的操作日志，业务操作，执行前执行后打印日志；
* 权限控制；
* 事务管理；
