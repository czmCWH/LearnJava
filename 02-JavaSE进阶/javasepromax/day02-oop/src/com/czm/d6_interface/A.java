package com.czm.d6_interface;

// 定义接口A
public interface A {
    // JDK 8之前接口中只能定义常量
//    public static final String HOME_PATH = "/c/my/doc";
    // 接口中定义常量，可以省略 public static final，默认会加上
    String HOME_PATH = "/c/my/doc";

    // 接口中定义抽象方法可以省略 public abstract
//    public abstract void run();
    void run();
    void go();
}
