package com.czm.d5_static_final;

public class Test01Static {

    /*
     1、static
      static 常用来修饰类的成员：成员变量、方法、嵌套类。

      a、成员变量
        被 static 修饰的成员变量称为：类变量、静态变量、静态字段，其特点：
          ⚠️ 在程序运行过程中只占用一份固定的内存（存储在方法区）；
          可以通过任意实例、类访问；

        没有被 static 修饰的成员变量称为：实例变量，其特点：
          在每个实例内部（堆空间）都有一份内存；
          只能通过实例访问，不可以通过类访问；

      b、方法
        被 static 修饰的方法称为：类方法、静态方法，其特点：
          可以通过实例、类访问；
          内部不可以使用 this；
          可以直接访问类变量、类方法；
          不可以直接访问实例变量、实例方法；

        没有被 static 修饰的方法称为：实例方法，其特点：
          只能通过实例访问，不可以通过类访问；
          内部可以使用this；
          可以直接访问实例变量、实例方法；
          可以直接访问类变量、类方法；

     2、使用 static 注意点
      不推荐使用实例访问类变量、类方法；
      在同一个类中，不能有同名的实例变量和类变量，不能有相同签名的实例方法和类方法。

      方法签名 = 方法名 + 方法参数类型

     */

    public static void main(String[] args) {
        Person.count = 10;

        // ⚠️，虽然可以通过实例访问 static 成员变量，但是不推荐！所有有警告
        Person p1 = new Person();
        p1.count = 20;

        Person p2 = new Person();
        System.out.println(p2.count);   // 20，
        System.out.println(Person.count);   // 20

        Person.test();
        p1.test();

    }
}
