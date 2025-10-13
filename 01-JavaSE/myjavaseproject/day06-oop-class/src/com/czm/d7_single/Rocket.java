package com.czm.d7_single;

/**
 * 定义一个类，实现单例模式 - 饿汉式
 * 饿汉式：在获取单例对象时，对象已经创建好了
 * 应用场景： 频繁使用的对象
 */
public class Rocket {
    // 私有的静态变量存储唯一的实例
    private static Rocket rocket = new Rocket();

    // 1、构造方法私有化
    private Rocket() {
        System.out.println("--- 初始化了 Rocket");
    }

    // 2、提供公共静态方法，返回唯一的那个实例
    public static Rocket getInstance() {
        return rocket;
    }

}
