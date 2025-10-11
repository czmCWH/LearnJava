package com.czm.d1_exception;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Test01 {

    /*
     1、什么是异常（Exception）？
     java 开发中会遇到各种错误：
        a、语法错误，会导致编译失败，程序无法运行。
        b、逻辑错误，比如需要执行加法操作时，不小心写成了减法操作。
        c、运行时错误，在程序运行过程中产生的意外，如果开发者不做处理，会导致程序终止运行。这在 Java 中也叫做异常。

     2、throwable
     Java 中所有的异常最终都继承自 java.lang.throwable。继承关系如下：
        Object:
            throwable:
                Exception: 代表程序中的错误，开发者使它或其子类来封装程序出现的问题。
                    RuntimeException：运行时异常，编译阶段不会出现错误提醒，运行时出现的异常(如:数组索引越界异常)
                    编译时异常：编译阶段就会出现错误提醒的。(如:日期解析异常）
                    ....
                Error: 代表系统级别错误，sun公司会把这些问题封装成Error对象给出来。开发者不必使用 Error。
                    ....

     3、异常的分类
        1、检查型异常（Checked Exception）
            这类异常一般难以避免，⚠️编译器会进行检查。--- 如果开发者没有处理这类异常，编译器将会报错。
            除 Error及其子类、RuntimeException及其子类 以外的异常。
            如：new FileOutputStream("/user/1.txt");
        2、非检查型异常（Unchecked Exception）
            这类异常一般可以避免，编译器不会进行检查。--- 如果开发者没有处理这类异常，编译器将不会报错。
            Error及其子类、RuntimeException及其子类 的异常是 非检查型异常。

     4、异常的处理
      程序产生了异常，一般我们会称之为：抛出了异常。

      不管抛出的是检查型异常，还是非检查型异常。只要程序员没有主动去处理它，都会导致 Java 程序终止运行。

      ⚠️ 所有异常处理有2种方法：
        1、try-catch，捕捉处理异常，防止程序抛出异常导致终止运行。
        2、throws，将异常抛给上层方法。

      任何异常最终未被处理，那么就会传递到 JVM 中，此时程序会终止运行。


     */

    public static void main(String[] args) {
        /*
         👉 常见的检查型异常
         */

        //  java.io.FileNotFoundException，文件不存在
//        FileOutputStream fos = new FileOutputStream("/user/1.txt");

//        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //  java.text.ParseException，解析字符串格式不正确
//        Date date = fmt.parse("2066/06/06");

        // java.lang.InterruptedException，
//        Thread.sleep(1000);

        // java.lang.ClassNotFoundException，不存在类
//        Class cls = Class.forName("Dog");
        // java.lang.InstantiationException，没有无参构造方法
        // java.lang.IllegalAccessException，没有权限访问构造方法
//        cls.newInstance();

        /*
         👉 常见的非检查型异常
         */
        // --- 1、Error
//        System.out.println(1);
//        for (int i = 0; i < 200; i++) {
//            // java.lang.OutOfMemoryError，内存不够用异常
//            long[] a = new long[10_0000_0000];
//        }
//        System.out.println(2);


        // java.lang.StackOverflowError，栈内存溢出异常
//        test();


        // --- 2、RuntimeException
//        StringBuilder str = null;
//        // java.lang.NullPointerException，空指针异常
//        str.append("abc");


//        // java.lang.NumberFormatException，数字的格式不对
//        Integer i = new Integer("abc");
//        System.out.println(i);

//        int[] array = {11, 22, 33};
//        // java.lang.ArrayIndexOutOfBoundsException，数组越界异常
//        array[4] = 44;

//        Object obj = "123.4";
//        // java.lang.ClassCastException，类型不匹配异常
//        Double d = (Double) obj;

    }

    public static void test() {
        test();     // 无限递归
    }
}
