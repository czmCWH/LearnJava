package com.czm.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

/**
 * 定义一个 Spring AOP 切面类 - 演示：AOP 各种通知使用
 * ⚠️ 如果把 @Aspect + @Component 都注释掉，将不执行此 Spring AOP切面类
 */
@Slf4j
//@Aspect     // 声明切面类
//@Component  // 表示把类交给 Spring 容器管理
public class MyAspect1 {

    /**
     * 1、before 前置通知 - 在目标方法运行之前运行
     */
    @Before("execution(* com.czm.service.impl.DeptServiceImpl.*(..))")
    public void before() {
        log.info("--- MyAspect1 - before --- 前置通知，目标方法前触发");
    }

    /**
     *  2、around 环绕通知 - 在目标方法运行之前、之后运行
     * @param pjp 连结点
     * @return Object，是指 调用目标方法的返回值
     */
    @Around("execution(* com.czm.service.impl.DeptServiceImpl.*(..))")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        log.info("--- MyAspect1 - around --- 环绕前通知");

        Object result = pjp.proceed();    // 调用目标方法 ⚠️ 对于 @Around 通知必须调用目标方法，如果不调用，目标方法将不会执行

        log.info("--- MyAspect1 - around --- 环绕后通知");
        return result;  // 返回目标方法的结果
    }

    /**
     * 3、after 后置通知 - 目标方法运行之后运行，无论是否出现异常都会运行
     */
    @After("execution(* com.czm.service.impl.DeptServiceImpl.*(..))")
    public void After() {
        log.info("--- MyAspect1 - after --- 后置通知，无论目标方法是否异常都会触发");
    }

    /**
     * 4、afterReturning 返回后通知 - 在目标方法运行之后运行，如果出现异常不会运行
     */
    @AfterReturning("execution(* com.czm.service.impl.DeptServiceImpl.*(..))")
    public void afterReturning() {
        log.info("--- MyAspect1 - afterReturning --- 返回后通知，目标方法正常返回才会触发");
    }

    /**
     * 5、afterThrowing 异常后通知 - 在目标方法运行之后运行，只有出现异常才会运行
     */
    @AfterThrowing("execution(* com.czm.service.impl.DeptServiceImpl.*(..))")
    public void afterThrowing() {
        log.info("--- MyAspect1 - afterThrowing ---异常后通知，只有目标方法抛异常才会触发");
    }

}
