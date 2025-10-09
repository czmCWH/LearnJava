package com.czm.d4_polymorphism;

public class Cat extends Animal implements Eatable {
    @Override
    public void speak() {
        System.out.println("Dog --- miao miao!");
    }

    @Override
    public void eat() {
        System.out.println("Cat --- 吃老鼠!");
    }
}
