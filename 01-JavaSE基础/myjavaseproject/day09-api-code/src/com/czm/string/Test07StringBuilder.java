package com.czm.string;

public class Test07StringBuilder {

    public static void main(String[] args) {

        /*
         1、StringBuilder 类
             a、在进行大量字符串的改动操作时(比如 拼接、替换)
                使用 String 会非常消耗内存、降低程序性能；
                使用 StringBuilder 可以节省内存、提高程序性能；
             b、StringBuilder 的常用方法有：append、insert、delete、replace、reverse等
             c、⚠️⚠️⚠️
                StringBuilder 并不是 String 的子类 或者 父类；
                StringBuilder、String 都实现了 CharSequence 接囗；因此许多方法参数类型会使用 CharSequence 接口
         */

        // 1、为什么使用 String 进行拼接、替换操作时会耗内存？
        // 如下所示 s1 变量进行操作时，会不断的产生新的对象，这样非常消耗内存。另外，开辟/销毁新堆空间会消耗性能。
        String s1 = "abc";  // 在堆内存中创建一个对象 "abc"
        s1 += "def";    // 创建对象 "abcdef"
        s1 += "ghi";    // 创建对象 "abcdefghi"
        System.out.println("s1 = " + s1);

        StringBuilder sb = new StringBuilder("abc");
        sb.append("def").append("ghi");
        System.out.println("sb = " + sb);

        // 2、String 与 StringBuilder 操作大量字符串性能对比
        testString();
        testStringBuilder();

        // 3、CharSequence 字符序列接口

        /*
          4、StringBuilder 的 append 原理 ⚠️
          StringBuilder 使用 char[] 数组来存储字符串。数组的默认容量是16，后续 append 方法修改时会创建 新容量的字符数组，
          新容量的长度是 原来容量*2 + 2，如：16扩容至34、34扩容至70、70扩容至142...。

          StringBuilder 只有在真正需要开辟堆空间的时候才会进行，不会浪费内存空间。
         */

    }

    static void testString() {
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < 10000; i++) {
            s = s + i;
        }
        long end = System.currentTimeMillis();
        System.out.println(" String Time = " + (end - start));
    }

    static void testStringBuilder() {
        long start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < 10000; i++) {
            sb.append(i);
        }
        long end = System.currentTimeMillis();
        System.out.println(" StringBuilder Time = " + (end - start));
    }
}
