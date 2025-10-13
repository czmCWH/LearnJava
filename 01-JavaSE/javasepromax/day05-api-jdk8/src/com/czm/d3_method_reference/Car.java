package com.czm.d3_method_reference;

public class Car {
    private String name;

    public Car() {
    }

    public Car(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" + "name='" + name + '\'' + '}';
    }
}

// 定义一个函数式接口
@FunctionalInterface
interface CreateCar {
    Car create(String name);
}