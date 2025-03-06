package com.czm.d15_integer;

import java.util.Objects;

public class Test {
    /*
     1、为什么要有包装类?
	    包装类就是把基本类型的数据包装成对象。
        核心目的：由于泛型和集合都不支持基本数据类型，因为万物皆对象，因此包装类在集合和泛型中大量使用而且是必须使用。

        基本数据类型：
	        byte、short、int、long、char、float、double、boolean
        对应的包装类（引用数据类型）：
	        Byte、Short、Int、Long、Char、Float、Double、Boolean
	  2、包装类的其他常见操作
        a、可以把基本类型的数据转换成字符串类型
            public static String toString(T x);
            public String toString();

        b、可以把字符串类型的数值转换成数值本身对应的数据类型（很有用）
            public static int parseInt(String x);
            public static Integer valueOf(String str);
     */
    public static void main(String[] args) {
        int a = 18;

        // 1、把基本类型通过包装类包装成对象。
        Integer a1 = new Integer(a);

        Integer a2 = Integer.valueOf(a);    // 手动包装

        // 2、自动装箱机制：基本类型的数据可以直接变成对象赋值给包装类。
        Integer t1 = 128;
        Integer t2 = 128;
        System.out.println("--- 128超出缓存池范围，会创建新的对象 = " + (t1 == t2));   // 打印：false
        System.out.println("---- equals = " + t1.equals(t2));
        System.out.println("---- Objects.equals = " + Objects.equals(t1, t2));

        // 3、自动拆箱机制：包装类的对象可以直接赋值给基本数据类型。
        int t11 = t1;
        int t12 = t2;
        System.out.println(t11 == t12);     // 打印：true

        System.out.println("================= 包装类的其他常见操作 ==================");
        int n1 = 23;
        String str = Integer.toString(n1);
        System.out.println(str + 1);    // 打印："231"

        Integer ni1 = n1;
        String str2 = ni1.toString();
        System.out.println(str2 + 1);    // 打印："231"

        String str3 = n1 + "";
        System.out.println(str3 + 1);     // 打印："231"

        System.out.println("-------- 包装类操作2 ------");
        String scoreStr = "99.123123";
        // 方法1:
//        double score = Double.parseDouble(scoreStr);
        // 方法2:
        double score = Double.valueOf(scoreStr);
        System.out.println(score);      // 打印：99.123123
        System.out.println(score + 0.5);      // 打印：99.623123

    }
}
