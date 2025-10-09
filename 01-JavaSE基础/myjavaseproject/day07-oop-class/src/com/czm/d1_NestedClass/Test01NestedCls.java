package com.czm.d1_NestedClass;

import com.czm.d1_NestedClass.Person.Hand;

public class Test01NestedCls {

    /*
      1、嵌套类 Nested Class
       嵌套类：定义在另一个类中的类；

       在嵌套类外层的类，称为：外部类（Outer Class）
       最外层的外部类，称为：顶级类（Top-level Class）

      2、内部类 Inner Class
       内部类：是指没有被 static 修饰的嵌套类，即非静态嵌套类。

       跟实例变量、实例方法一样，内部类 与 `外部类的实例` 相关联。其特点如下：
          a、必须先创建外部类实例，然后再用外部类实例创建内部类实例。
          b、内部类不能定义除 编译时常量 以外的任何 static 成员。
          c、内部类可以直接访问外部类中的所有成员（即使被声明为 private）。
          d、外部类可以直接访问内部类实例的成员变量、方法（即使被声明为 private）。

       ⚠️ 如果必须先有 C实例 才能创建 A实例，那么可以将 A 设计为 C 的内部类。

      3、内部类的内存细节
       内部类的对象中会有一个指针指向其外部类的对象，如果某一个内部类的对象不释放内存，其内部类的对象是不会释放的。
       只有当所有内部类的对象释放后，其外部类的对象才可以被释放。

     */

    public static void main(String[] args) {

        Person p = new Person("张三");
        // 使用 p 对象实例化内部类
        Hand hand = p.new Hand(10, 123);
        hand.printName();
        p.printHand(hand);


        // 内部类访问外部类成员的细节：
        new OuterClass().new InnerClass().print();

    }

}
