package com.czm.d2_oop_inherit;

public class Person {
    public int age;
    public void walk() {
        System.out.println(age + " walk");
    }

    public int sameOne = 11;

    public void eat() {
        System.out.println("Person ---> eat");
    }

    // 构造方法
    public Person() {
        System.out.println("Person constructor");
    }

    public Person(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "age=" + age +
                ", sameOne=" + sameOne +
                '}';
    }
}
