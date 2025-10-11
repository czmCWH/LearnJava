package com.czm.d2_innerClass_x;

public class Test {
    /*
     1、静态内部类【了解】
        有 static 修饰的内部类，属于外部类自己持有，
        创建静态内部类对象语法：
            Outer.Inner in = new Outer.Inner();

     2、静态内部类访问外部类成员的特点：
        a、静态内部类中可以访问外部类的静态成员。
        b、静态内部类不可以访问外部的实例成员。
            因为静态内部类属于外部类持有，它没有外部类对象，所以无法获取外部类对象的信息。

     3、
     */
    public static void main(String[] args) {
        // 1、创建静态内部类
        Outer.Inner in = new Outer.Inner();
        in.showSome();
    }
}
