package com.czm.d1_static_field_x;

public class Student {
    // 类变量、静态变量，有 static 修饰，属于类持有，在内存中只加载一次，被类和类的全部对象共享。
    static String name;
    // 对象变量、实例变量，无 static 修饰，属于每个对象。
    int age;
}
