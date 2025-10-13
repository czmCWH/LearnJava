package com.czm.d10_interface_attention;

public class Test2 {

    // 3、一个类继承了父类，又同时实现了接口，父类中接口中有同名的默认方法，实现类会优先用父类的。
    // 这种情况在 JDK21 中已经处理必须实现接口中的方法

    public static void main(String[] args) {
        Dog d = new Dog();
        d.run();
    }
}

class Animal {
    void run() {
        System.out.println("--- 动物跑的快！");
    }
}

interface Sport {
    default void run() {
        System.out.println("---- 接口中跑的快！");
    }
}

class Dog extends Animal implements Sport {

    @Override
    public void run() {
        // 调用父类的方法
        super.run();
        // 调用接口的方法
        Sport.super.run();
    }
}