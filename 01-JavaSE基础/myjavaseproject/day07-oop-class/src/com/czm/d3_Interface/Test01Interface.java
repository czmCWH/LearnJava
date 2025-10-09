package com.czm.d3_Interface;

public class Test01Interface {

    /*
     1、接口（Interface）
      API（Application Programming Interface）应用编程接口，提供给开发者调用的一组功能（无需提供源代码）。

      Java 中的接口是指：一系列方法声明的集合；用来定义规范、标准；

     2、接口中可以定义的内容
      接口可以定义：抽象方法、常量（static final）、嵌套类型，从 Java8 开始可以定义：默认方法、静态方法（类方法）。
        上述可以定义的内容都是隐式 public 的，因此可以省略 public 关键字；
        从 Java 9 开始可以定义 private 方法；
      常量可以省略 static final。
      抽象方法可以省略 abstract。
      不能自定义构造方法、不能定义（静态）初始化块、不能实例化。

     3、接口的细节
      a、接口名称可以在任何使用类型的地方使用。
      b、一个类可以通过 implements 关键字实现一个或多个接口。
        实现接口的类必须实现接口中定义的所有抽象方法，除非它是一个抽象类；
        如果一个类实现的多个接口中有相同的抽象方法，只需要实现此方法一次；
        extends 和 implements 可以一起使用，implements 必须写在 extends 的后面；
        当父类、接口中的方法签名一样时，那么返回值类型必须一样；
      c、一个接口可以通过 extends 关键字继承一个或者多个接口。
        当多个父接口中的方法签名一样时，那么返回值类型也必须一样；

     4、Java 面向接口编程

     */

    public static void main(String[] args) {
        Child ch = new Child("小明");
        ch.setTeach(new Teahcer());
        ch.study();

        System.out.println("--- 给孩子换一个家教！");
        ch.setTeach(new CollegeStudent());
        ch.study();
    }
}
