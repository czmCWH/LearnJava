package com.czm;

/*
1、Java 开发语法须知
    每一条语句都必须以分号;结尾。
    Java 中的方法，就是其它编程语言中的函数。
    程序的入口是 main 方法。没有 main 方法，Java 程序是无法启动的。

    方法必须包含在 class 内部，先有 class，再有方法。
    public class 的名称必须要和文件名保持一致。
    方法左大括号位置推荐写在方法定义的一行，而不是换行书写。

2、注释
Java 的注释有3种书写格式：
    单行注释
    多行注释
    文档注释

⚠️：生成文档说明书：`javadoc -d 文件夹 -encoding [代码编码] 程序名.java`

3、Java 中打印输出快捷书写：sout
 */

// 这是单行注释
/*
这是多行注释
 */
/**
 * 文档注释
 * 一般用在类、方法、成员变量上，且在其上的注释内容会被提取到程序说明文档中去
 */


public class HelloDemo {
    // main 方法，程序的入口。
    // 如果一个 Java 项目中有2个不同的 class，他们都有自己的 main 方法，只能选择其中一个入口开始执行程序。
    public static void main(String[] args) {
        System.out.println("hello world!");
    }
}
