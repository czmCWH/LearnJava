package com.czm.d6_object_stream;

import java.io.Serializable;

// 如果对象需要实现序列化，必须实现 Serializable 接口。
public class Student implements Serializable {
    private String name;
    private int age;
    // transient：屏蔽该属性参与序列化
    private transient String password;
    private double height;

    public Student() {
    }

    public Student(String name, int age, String password, double height) {
        this.name = name;
        this.age = age;
        this.password = password;
        this.height = height;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", password='" + password + '\'' +
                ", height=" + height +
                '}';
    }
}
