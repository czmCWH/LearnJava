package com.czm.d4_abstract;

public class B extends A {

    @Override
    public void eat() {
        System.out.println("--- 实现抽象方法 eat");
    }

    @Override
    public void run() {
        System.out.println("--- 实现抽象方法 run");
    }
}
