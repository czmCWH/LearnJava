package com.czm.d4_polymorphism;

public class Dog extends Animal implements Eatable {
    @Override
    public void speak() {
        System.out.println("Dog --- wang wang!");
    }

    @Override
    public void eat() {
        System.out.println("Dog --- 吃骨头！");
    }

    public static void sleep() {
        System.out.println("Dog --- 趴着睡！");
    }

    public int age = 2;
    public int getDAage() {
        return age;
    }
}
