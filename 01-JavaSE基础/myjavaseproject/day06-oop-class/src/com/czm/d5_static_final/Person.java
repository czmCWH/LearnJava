package com.czm.d5_static_final;

public class Person {
    // 使用 static 声明类变量
    public static int count;

    public Person() {}

    public static void test() {
        System.out.println("--- static test");
        test1();
    }

    public static void test1() {
        System.out.println("--- static test1 count = " + count);
    }
}
