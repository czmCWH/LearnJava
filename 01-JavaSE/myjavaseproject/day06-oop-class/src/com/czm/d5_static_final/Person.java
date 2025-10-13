package com.czm.d5_static_final;

/**
 * 定义 类变量，用于记录程序启动后，此类实例化了多少个对象。
 */
public class Person {
    // 使用 static 声明类变量
    public static int count;

    public Person() {
        // 访问当前类中的静态变量，类名可以省略。
        count++;
    }

    // 类方法
    public static void test() {
        System.out.println("--- static test");
        test1();
    }

    // 类方法
    public static void test1() {
        System.out.println("--- static test1 count = " + count);
    }
}
