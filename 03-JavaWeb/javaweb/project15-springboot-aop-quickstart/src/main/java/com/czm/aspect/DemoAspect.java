package com.czm.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * Spring AOP 入门案例：统计 DeptServiceImpl 类中所有方法的执行时间

 * ⚠️⚠️⚠️ 如果把 @Aspect + @Component 都注释掉，将不执行此 Spring AOP切面类
 */

@Slf4j
//@Aspect     // 声明当前类是一个 AOP类（切面类）
//@Component
public class DemoAspect {

    /**
     * 统计耗时
     * @return
     */
    @Around("execution(* com.czm.service.impl.DeptServiceImpl.*(..))")   // 指定匹配 DeptServiceImpl 中的所有方法
    public Object recordTime(ProceedingJoinPoint joinPoint) throws Throwable {
        // 1、获取方法运行的开始时间
        long begin = System.currentTimeMillis();
        // 2、运行原始方法
        Object result = joinPoint.proceed();
        // 3、获取方法运行结束时间，计算执行耗时
        long end = System.currentTimeMillis();
        log.info("--- DemoAspect 执行耗时 = {}ms", end - begin);
        // 4、返回响应结果
        return result;
    }
}
