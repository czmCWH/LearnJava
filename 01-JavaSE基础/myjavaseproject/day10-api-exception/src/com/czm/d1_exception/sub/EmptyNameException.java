package com.czm.d1_exception.sub;

public class EmptyNameException extends RuntimeException {
    public EmptyNameException() {
        super("name must not be empty");
    }
}
