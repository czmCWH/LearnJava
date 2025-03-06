package com.czm.d7_extends;

public class B extends A {

    private int k;

    public void print3() {
        System.out.println("======print3======");
        print1();
    }

    public B() {
        System.out.println("===B的构造器被调用===");
    }
}
