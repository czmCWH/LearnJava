package com.czm.exception;

/**
 * 自定义异常类
 */

public class BusinessException extends RuntimeException {

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }
}
