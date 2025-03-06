package com.czm.d1_polymorphic;

public class Dog extends Animal {

    public String name = "小狗";

    @Override
    public void cry() {
        System.out.println("--- 狗，汪汪汪");
    }

    public void dogWatchDoor() {
        System.out.println("狗看门！");
    }
}
