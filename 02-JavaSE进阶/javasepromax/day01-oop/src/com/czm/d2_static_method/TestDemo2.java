package com.czm.d2_static_method;

public class TestDemo2 {
    /*
     1、类方法(静态方法)应用场景
        类方法最常见的应用场景是做工具类。

     2、工具类
        工具类中的方法都是一些类方法，每个方法都是用来完成一个功能的，工具类是给开发人员共同使用的。
        工具类中不使用实例方法，因为实例方法需要创建对象来调用，会浪费内存。
        工具类没有创建对象的需求，建议将工具类的构造器私有；

     3、使用类方法来设计工具类的好处
        提高了代码复用；方便调用，提高了开发效率。
     4、
     */
    public static void main(String[] args) {
        System.out.println("--- 工具类生成短信验证码：");
        System.out.println(ZmUtil.createCode(8));
    }
}
