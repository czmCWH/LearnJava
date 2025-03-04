package com.czm.constructor;

public class Student {
    String name;
    int age;
    // 1、无参构造器
    public Student() {
        System.out.println("--- 执行了无参构造器");
    }
    // 2、有参构造器
    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("--- 执行了有参构造器");
    }
}
