package com.czm.aspect;

import com.czm.entity.OperateLog;
import com.czm.mapper.OperateLogMapper;
import com.czm.utils.CurrentHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * 定义 Spring AOP 切面类 - 案例1：记录特定方法的操作日志信息
 *      在 DeptController 中使用 @Log 注解标识特定的方法，记录这些方法的操作日志到数据库表中。
 */
@Slf4j
@Aspect
@Component
public class OperationLogAspect {

    @Autowired
    private OperateLogMapper operateLogMapper;

    @Around("@annotation(com.czm.anno.Log)")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();

        // 执行目标方法
        Object result = joinPoint.proceed();

        // 计算耗时
        long endTime = System.currentTimeMillis();
        long costTime = endTime - startTime;

        // 1、获取目标方法签名
        Signature signature = joinPoint.getSignature();

        // 2、获取目标方法名
        String methodName = signature.getName();

        // 3、获取目标类名
        String name = joinPoint.getTarget().getClass().getName();

        // 4、获取目标方法运行时的参数
        Object[] args = joinPoint.getArgs();

        // 构建日志对象
        OperateLog olog = new OperateLog();
        olog.setOperateEmpId(getCurrentUserId());
        olog.setOperateTime(LocalDateTime.now());
        olog.setClassName(name);
        olog.setMethodName(methodName);
        olog.setMethodParams(Arrays.toString(args));
        olog.setReturnValue(result != null ? result.toString() : "void");
        olog.setCostTime(costTime);

        // 保存日志
        operateLogMapper.insert(olog);

        log.info("--- 记录操作日志：{}", olog);

        return result;
    }

    // 获取当前登录用户ID
    private Integer getCurrentUserId() {
        // ⚠️：通过 ThreadLocal 获取用户ID
        return CurrentHolder.getCurrentId();
    }
}
