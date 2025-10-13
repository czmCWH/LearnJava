package com.czm.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


/**
 * AOP 通知，通过 @PointCut 注解 抽取公共切点表达式
 * ⚠️⚠️⚠️ 如果把 @Aspect + @Component 都注释掉，将不执行此 Spring AOP切面类
 */


@Slf4j
//@Aspect     // 声明切面类
//@Component  // 表示把类交给 Spring 容器管理
public class MyAspect2 {

    /**
     * 抽取公共切点表达式
     * public：仅能在当前切面类中引用该表达式
     * private：在其它外部的切面类中也可以引用该表达式
     */
    @Pointcut("execution(* com.czm.service.impl.DeptServiceImpl.*(..))")
    public void pt() {}

    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("--- MyAspect11 - around --- 环绕前通知");
        Object result = joinPoint.proceed();    // 调用目标方法
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
