package com.czm.d1_exception.sub;

public class WrongAgeException extends RuntimeException {
    private int age;
    public WrongAgeException(int age, int max) {
        super("wrong age: " + age + ", age must be > " + max);
    }
}