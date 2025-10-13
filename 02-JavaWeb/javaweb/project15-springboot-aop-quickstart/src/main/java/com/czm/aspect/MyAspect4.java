package com.czm.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 演示：切面表达式 - @annotation(注解的全类名)
 * ⚠️⚠️⚠️ 如果把 @Aspect + @Component 都注释掉，将不执行此 Spring AOP切面类
 */

@Slf4j
//@Aspect     // 声明切面类
//@Component
public class MyAspect4 {

//    @Pointcut("execution(* com..DeptServiceImpl.list(..)) || execution(* com..DeptServiceImpl.getById(..))")

    @Pointcut("@annotation(com.czm.aspect.Log)")    // 表示只匹配 @Log 自定义注解的方法
    public void pt() {}

    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("--- MyAspect4 - around --- 环绕前通知");
        Object result = joinPoint.proceed();    // 调用目标方法
        log.info("--- MyAspect4 - around --- 环绕后通知");
        return result;  // 返回目标方法的结果
    }

    @Before("pt()")
    public void before() {
        log.info("--- MyAspect4 - before --- 前置通知，目标方法前触发");
    }

    @After("pt()")
    public void After() {
        log.info("--- MyAspect4 - after --- 后置通知，无论目标方法是否异常都会触发");
    }

}
