package com.czm.d5_static_singleInstance;

// 懒汉式单例模式：第一次拿对象时，才开始创建对象。
public class Single2 {
    // 2、静态变量记住类的唯一对象
    private static Single2 instance;
    // 1、私有构造器
    private Single2() {}
    // 3、静态方法返回唯一对象
    public static Single2 getInstance() {
        if (instance == null) {
            instance = new Single2();
        }
        return instance;
    }
}
