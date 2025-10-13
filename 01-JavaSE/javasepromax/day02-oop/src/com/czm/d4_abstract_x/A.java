package com.czm.d4_abstract_x;

public abstract class A {
    public String name;
    public Double height;
    public int age;

    public A() {

    }

    public A(String name, Double height, int age) {
        this.name = name;
        this.height = height;
        this.age = age;
    }

    // 定义抽象方法，只有方法签名，不能有方法体。
    public abstract void eat();
    public abstract void run();

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
