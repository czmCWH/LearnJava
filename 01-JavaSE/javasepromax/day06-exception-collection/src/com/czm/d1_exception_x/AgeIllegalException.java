package com.czm.d1_exception_x;

// 自定义编译时异常
public class AgeIllegalException extends Exception {
    public AgeIllegalException() {
    }

    public AgeIllegalException(String message) {
        super(message);
    }
}
