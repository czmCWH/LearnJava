package com.czm.object01;

// Student 表示类，它表示对象的数据结构，是对象的模版。
public class Student {
    // 1、定义成员变量、对象属性，也叫做字段（field）。
    String name;    // 姓名
    double chinese; // 语文成绩
    double math;    // 数学成绩

    // 2、定义成员方法(对象的行为)
    public void printAllScore() {
        System.out.println(name + "的总成绩：" + (chinese + math));
    }

    public void printAverageScore() {
        System.out.println(name + "的平均成绩：" + (chinese+math)/2);
    }
}

class Animal {

}

class Cat {

}