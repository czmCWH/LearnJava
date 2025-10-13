package com.czm.d2_safe;

/**
 * ⚠️ 基于 synchronized 实现 单例模式
 */
public class SingleClass {
    private static SingleClass instance = null;
    private SingleClass() {}

    // synchronized 线程同步，保证线程安全
    public static synchronized SingleClass getInstance() {
        if (instance == null) {
            instance = new SingleClass();
        }
        return instance;
    }
}
