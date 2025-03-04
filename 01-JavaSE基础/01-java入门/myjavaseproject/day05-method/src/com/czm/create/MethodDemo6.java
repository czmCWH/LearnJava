package com.czm.create;

public class MethodDemo6 {

    /*
     1、方法重载
    一个类中，出现多个方法的名称相同，但是它们的形参列表是不同的，那么这些方法就称为方法重载了。

     2、方法重载的注意事项
        一个类中，只要一些方法的名称相同、形参列表不同，那么它们就是方法重载了。
            其它的都不管(如:修饰符，返回值类型是否一样都无所谓)。
        形参列表不同指的是:形参的个数、类型、顺序不同，不关心形参的名称，

     3、方法重载的应用场景
        开发中我们经常需要为处理一类业务，提供多种解决方案，此时用方法重载来设计是很专业的。
     */

    public static void main(String[] args) {

    }

    public static void test() {

    }

    public static void test(int a) {

    }

    public static void test(double a) {

    }

    public static void test(double a, double b) {

    }

//    public static int test(double b, double a) {
//
//    }

    public static void test(int a, double b) {

    }

    public static void test(double b, int a) {

    }
}
