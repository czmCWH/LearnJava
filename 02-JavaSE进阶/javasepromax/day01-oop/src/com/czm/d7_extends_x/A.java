package com.czm.d7_extends_x;

public class A {
    public int i;
    public void print1() {
        System.out.println("------print1------");
    }

    private int j;
    private void print2() {
        System.out.println("------print2------");
    }

    public A() {
        System.out.println("===A的构造器被调用===");
    }
}
