package com.czm.d1_exception;


import java.text.SimpleDateFormat;

public class Test1 {
    /*
     1、认识异常
        异常，代表程序出现的问题，例如：数组索引越界。

     2、Java 异常体系分类
        java.lang.throwable:
            a、Error
                代表系统级别错误，也就是说系统一旦出现问题，sun公司会把这些问题封装成Error对象给出来。
                说白了，Error是给sun公司自己用的，不是给我们程序员用的，因此我们开发人员不用管它。

            b、Exception：
                代表的是我们程序可能出现的问题，所以，我们程序员通常会用Exception以及它的孩子来封装程序出现的问题。
                Exception 分为：
                    RuntimeException及其子类：运行时异常，编译阶段不会出现错误提醒，运行时出现的异常(如:数组索引越界异常)
                    编译时异常：编译阶段就会出现错误提醒的。(如:日期解析异常）
     3、异常的作用
        a、异常是用来查询系统 Bug 的关键参考信息！
        b、异常可以作为方法内部的一种特殊返回值，以便通知上层调用者底层的执行情况！

     */
    public static void main(String[] args) {
        // 1、数组索引越界异常：ArrayIndexOutOfBoundsException
//        int[] arr = {1, 2, 3};
//        System.out.println("===="+arr[3]);

        // 2、空指针异常：NullPointerException
//        String name = null;
//        System.out.println("===="+name.length());

        // 3、数学操作异常：ArithmeticException
//        System.out.println(10/0);

        // 4、类型转换异常：ClassCastException
//        Object o = "你好abfsd123";
//        Integer i = (Integer)o;

        // 5、数字转换异常：NumberFormatException
//        String name = "123a";
//        int i = Integer.valueOf(name);

        // 2、执行函数时，捕获异常
        try {
            System.out.println(divide(5, 0));
        } catch (Exception e) {
            System.out.println("--- 捕获到异常");
            e.printStackTrace();    // 打印异常信息
        }
        System.out.println("--- 结束了");

    }

    public static void parsetDate(String str) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date d = sdf.parse(str);    // 编译时异常，写代码时就报错。
//        System.out.println(d);
    }

    // 1、定义函数时抛出异常
    public static int divide(int a, int b) {
        if (b == 0) {
            System.out.println("b 不能为 0");
            // 抛出异常作为返回值，通知上层此处出现了什么bug。
            throw new RuntimeException("b 不能为 0");
        }
        int c = a / b;
        return c;
    }
}
