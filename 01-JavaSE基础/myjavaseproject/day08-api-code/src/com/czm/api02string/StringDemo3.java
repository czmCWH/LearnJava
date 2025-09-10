package com.czm.api02string;

public class StringDemo3 {
    /*
     1、String 使用注意事项

        String 对象的内容不可改变，被称为不可变字符串对象。
            因为: 每次试图改变字符串对象实际上是新产生了新的字符串对象了，变量每次都是指向了新的字符串对象，
                  之前字符串对象的内容确实是没有改变的，因此说String的对象是不可变的。


        只要是以 "xxx" 双引号方式写出的字符串对象，会存储到堆内存的字符串常量池中，且相同内容的字符串只存储一份;
        但通过 new 方式创建字符串对象，每new一次都会产生一个新的对象放在堆内存中。
     */
    public static void main(String[] args) {

        System.out.println("--- 以 “xxx” 方式创建的字符串：");
        String s1 = "abc";
        String s2 = "abc";
        System.out.println(s1 == s2);   // 打印：true

        System.out.println("--- 以 new 方式创建的字符串：");
        String s3 = new String("abc");
        System.out.println(s1 == s3);   // 打印：false
        String s4 = new String("abc");
        System.out.println(s3 == s4);   // 打印：false

        System.out.println("--- test1");
        test1();

        System.out.println("--- test2");
        test2();
    }

    public static void test1() {
        String s1 = "abc";
        String s2 = "ab";
        String s3 = s2 + "c";   // 计算出来的放在新的堆内存
        System.out.println(s1 == s3);   // 打印：false
    }

    public static void test2() {
        String s1 = "abc";
        String s2 = "a" + "b" + "c";
        System.out.println(s1 == s2);   // 打印：true
        // Java存在编译优化机制，程序在编译时:会直接把 "a" + "b" + "c" 转成 "abc”，以提高程序的执行性能
    }
}
