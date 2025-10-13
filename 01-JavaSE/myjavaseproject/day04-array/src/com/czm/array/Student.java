package com.czm.array;

public class Student implements Comparable<Student> {
    private String name;
    private int age;
    private char gender;
    private double height;

    public Student() {
    }

    public Student(String name, int age, char gender, double height) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
    }

    // 指定大小规则
    // 比较者：this
    // 被比较者：参数o
    @Override
    public int compareTo(Student o) {
        // 官方规定：
        // 如果认为左边 > 右边，返回 正整数；
        // 如果认为左边 < 右边，返回 负整数；
        // 如果认为左边 == 右边，返回 0；
//        if (this.age > o.age) {
//            return 1;
//        } else if (this.age < o.age) {
//            return -1;
//        }
//        return 0;
        // ⚠️：优化，根据差值来确保排序结果
        return this.age - o.age;
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

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
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
                ", gender=" + gender +
                ", height=" + height +
                '}' + "\n";
    }
}
