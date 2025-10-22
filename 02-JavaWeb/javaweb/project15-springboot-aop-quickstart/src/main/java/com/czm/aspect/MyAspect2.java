package com.czm.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/**
 * 定义一个 Spring AOP 切面类 - 演示：@PointCut 注解 抽取公共切点表达式
 * ⚠️ 如果把 @Aspect + @Component 都注释掉，将不执行此 Spring AOP切面类
 */
@Slf4j
//@Order(0)     // @Order 注解，用于控制 Spring AOP 通知执行的顺序
//@Aspect     // 声明切面类
//@Component  // 表示把类交给 Spring 容器管理
public class MyAspect2 {

    /**
     * 使用 @Pointcut 注解 自定义公共切点表达式
     *  ⚠️ 方法的权限修饰符对 切点表达式 的作用：
     *      private：当前声明的切入点表达式，仅能在当前切面类中引用该表达式
     *      public：当前声明的切入点表达式，在其它外部的切面类中也可以引用该表达式
     */
    @Pointcut("execution(* com.czm.service.impl.DeptServiceImpl.*(..))")
    public void pt() {}

    // 在通知中，使用 方法调用的方式 引入 自定义的公共切入点表达式
    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("--- MyAspect11 - around --- 环绕前通知");

        Object result = joinPoint.proceed();     // 调用目标方法 ⚠️ 对于 @Around 通知必须调用目标方法，如果不调用，目标方法将不会执行

        log.info("--- MyAspect11 - around --- 环绕后通知");
        return result;  // 返回目标方法的结果
    }

    @Before("pt()")
    public void before() {
        log.info("--- MyAspect11 - before --- 前置通知，目标方法前触发");
    }

    @After("pt()")
    public void After() {
        log.info("--- MyAspect11 - after --- 后置通知，无论目标方法是否异常都会触发");
    }

    @AfterReturning("pt()")
    public void afterReturning() {
        log.info("--- MyAspect11 - afterReturning --- 返回后通知，目标方法正常返回才会触发");
    }

    @AfterThrowing("pt()")
    public void afterThrowing() {
        log.info("--- MyAspect11 - afterThrowing ---异常后通知，只有目标方法抛异常才会触发");
    }
}
