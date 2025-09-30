package com.czm.d7_single;

/**
 * 定义一个类，实现单例模式 - 懒汉式
 */
public class Rocket2 {
    private Rocket2() {
        System.out.println("--- 初始化了 Rocket2");
    }

    /**
     * 这个方法存在非线程安全问题！
     */
    public static Rocket2 getInstance() {
        if (rocket == null) {
            rocket = new Rocket2();
        }
        return rocket;
    }

    // 私有的静态变量存储唯一的实例
    private static Rocket2 rocket = null;
}
