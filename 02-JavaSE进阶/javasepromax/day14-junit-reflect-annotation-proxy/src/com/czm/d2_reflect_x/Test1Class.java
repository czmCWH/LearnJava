package com.czm.d2_reflect_x;

public class Test1Class {
    /*
     1、反射(Reflection)
        反射就是：加载类，并允许以编程的方式解剖类中的各种成分(成员变量、方法、构造器等)。

     2、反射学什么?
        学习获取类的信息、操作它们
        1、反射第一步：加载类，获取类的字节码：Class对象
        2、获取类的构造器：Constructor对象
        3、获取类的成员变量：Field对象
        4、获取类的成员方法:Method对象

     3、获取 Class 对象的三种方式
        注意：无论通过那种方式获取的 Class 对象多少次，得到的都是相同的 Class 对象。
        ⚠️⚠️⚠️
            == 一般是比较地址；
            equals 一般通过重写来比较内容；
     */
    public static void main(String[] args) throws Exception {
        // 获取 Class 对象的三种方式

        System.out.println("--- 方式1：类名.class");
        Class c1 = Student.class;
        System.out.println(c1);

        System.out.println("--- 方式2：Class.forName");
        Class c2 = Class.forName("com.czm.d2_reflect_x.Student");
        System.out.println(c2);

        System.out.println("--- 方式3：对象.class");
        Student s1 = new Student();
        Class c3 = s1.getClass();
        System.out.println(c3);

        System.out.println();
        System.out.println(c1 == c2);
        System.out.println(c2 == c3);


    }
}
