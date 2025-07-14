package com.czm.aspect;

import com.czm.anno.AutoFill;
import com.czm.constant.AutoFillConstant;
import com.czm.context.BaseContext;
import com.czm.enumeration.OperationType;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

/**
 * 自定义切面类，用于公共字段自动填充。
 */

@Slf4j
@Component
@Aspect
public class AutoFillAspect {

    // 采用前置通知
    @Before("@annotation(com.czm.anno.AutoFill)")
    public void autoFill(JoinPoint joinPoint) {
        // 1、获取目标方法上的注解，并拿到注解的属性值
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();   // 方法签名
        Method method = methodSignature.getMethod();    // 方法对象
        AutoFill autoFill = method.getAnnotation(AutoFill.class);       // 获取自定义注解
        OperationType operationType = autoFill.value(); // 获取自定义注解 的 参数值

        // 2、获取到目标方法的参数对象
        Object[] args = joinPoint.getArgs();    // 获取增强方法的参数
        if (args == null || args.length == 0) {
            return;
        }
        Object entity = args[0];    // 获取到插入、更新方法的参数对象

        // 3、判断注解中的属性值，使用反射补充属性值
        try {
            if (operationType == OperationType.INSERT) {    // 插入操作：创建时间、更新时间、创建人、更新人
                Method setCreateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_TIME, LocalDateTime.class);
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setCreateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_CREATE_USER, Long.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                setCreateTime.invoke(entity, LocalDateTime.now());
                setUpdateTime.invoke(entity, LocalDateTime.now());
                setCreateUser.invoke(entity, BaseContext.getCurrentId());
                setUpdateUser.invoke(entity, BaseContext.getCurrentId());

            } else if (operationType == OperationType.UPDATE) { // 修改操作：更新时间、更新人
                Method setUpdateTime = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_TIME, LocalDateTime.class);
                Method setUpdateUser = entity.getClass().getDeclaredMethod(AutoFillConstant.SET_UPDATE_USER, Long.class);
                setUpdateTime.invoke(entity, LocalDateTime.now());
                setUpdateUser.invoke(entity, BaseContext.getCurrentId());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
