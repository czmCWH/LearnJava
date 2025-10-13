package com.czm.handler;

import com.czm.constant.MessageConstant;
import com.czm.exception.BaseException;
import com.czm.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;

/**
 * 全局异常处理器，处理项目中抛出的业务异常
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    private final HttpMessageConverters messageConverters;

    public GlobalExceptionHandler(HttpMessageConverters messageConverters) {
        this.messageConverters = messageConverters;
    }

    /**
     * 捕获业务异常
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result exceptionHandler(BaseException ex){
        log.error("异常信息：{}", ex.getMessage());
        return Result.error(ex.getMessage());
    }

    /**
     * 处理SQL异常
     * @param ex
     * @return
     */

    /**
     * 定义一个捕获 Sql 异常的全局异常处理方法。
     * @param ex
     * @return
     */
    @ExceptionHandler
    public Result doSQLException(SQLIntegrityConstraintViolationException ex){
        String message= ex.getMessage();
        log.error("--- 异常信息:{}", message);
       if (message.contains("Duplicate entry")){
           String[] split = message.split(" ");
           String username = split[2];
           String msg = username + MessageConstant.ALREADY_EXISTS;
           return Result.error(msg);
       }

        return Result.error(MessageConstant.UNKNOWN_ERROR);
    }


}
