package com.czm.d4_static_code;

import java.util.ArrayList;

public class Test1 {
    /*
     1、代码块
	   ⚠️⚠️⚠️ 代码块是类的5大成分之一(成员变量、构造器、方法、代码块、内部类)。

     2、代码块分类
	    a、静态代码块 --- 使用多
		    语法格式：static {}
		    特点：类加载时自动执行，由于类只会加载一次，所以静态代码块也只会执行一次。
		    作用：完成类的初始化，例如：对类变量（静态变量）的初始化赋值。

    	b、实例代码块 --- 实用性几乎为零
	    	语法格式：{}
		    特点：每次创建对象时，执行实例代码块，并在构造器前执行。
		    作用：和构造器一样，都是用来完成对象的初始化的，例如：对实例变量进行初始化赋值

     3、
     */

    public static String info;
    public static ArrayList<String> list = new ArrayList<>();

    // 定义静态代码块
    static {
        System.out.println("--- 静态代码块");

        // 静态代码块中通常用于初始化静态变量，避免静态变量在实例方法中多次被初始化。
        info = "初始化静态变量";
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
    }

    // 实例代码块
    {
        System.out.println("--- 实例代码块执行了");
    }

    public Test1() {
        System.out.println("=== 构造方法执行了！===");
    }

    public static void main(String[] args) {
        System.out.println("===main方法执行了==");
        new Test1();
        new Test1();
    }
}
