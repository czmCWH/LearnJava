package com.czm.d7_single;

/**
 * 定义一个类，实现单例模式 - 懒汉式
 * 懒汉式：第一次拿对象时，才开始创建对象。
 * 应用场景：不频繁使用的对象，避免占用内存
 */
public class Rocket2 {
    private Rocket2() {
        System.out.println("--- 初始化了 Rocket2");
    }

    // 私有的静态变量存储唯一的实例
    private static Rocket2 rocket = null;

    /**
     * 这个方法存在非线程安全问题！
     */
    public static Rocket2 getInstance() {
        if (rocket == null) {
            rocket = new Rocket2();
        }
        return rocket;
    }
}
