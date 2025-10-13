package com.czm.d8_generics.demo;

import org.jetbrains.annotations.NotNull;

/**
 * 限制泛型的类型参数
 */
public class Man<T extends Number> {
    private T age;
    public Man(T age) {
        this.age = age;
    }
    public T getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Man [age = " + age + "]";
    }
}