# 一、AOP 面向切面编程

> Spring 两大核心思想：IOC、AOP。⚠️⚠️⚠️

`AOP`：`Aspect Oriented Programming` (面向切面编程、面向方面编程)，`可简单理解为就是面向特定方法编程`。

场景：案例中部分业务方法运行较慢，定位执行耗时较长的接口，此时需要统计每一个业务方法的执行耗时。
    类似这种针对某一个或某几个方法进行编程的场景，可以借助 AOP 简化操作。
优势：⚠️
    1、减少重复代码
    2、代码无侵入
    3、提高开发效率
    4、维护方便

AOP是一种思想，而在 Spring 框架中对这种思想进行的实现，因此学习的就是 `Spring AOP`。
Spring AOP 底层会生成一个代理对象，来执行公共的方法。


# 二、Spring AOP 基础

## 2.1、Spring AOP 快速入门
需求：统计所有业务层方法的执行耗时。

### 步骤1、导入 AOP 依赖

AOP依赖方案一：运行时动态代理，性能较低，
```xml
<!-- AOP 起步依赖 -->
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-aop</artifactId>
    <!-- 如果是基于 Springboot 官方骨架创建的项目，则不需要指定 aop依赖的 version，因为父工程中统一管理了依赖的版本！ -->
</dependency>
```
AOP依赖方案二：字节码直接增强，无代理开销。
```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjrt</artifactId>
    <version>1.9.20</version>
</dependency>
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
    <version>1.9.24</version>
</dependency>
```

### 步骤2、编写AOP的程序：针对特定的方法根据业务需要进行编程 (即：编写公共的逻辑代码)
代码实现：`/aspect/DemoAspect.java`
```java
/**
 * Spring AOP 入门案例：声明一个 AOP类、切面类
 * ⚠️:
 *   对于AOP 程序来说，类名都以 Aspect 结尾。
 *   如果把 @Aspect + @Component 都注释掉，Spring 将不执行此 Spring AOP切面类
 */
@Slf4j
@Aspect     // 标识当前类是一个 AOP类（即，切面类）
@Component  // @Component 注解，将此类交给 IOC容器管理
public class DemoAspect {
    /**
     * 统计业务层中所有方法的执行时间
     */
    @Around("execution(* com.czm.service.impl.DeptServiceImpl.*(..))")   // @Around 注解，表示当前AOP程序针对哪些方法生效。指定匹配 DeptServiceImpl 中的所有方法
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        // 1、记录方法运行的开始时间
        long begin = System.currentTimeMillis();

        // 2、执行原始的业务方法，⚠️
        Object result = pjp.proceed();

        // 3、记录方法运行结束时间，计算执行耗时
        long end = System.currentTimeMillis();
        log.info("--- DemoAspect 方法：{} ，执行耗时：{}ms", pjp.getSignature(), end - begin);

        // 4、返回响应结果
        return result;
    }
}
```


## 2.2、SpringAOP 的应用场景
* 统计方法的执行耗时；
* 记录系统的（增删改查）操作日志，业务操作，执行前执行后打印日志；
* 事务管理；事务底层就是基于 AOP 实现的。
* 权限控制；


# Spring AOP 的核心概念 ⚠️
详细解释见图：`/img/02-Spring AOP核心概念.jpg`

    - 连接点 (JoinPoint)
    - 切入点 (Pointcut)
    - 通知 (Advice)
    - 切面 (Aspect)
    - 目标对象 (Target)
