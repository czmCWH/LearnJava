package com.czm.d1_static_field;

// 学习路线：特点、用法、应用场景

public class TestDemo1 {
    /*
     1、static
        static 静态，可以修饰成员变量、成员方法。

     2、成员变量按照有无 static 修饰，分为两种:
	    类变量：也称为静态变量，有 static 修饰，属于类，在计算机里只有一份，会被类的全部对象共享。
	    实例变量(对象变量)：无 static 修饰，属于每个对象的。

     3、静态变量和实例变量在内存的存储
        程序先把定义的类加载到方法区；
        堆内存中开辟中一块区域存放类的静态变量；
        实例化对象时会在堆内存中开辟一块区域存放对象，该区域包含：实例变量、类的地址(指向类所在的方法区)

     4、static成员变量的特点
        类变量：属于类，与类一起加载一次，在内存中只有一份，可以被类和类的所有对象共享。

     5、static成员变量的应用场景
        在开发中，如果某个数据只需要一份，且希望能够被共享(访问、修改)，则该数据可以定义成类变量来记住。

     */

    public static void main(String[] args) {

        Student.name = "好好学习java";
        System.out.println("--- name 静态变量 = " + Student.name);

        // 静态变量也可以用对象访问：对象.类变量 --- 不推荐
        Student s1 = new Student();
        s1.name = "java";
        s1.age = 18;

        Student s2 = new Student();
        s2.name = "python";
        s2.age = 25;

        System.out.println("--- 对象访问静态变量 =" + s1.name);
        System.out.println("--- name 静态变量 = " + Student.name);
        System.out.println();
        System.out.println("--- 实例变量 = " + s1.age);
        System.out.println("--- 实例变量 = " + s2.age);
    }
}
