package com.czm.d1_polymorphic_x;

public class Cat extends Animal {
    public String name = "小猫咪";

    @Override
    public void cry() {
        System.out.println("--- 猫，喵喵喵");
    }

    public void catEatFish() {
        System.out.println("猫吃鱼！");
    }
}
