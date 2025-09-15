package com.czm.string;

public class Test02SCP {
    public static void main(String[] args) {

        /*
         1、字符串常量池 (String Constant Pool)
         Java 中有个字符串常量池（String Constant Pool，简称 SCP），存放字符串常量。
         SCP 以前放在方法区，从 Java 7开始 SCP 属于堆空间的一部分。

         当使用字符串字面量时，会先查看 SCP。
            如果 SCP 中存在与字面量内容一样的字符串对象 A 时，就返回 A；
            否则，创建一个新的字符串对象 D，并加入到 SCP 中，返回 D；

         而通过 new 方式创建字符串对象，每new一次都会产生一个新的对象放在堆内存中。
         */
        String s1 = "abc123";
        String s2 = "abc123";
        String s6 = "abc" + "123";  // Java存在编译优化机制，程序在编译时:会直接把 字面量字符串相加表达式 转换为 字面量字符串，以提高程序的执行性能
        String s3 = new String(s1);   // new 会从堆空间分配一块新内存
        System.out.println(s1 == s2);       // true
        System.out.println("--- s1 == s6：" + (s1 == s6));   // true
        System.out.println(s1 == s3);       // false

        char[] chars = {'a', 'b', 'c', '1', '2', '3'};
        String s4 = new String(chars);
        String s5 = new String(s4);
        System.out.println(s3 == s4);       // false

        /*
         == 比较变量存储的值是否相等。
         equals 方法比较对象的值是否相等
         */
        System.out.println("--- s1.equals(s3) = " + s1.equals(s3));     // true
        System.out.println("--- s1.equals(s4) = " + s1.equals(s4));     // true


        /*
         上述所示变量，s1、s2、s3 指向对象的 value 是同一个。s4、s5 指向对象的 value 是同一个。
         验证方式：通过在随意变量上添加断点，然后点击右上角【爬虫icon】调试，可知它们 value 的ID 是一样的。
         */

    }
}
