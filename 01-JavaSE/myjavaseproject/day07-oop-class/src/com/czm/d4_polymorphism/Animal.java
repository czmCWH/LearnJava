package com.czm.d4_polymorphism;

public class Animal {
    public void speak() {
        System.out.println("Animal --- speak");
    }

    public static void sleep() {
        System.out.println("Animal --- sleep");
    }

    public int age = 1;
    public int getAAage() {
        return age;
    }
}
