package com.czm.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 定义一个 Spring AOP 切面类
 *      演示：通过连接点对象获取目标方法相关的信息：方法名、参数、签名、目标类名、返回值（仅限环绕通知中获取）
 */
@Slf4j
@Aspect
@Component
public class MyAspect5 {

    @Pointcut("execution(* com.czm.service.impl.DeptServiceImpl.*(..))")
    public void pt() {}

    @Around("pt()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("--- MyAspect5 - around --- 环绕前通知");
        // 0、获取目标对象
        Object target = joinPoint.getTarget();
        log.info("--- target = {}", target);

        // 1、获取目标类名
        String name = joinPoint.getTarget().getClass().getName();
        log.info("--- name = {}", name);

        // 2、获取目标方法签名
        Signature signature = joinPoint.getSignature();
        log.info("--- signature = {}", signature);

        // 3、获取目标方法名
        String methodName = signature.getName();
        log.info("--- methodName = {}", methodName);

        // 4、获取目标方法运行时的参数
        Object[] args = joinPoint.getArgs();
        log.info("--- args = {}", args);

        // 5、获取方法返回值
        Object result = joinPoint.proceed();     // 调用目标方法 ⚠️ 对于 @Around 通知必须调用目标方法，如果不调用，目标方法将不会执行
        log.info("--- result = {}", result);

        log.info("--- MyAspect5 - around --- 环绕后通知");
        return result;  // 返回目标方法的结果
    }

    @Before("pt()")
    public void before(JoinPoint joinPoint) {
        log.info("--- MyAspect5 - before --- 前置通知，目标方法前触发");
        // 1、获取目标类名
        String name = joinPoint.getTarget().getClass().getName();
        log.info("--- before name = {}", name);

        // 2、获取目标方法签名
        Signature signature = joinPoint.getSignature();
        log.info("--- before signature = {}", signature);

        // 3、获取目标方法名
        String methodName = signature.getName();
        log.info("--- before methodName = {}", methodName);

        // 4、获取方法参数
        Object[] args = joinPoint.getArgs();
        log.info("--- before args = {}", args);
    }

}
