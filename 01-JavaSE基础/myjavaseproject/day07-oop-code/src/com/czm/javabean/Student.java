package com.czm.javabean;

// 定一个实体类
public class Student {
    // 1、所有的成员变量私有
    private String name;
    private int age;
    private double height;

    // 2、提供一个公共的无参构造器（默认会存在）
    public Student() {}
    public Student(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    // 3、提供 get、set 方法
    // get/set方法已经是java的规范，因此可以快捷创建：
    //  点击类定义区域 右键 > 生成 > setter/getter > 框选private变量


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

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
