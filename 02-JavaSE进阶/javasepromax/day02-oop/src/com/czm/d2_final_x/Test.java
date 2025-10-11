package com.czm.d2_final_x;

public class Test {
    /*
     1、final
        final 关键字是最终的意思，可以修饰(类、方法、变量)

        修饰类：该类被称为最终类，特点是不能被继承了。一般用于工具类。

        修饰方法：该方法被称为最终方法，特点是不能被重写了。

        修饰变量：该变量只能被赋值一次。

     2、Java 的变量分类
        成员变量
            静态成员变量；
            实例成员变量；
        局部变量：存在于方法内、形参、for循环变量、构造函数中的变量

     3、final 修饰变量的注意
        final修饰基本类型的变量，变量存储的数据不能被改变
        final修饰引用类型的变量，变量存储的地址不能被改变，但地址所指向对象的内容是可以被改变的。

     */

    final int count = 10;

    public static final String NAME = "好好学习";
    public static final String DESC;

    // 静态代码块赋值
    static {
//        NAME = "";
        DESC = "天天向上";  // 只能赋值一次
    }

    public static void main(String[] args) {
//        count = 20;
    }
}
