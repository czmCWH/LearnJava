package com.czm.d1_exception_x;

// 自定义一个 RuntimeException 异常
public class AgeIllegalRuntimeException extends RuntimeException {
    public AgeIllegalRuntimeException() {
    }

    public AgeIllegalRuntimeException(String message) {
        super(message);
    }
}
