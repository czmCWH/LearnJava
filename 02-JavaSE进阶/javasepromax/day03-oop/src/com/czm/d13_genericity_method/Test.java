package com.czm.d13_genericity_method;

import java.util.ArrayList;

public class Test {
    /*
     1、通配符
	    就是“?”，可以在“使用泛型”的时候代表一切类型。
	    E T K V 是在定义泛型的时候使用。

     2、泛型的上下限:
        泛型上限（? extends Car）：? 能接收的必须是car或者其子类;
        泛型下限（? super Car）：? 能接收的必须是Car或者其父类;

     3、泛型的擦除问题和注意事项
    	a、泛型是工作在编译阶段的，一旦程序编译成 class 文件，class 文件中就不存在泛型了，这就是泛型擦除。
	    b、泛型不能直接支持基本数据类型，只能支持对象类型(引用数据类型)。

     */
    public static void main(String[] args) {
        ArrayList<TSL> list1 = new ArrayList<>();
        list1.add(new TSL());
        list1.add(new TSL());

        ArrayList<LX> list2 = new ArrayList<>();
        list2.add(new LX());
        list2.add(new LX());

        // 1、如下方式会报错：
        // 原因：⚠️虽然LX和TSL是Car的子类，但是 ArrayList<TSL>和 ArrayList<LX> 与 ArrayList<car> 没有半毛钱关系。
//        drive(list1);
//        drive(list2);

        System.out.println("=============泛型通配符============");
        // 2、使用泛型时用通配符解决
        drive2(list1);
        drive2(list2);

        ArrayList<Dog> list3 = new ArrayList<>();
        list3.add(new Dog());
        drive2(list3);
        System.out.println("=============泛型上限============");
        // 3、泛型上限
        drive3(list1);
        drive3(list2);
//        drive3(list3);  // 报错
    }

    // 使用泛型上限 ? extends Car，接收类型必须是 Car、或者 Car 的子类
    public static void drive3(ArrayList<? extends Car> list) {
        System.out.println("---开车");

    }

    // 使用 通配符 ?，表示【在使用泛型的时候】代表一切类型。E、T、K、V 是定义时使用
    public static void drive2(ArrayList<?> list) {
        System.out.println("---开车");

    }

    public static void drive(ArrayList<Car> list) {
        System.out.println("---开车");

    }
}
