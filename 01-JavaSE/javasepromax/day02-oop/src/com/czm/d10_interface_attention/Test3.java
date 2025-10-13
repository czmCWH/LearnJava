package com.czm.d10_interface_attention;

public class Test3 {

    // 4、一个类实现了多个接口，多个接口中存在同名的默认方法，可以不冲突，这个类重写该方法即可。

    public static void main(String[] args) {
        D d = new D();
        d.run();
    }
}

interface A3 {
    default void run() {
        System.out.println("-----A3");
    }
}

interface B3 {
    default void run() {
        System.out.println("-----B3");
    }
}

interface C3 {
    default void run() {
        System.out.println("-----C3");
    }
}

class D implements A3, B3, C3 {

    @Override
    public void run() {
        // 重写方法，调用 B3 接口的方法
        B3.super.run();
    }
}