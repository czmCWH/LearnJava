package com.czm.d6_interface_x;

public class StudentImpl implements A, B {

    @Override
    public void run() {
        System.out.println("--- 学生奔跑");
    }

    @Override
    public void go() {
        System.out.println("--- 学生外出了");
    }

    @Override
    public void eat() {
        System.out.println("--- 学生吃饭了");
    }
}
