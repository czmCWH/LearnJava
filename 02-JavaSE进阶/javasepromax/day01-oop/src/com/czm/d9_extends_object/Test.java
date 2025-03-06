package com.czm.d9_extends_object;



public class Test {
    /*
     1、Java 类的单继承
        Java 是单继承的，java 中的类不支持多继承，但是支持多层继承

     2、为什么不支持多继承？
        因为如果一个类继承多个类，这时多个类中存在相同类型的方法，则子类无法判断该继承那个方法。
        这样会造成歧义。

     3、Object类
        object 类是 java 所有类的祖宗类。我们写的任何一个类，其实都是 object 的子类或子孙类。
     */
    public static void main(String[] args) {
        A a = new A();
        a.toString();   // toString 是 Object 类中的方法。
    }
}
