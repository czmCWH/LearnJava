package com.czm.d4_innerClass;

public abstract class Animal {
    public abstract void cry();
}

class Dog extends Animal {
    @Override
    public void cry() {
        System.out.println("--- 狗，汪汪汪！");
    }
}

class Cat extends Animal {
    @Override
    public void cry() {
        System.out.println("--- 猫，喵喵喵！");
    }
}