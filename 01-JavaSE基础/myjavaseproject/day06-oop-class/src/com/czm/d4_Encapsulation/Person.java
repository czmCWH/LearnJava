package com.czm.d4_Encapsulation;

public class Person {
    // 1、成员变量（对象的属性）
    String name;

    // 封装1：合理隐藏
    // private 修饰私有成员变量，只能在本类中访问。
    private int age;

    double height;
    double weight;

    // 封装2：合理暴露
    // 给 private 成员变量提供公开的 set、get 方法
    // public 修饰的成员就是公开的意思，它修饰的成员可以在任何地方访问。
    public  void setAge(int age) {
        if (age > 0 && age <= 150) {
            this.age = age;
        } else {
            System.out.println("age 符合要求");
        }
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }
}
