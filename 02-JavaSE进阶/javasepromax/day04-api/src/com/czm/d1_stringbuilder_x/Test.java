package com.czm.d1_stringbuilder_x;

public class Test {
    /*
     1、StringBuilder
        代表可变字符串对象，相当于是一个容器，它里面装的字符串是可以改变的，就是用来操作字符串的。
        好处：StringBuilder 比 String 更适合做字符串的修改操作，效率会更高，代码也会更简洁。

     2、为啥操作字符串用 StringBuilder ？
        因为 StringBuilder 拼接字符串比 String 性能高。
        ⚠️：对于字符串相关的操作，如频繁的拼接、修改等，建议用StringBuidler，效率更高!

     3、StringBuffer 与 StringBuilder
        StringBuffer 的用法与 StringBuilder 是一模一样的。
        但 StringBuilder 是线程不安全的 StringBuffer 是线程安全的。
        开发中一般使用 StringBuilder，不用考虑 线程安全。
     */
    public static void main(String[] args) {

        System.out.println("--------- 1、创建 StringBuilder  对象");
        StringBuilder sb1 = new StringBuilder();    // 空字符串
        StringBuilder sb2 = new StringBuilder("好好学习java");
        System.out.println(sb1);
        System.out.println(sb2);

        System.out.println("--------- 2、操作 StringBuilder 的内容");
        System.out.println("--- 拼接");
        sb2.append("，超越自我").append("，理解更好的开发思想！");
        System.out.println(sb2);

        System.out.println("--- 反转内容 = " + sb2.reverse());
        System.out.println("--- 内容长度 = " + sb2.length());
        System.out.println("--- 转换为 String = " + sb2.toString());
        // ⚠️：StringBuilder 是拼接字符串的手段，String才是开发中的目的。
        // 开发中通常用 String 接收数据，因为 它可以直接接收 “” 的字符串常量。

        System.out.println("--------- 3、StringBuilder 与 String 性能比较");
//        String s = "";
//        for (int i = 0; i < 3; i++) {
//            s += "abc";       // 每次都会在堆内存中创建新的对象，因此会影响性能
//        }
//
//        StringBuilder sbContainer = new StringBuilder();
//        for (int i = 0; i < 3; i++) {
//            sbContainer.append("abc");    // 不会创建新的对象，只是向容器里添加内容。
//        }
    }
}
