package com.czm.exception;

import com.czm.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 定义一个全局异常处理器
 *   @RestControllerAdvice 注解，用于全局性地处理控制器（Controller）层的异常；
 *   @RestControllerAdvice == @ControllerAdvice + @ResponseBody；
 *   @ResponseBody 用于把方法返回值自动序列化为 JSON/XML
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * @ExceptionHandler 注解，集中处理特定异常
     * @param ex 表示拦截什么类型的异常。Exception，表示拦截所有类型的异常
     * @return 返回值表示处理异常后，返回给前端的响应结果
     */
    @ExceptionHandler   //
    public Result doException(Exception ex) {
        log.info("--- 全局异常信息 = {}", ex.getMessage());
        return Result.error("出错了，请联系管理员！");
    }

    /**
     * 指定捕获 DuplicateKeyException 异常
     * @ExceptionHandler 会按照异常的继承关系，从下往上查询异常处理方法进行处理
     */
    @ExceptionHandler(value = DuplicateKeyException.class)
    public Result doDuplicateKeyException(DuplicateKeyException ex) {
        log.info("--- 重复key异常 = {}", ex.getMessage());
        // 获取异常的信息并解析，反馈更精确的信息给前端
        String message = ex.getMessage();
        int i = message.indexOf("Duplicate entry");
        String errMsg = message.substring(i);
        String[] arr = errMsg.split(" ");
        return Result.error(arr[2] + "已存在");
    }
}
