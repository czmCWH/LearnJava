package com.czm.d9_interface_jdk8_x;

public interface A {
    // 1、默认方法（普通方法），必须用 default 修饰
    public default void run() {
        System.out.println("接口的默认方法，可以写方法的实现");
        go();
    }

    // 2、私有方法
    // 只能在当前接口内部的默认方法、私有方法中调用
    private void go() {
        System.out.println("--- 接口的私有方法");
    }

    // 3、静态方法
    // 用接口名本身来调用
    public static void doSome() {
        System.out.println("--- 静态方法");
    }

}
