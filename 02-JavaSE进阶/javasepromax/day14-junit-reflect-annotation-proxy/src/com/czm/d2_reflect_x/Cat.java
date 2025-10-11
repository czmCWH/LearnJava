package com.czm.d2_reflect_x;

public class Cat {
    private static int a;
    public static final String COUNTRY = "波斯";
    private  String name;
    private  int age;

    public Cat() {
        System.out.println("--- 无参构造器之行了");
    }

    private Cat(String name, int age) {
        System.out.println("--- 有参构造器之行了～～～");
        this.name = name;
        this.age = age;
    }

    private void run() {
        System.out.println("--- 🐈猫抓老师跑得快！");
    }

    public void eat() {
        System.out.println("--- 🐱吃猫粮了！");
    }

    private String eat(String name) {
        return "猫最爱吃：" + name;
    }

    public static int getA() {
        return a;
    }

    public static void setA(int a) {
        Cat.a = a;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
