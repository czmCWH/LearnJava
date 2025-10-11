package com.czm.d2_reflect;

public class Test1Class {
    /*
     1、反射 Reflection
      反射就是：加载类，并允许以编程的方式解剖类中的各种成分（成员变量、方法、构造器等）。
      反射很抽象，它是大型框架底层原理所涉及到的技术。

      反射应用的场景：如在 IDEA 中在对象后面敲 . 时，该对象的成员信息会下拉显示出来。

     2、反射学什么?
        学习获取类的信息、操作它们
        1、反射第一步：加载类，获取类的字节码：Class对象
           加载类是指把 类.class 文件加载到 内存中，会在内存中产生一个 Class对象。
        2、获取类中的成分、并对其进行操作：
           获取类的构造器：Constructor对象
           获取类的成员变量：Field对象
           获取类的成员方法：Method对象

     3、获取 Class 对象的三种方式
        注意：无论通过那种方式获取的 Class 对象多少次，得到的都是相同的 Class 对象。
        ⚠️⚠️⚠️
            == 一般是比较地址；
            equals 一般通过重写来比较内容；
     */
    public static void main(String[] args) throws Exception {

        // 获取 Class 对象（或 获取类本身） 有三种方式：

        // 方式1，类名.class
        Class c1 = Student.class;
        System.out.println(c1);

        // 方式2，Class.forName("类的全类名")
        Class c2 = Class.forName("com.czm.d2_reflect.Student");
        System.out.println(c2);

        // 方式3：对象.class
        Student s1 = new Student();
        Class c3 = s1.getClass();
        System.out.println(c3);

        System.out.println("--- 获取类对象的全类名：" + c1.getName());
        System.out.println("--- 获取类对象的类名：" + c1.getSimpleName());

        // 类对象在 内存中只会加载一个，所以 c1、c2、c3 都指向同一个类对象
        System.out.println(c1 == c2);   // true
        System.out.println(c2 == c3);   // true

    }
}
