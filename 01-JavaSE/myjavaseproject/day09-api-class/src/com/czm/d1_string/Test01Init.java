package com.czm.d1_string;

public class Test01Init {
    /*
     1、String 类
     Java 中使用 java.lang.String 类表示字符串，它封装字符串数据，提供了处理字符串的方法。

        Java 8底层使用 char[]存储字符数据，从 Java 9开始，底层使用 byte[]存储字符数据。
        所有字符串字面量(比如："abc"、"123"、"你好")都是 String 类的实例(对象)。
        ⚠️ String 对象一旦创建完毕，它的字符内容是不可以修改的。比如："123" 对象，它存储在堆空间，它的内容123是不可修改的。因此 String 被称为不可变字符串对象

     */
    public static void main(String[] args) {
        // 1、初始化一个 String

        // 方式一：字面量方式初始化
        String str = "abc";     // Java 底层使用 char[] array = {'a', 'b', 'c'};
        String dog = "小黑狗";

        // 方式二：调用String类的构造器初始化字符串对象。（了解）
        // public String()		// 创建一个空白字符串对象，不含有任何内容
        String s1 = new String();   // s1 == ""
        System.out.println("--- s1 = " + s1.length());

        // public String(string original)	// 根据传入的字符串内容，来创建字符串对象
        String s2 = new String("春天春天");
        System.out.println("--- s2 = " + s2);

        // public String(char[] chars)		// 根据字符数组的内容，来创建字符串对象
        char[] chars = {'a', 'b', 'c'};
        String s3 = new String(chars);
        System.out.println("--- s3 = " + s3);     // 打印：abc

        // public String(byte[] bytes)		// 根据字节数组的内容，来创建字符串对象
        byte[] b = { 97, 98, 65, 66};
        String s4 = new String(b);
        System.out.println("--- s4 = " + s4);     // 打印：abAB


        String s = "555";   // 栈空间中的 s 局部变量存储指向堆空间的引用，堆空间存放 "555" 对象。
        s += "，555";    // s 变量存储的引用，指向堆空间的新对象 "555，555"
        s += "，666";    // s 变量存储的引用，指向堆空间的新对象 "555，555，666"
        test(s);    // ⚠️ Java 函数是值传递，即传递 s 变量的引用。
        System.out.println("--- s = " + s);     // 555，555，666
        s = "666";   // s 变量存储的引用，指向堆空间的新对象 666
        test(s);
        System.out.println("--- s = " + s);     // 666

    }

    // 栈空间的 局部变量 a 接收传递对象的引用
    public static void test(String a) {
        a += "，555";
    }
}
