package com.czm;

public class d6_method_overload {

    /*
     1、方法签名（Method Signature）
     方法签名由2部分组成：方法名 + 参数类型。
     ⚠️ 在同一个类中，不能定义2个方法签名一样的方法。

     2、Java 支持方法重载（Method Overload）
    一个类中，出现多个方法的名称相同，但是它们的形参列表是不同的，那么这些方法就称为方法重载了。即：方法名相同，方法签名不同。
    方法重载与返回值类型、参数名称无关！！！

     3、方法重载的注意事项
        一个类中，只要一些方法的名称相同、形参列表不同，那么它们就是方法重载了。
            其它的都不管(如:修饰符，返回值类型是否一样都无所谓)。
        形参列表不同指的是:形参的个数、类型、顺序不同，不关心形参的名称，

     4、方法重载的应用场景
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
