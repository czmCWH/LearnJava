package com.czm.d5_static_singleInstance;

public class Test1 {
    /*
     1、设计模式
	一个问题通常有n种解法，其中肯定有一种解法是最优的，这个最优的解法被人总结出来了，称之为设计模式。

	设计模式有23种，对应23种软件开发中会遇到的问题。

	设计模式学习点：解决什么问题？设计模式怎么写？

     2、单例设计模式
	    单例设计模式：确保一个类只有一个对象。

	    实现步骤：
	        1、饿汉式单例模式 --- 频繁使用的对象
		        a、把类的构造器私有。
		        b、定义一个私有类变量存储类的唯一一个对象。
		        c、定义一个类方法，返回该对象。
		    2、懒汉式单例模式 --- 不频繁使用的对象，避免占用内存
		        a、把类的构造器私有。
                b、定义一个类变量用于存储对象。
                c、提供一个类方法，保证返回的是同一个对象。

     3、应用场景
        例如：任务管理器对象、获取运行时对象。

     */
    public static void main(String[] args) {
        Single s1 = Single.getInstance();
        Single s2 = Single.getInstance();
        System.out.println(s1);
        System.out.println(s2);
    }
}
