package com.czm.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

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
     * @Around 注解，表示当前AOP程序针对哪些方法生效。指定匹配 DeptServiceImpl 中的所有方法。
     * execution，用来定义切入点表达式。切入点表达用于描述切入点。
     */
    @Around("execution(* com.czm.service.impl.DeptServiceImpl.*(..))")
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
