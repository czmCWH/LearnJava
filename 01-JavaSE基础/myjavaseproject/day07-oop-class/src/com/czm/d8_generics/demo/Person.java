package com.czm.d8_generics.demo;

/**
 * 泛型方法 - 构造方法(很少用)
 */
public class Person<T> {
    // T 是属于 Person 泛型类型的 类型参数
    private T age;

    // 构造方法为泛型方法，E 是属于 泛型方法的 类型参数
    public <E> Person(E name, T age) {
        this.age = age;
    }
}
