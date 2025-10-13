package com.czm.exception;

import com.czm.entity.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 定义一个全局异常处理器
 *
 * @RestControllerAdvice === @ControllerAdvice + @ResponseBody
 */

@Slf4j
@RestControllerAdvice       // @RestControllerAdvice 注解用于捕获 Controller 层抛出的所有异常。
public class GlobalExceptionHandler {

    @ExceptionHandler   // 指定处理何种异常
    public Result doException(Exception ex) {
        log.info("--- 全局异常处理器，拦截到的异常 = {}", ex.getMessage());
        return Result.error("出错了，请联系管理员！");
    }
}
