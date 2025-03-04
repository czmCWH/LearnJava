package com.czm.string;

public class StringDemo1 {
    /*
     java.lang.String 表示字符串类，它封装字符串数据，提供了处理字符串的方法

     1、String创建对象封装字符串数据的方式
     */
    public static void main(String[] args) {
        // 方式一:
        // Java 程序中的所有字符串文字(例如“abc”)都为此类的对象
        String dog = "小黑狗";
        String p = "小明";

        // 方式二：调用String类的构造器初始化字符串对象。（了解）

        // public String()		// 创建一个空白字符串对象，不含有任何内容
        String s1 = new String();   // s1 == ""
        System.out.println(s1.length());

        // public String(string original)	// 根据传入的字符串内容，来创建字符串对象
        String s2 = new String("春天春天");
        System.out.println(s2);

        // public String(char[] chars)		// 根据字符数组的内容，来创建字符串对象
        char[] chars = {'a', 'b', 'c'};
        String s3 = new String(chars);
        System.out.println(s3);     // 打印：abc

        // public String(byte[] bytes)		// 根据字节数组的内容，来创建字符串对象
        byte[] b = { 97, 98, 65, 66};
        String s4 = new String(b);
        System.out.println(s4);     // 打印：abAB
    }
}
