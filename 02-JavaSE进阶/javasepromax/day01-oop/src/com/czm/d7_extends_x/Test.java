package com.czm.d7_extends_x;

public class Test {
    /*
     1、继承
        Java 中提供了一个关键字 extends，用这个关键字，可以让一个类和另一个类建立起父子关系。
        继承语法：
            public class B extends A {
	        }
            B 称为 子类、派生类
            A 称为 父类、基类、超类

     2、继承特点
        子类能继承父类的非私有成员(成员变量、成员方法)
        继承后对象的创建，子类的对象是由子类、父类共同完成的。（即：带继承关系的类，java会用类和其父类，这多张设计图来一起创建类的对象。）

     3、继承的好处
        减少重复代码的编写。
     4、
     */
    public static void main(String[] args) {
        B b = new B();
        System.out.println(b.i);
//        System.out.println(b.j);  // 报错：无法访问
//        System.out.println(b.k); // 报错：无法访问
        b.print1();
//        b.print2();   //  报错：无法访问
        b.print3();
    }
}
