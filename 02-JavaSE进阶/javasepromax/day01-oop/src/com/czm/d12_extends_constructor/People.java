package com.czm.d12_extends_constructor;

public class People {
    private String name;
    private int age;
    private double weight;

    public People() {}

    public People(String name, int age, double weight) {
        this.name = name;
        this.age = age;
        this.weight = weight;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
