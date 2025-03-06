package com.czm.d3_static_attention;

public class Test1 {
    /*
     1、使用 static 的几点注意事项

        类方法(静态方法)中可以直接访问类的静态成员，不可以直接访问实例成员

        实例方法中既可以直接访问类成员，也可以直接访问实例成员；

        实例方法中可以出现 this 关键字，类方法中不可以出现 this 关键字；
        (因为 this 表示当前对象)
     */

    // 静态变量
    public static String info = "与动物有区别！";

    // 静态方法
    public static void doSome() {
        System.out.println("人类可以创造");
    };

    // 实例变量
    private String name;

    public void sayHello() {
        System.out.println("---hello world!" + info);
    }

    public static void main(String[] args) {
        Test1 t1 = new Test1();
        t1.sayHello();

    }
}
