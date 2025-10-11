package com.czm.d1_innerClass_x;

public class Outer1 {

    public static String slogan = "好好学习";
    public static void printSlogan() {
        System.out.println("----static slogan = " + slogan);
    }
    private String name = "哈哈";
    private String hobby;
    private double height;
    public void printDesc() {
        System.out.println("=== 身高、爱好 =" + hobby + ", " + height);
    }

    public Outer1() {}

    public Outer1(String hobby, double height) {
        this.hobby = hobby;
        this.height = height;
    }

    // 1、成员内部类
    // 特点：无 static 修饰，属于外部类的对象持有。
    // 必须存在外部类对象，才会有这个内部类。
    public class Inner {
        private String name = "嘻嘻";
        private int age;

        public Inner() {}

        public Inner(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public void showOuter() {
            // 1、成员内部类中可以访问外部类的静态成员。
            System.out.println(slogan);
            printSlogan();

            // 2、成员内部类可以直接访问外部类的实例成员。
            // 因为成员内部类属于外部类对象持有。
            System.out.println(hobby);
            printDesc();
        }

        public void printName() {
            System.out.println("=== 成员内部类访问同名的 局部变量、实例变量、外部类实例变量");
            String name = "呼呼";
            System.out.println(name);
            System.out.println(this.name);
            System.out.println(Outer1.this.name);
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
