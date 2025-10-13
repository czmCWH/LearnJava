package com.czm.d5_set_x;

// 让自定义类实现 Comparable 接口，并实现 compareTo 方法。
public class Dog implements Comparable<Dog> {
    private String color;
    private double age;

    public Dog() {}

    public Dog(String color, double age) {
        this.color = color;
        this.age = age;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }

    @Override
    public int compareTo(Dog o) {
        return (int)(o.age - this.age);
    }

    @Override
    public String toString() {
        return "Dog{" +
                "color='" + color + '\'' +
                ", age=" + age +
                '}' + "\n";
    }
}
