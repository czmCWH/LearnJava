package com.czm.d5_static_singleInstance;

// 饿汉式单例模式：在获取单例对象时，对象已经创建好了
public class Single {
    // 2、私有静态变量存储唯一个对象
    private static Single instance = new Single();
    // 1、私有化构造器
    private Single() {}
    // 3、静态方法公开静态变量
    public static Single getInstance() {
        return instance;
    }
}
