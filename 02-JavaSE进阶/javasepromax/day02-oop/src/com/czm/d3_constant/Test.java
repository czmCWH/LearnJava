package com.czm.d3_constant;

public class Test {
    /*
     1、常量
        使用了 static final 修饰的成员变量就被称为常量
        作用：通常用于记录系统的配置信息

     2、使用常量记录系统配置信息的优势、执行原理
        代码可读性更好，可维护性也更好。
        程序编译后，常量会被“宏替换”：出现常量的地方全部会被替换成其记住的字面量。这样可以保证使用常量和直接用字面量的性能是一样的，
     3、
     */
    public static void main(String[] args) {
        System.out.println(Constant.HOST_NAME);
    }
}
