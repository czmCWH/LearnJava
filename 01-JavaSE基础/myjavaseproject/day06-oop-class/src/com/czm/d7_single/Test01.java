package com.czm.d7_single;

public class Test01 {
    /*
      1、单例模式（Singleton Pattern）
       如果一个类设计成单例模式，那么在程序运行过程中，这个类只能创建一个实例。

      2、定义单例的方式：
        饿汉式 - 常用；
        懒汉式 - 存在线程安全；

     */
    public static void main(String[] args) {

        Rocket r1 = Rocket.getInstance();
        Rocket r2 = Rocket.getInstance();
        System.out.println(r1 == r2);   // true
        System.out.println(r1.equals(r2));  // true

    }
}
