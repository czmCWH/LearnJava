package com.czm.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 演示：切入点表达式 - execution
 * ⚠️⚠️⚠️ 如果把 @Aspect + @Component 都注释掉，将不执行此 Spring AOP切面类
 */

@Slf4j
//@Aspect     // 声明切面类
//@Component
public class MyAspect3 {

    @Pointcut("execution(public void com.czm.service.impl.DeptServiceImpl.delete(java.lang.Integer))")    // 指定 切入点 只命中 delete 方法
//    @Pointcut("execution(void com.czm.service.impl.DeptServiceImpl.delete(java.lang.Integer))")
//    @Pointcut("execution(void delete(java.lang.Integer))")      // 会扫描整个项目中 delete 方法
//    @Pointcut("execution(* com.*.service.*.DeptServiceImpl.*(*))")  // * 通配符代表单个符号，可以作用在返回值、包名、类名、方法名、参数上
//    @Pointcut("execution(* com..DeptServiceImpl.*(..))")    // 代表任意个任意的字符，一般用在参数、包名上
    public void pt() {}

    // 例如：只给 list 和 getById 方法添加通知。对于一些特殊的没有共性的方法，可以通过连接符 || 、&& 拼接表达式。
    @Pointcut("execution(* com..DeptServiceImpl.list(..)) || execution(* com..DeptServiceImpl.getById(..))")
    public void pt2() {}

    @Around("pt2()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("--- MyAspect3 - around --- 环绕前通知");
        Object result = joinPoint.proceed();    // 调用目标方法
        log.info("--- MyAspect3 - around --- 环绕后通知");
        return result;  // 返回目标方法的结果
    }

    @Before("pt2()")
    public void before() {
        log.info("--- MyAspect3 - before --- 前置通知，目标方法前触发");
    }

    @After("pt2()")
    public void After() {
        log.info("--- MyAspect3 - after --- 后置通知，无论目标方法是否异常都会触发");
    }
}
