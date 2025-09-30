package com.czm.d1_oop_class;

public class Person {
    String name;
    int age;
    // 1、无参构造器
    public Person() {
        System.out.println("--- 执行了无参构造器");
    }
    // 2、有参构造器
    public Person(String name) {
        this.name = name;
        System.out.println("--- 执行了有参构造器");
    }
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("--- 执行了有参构造器");
    }
}
