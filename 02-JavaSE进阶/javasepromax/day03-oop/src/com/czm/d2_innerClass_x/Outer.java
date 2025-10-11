package com.czm.d2_innerClass_x;

public class Outer {
    // 静态成员
    public static String slogan = "好好学习";
    public static void printSlogan() {
        System.out.println("--- slogan = " + slogan);
    }

    // 实例成员
    private String name = "哈哈";
    public void printName() {
        System.out.println("--- name = " + name);
    }

    // 定义一个静态内部类
    public static class Inner {
        // 类有的成分 静态内部类都可以有
        private String name = "嘻嘻";
        private int age;

        public Inner() {}

        public Inner(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public void showSome() {
            // 1、静态内部类中可以访问外部类的静态成员。
            System.out.println("--- outer slogan = " + slogan);
            printSlogan();

            // 2、静态内部类不可以访问外部的实例成员。
            // 因为静态内部类属于外部类持有，它没有外部类对象，所以无法获取外部类对象的信息。
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
}
