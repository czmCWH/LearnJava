package com.czm.d7_interface_x;

import java.util.ArrayList;

public class Test {
    /*
     1、接口综合案例
        设计一个班级学生的信息管理模块，学生的数据有:姓名、性别、成绩。
            功能1：要求打印出全班学生的信息;
            功能2：要求打印出全班学生的平均成绩；
        要求以上功能可以灵活的切换以下方案：
            方案1：能打印出班级全部学生的信息;能打印班级全部学生的平均分
            方案2：能打印出班级全部学生的信息(包含男女人数);能打印班级全部学生的平均分(要求是去掉最高分、最低分

        ！！！以上实现是功能解耦合。

     2、实现步骤
        1、定义接口。
        2、定义两套实现类，来分别处理，以便解耦合。

     3、
     */
    public static void main(String[] args) {

        ArrayList<Student> list = new ArrayList<Student> ();
        list.add(new Student("小红", '女', 97.0));
        list.add(new Student("小李", '男', 35.0));
        list.add(new Student("小明", '女', 78.0));
        list.add(new Student("小王", '男', 45.0));
        list.add(new Student("王八", '女', 80.0));

        ClassData s1 = new ClassDataImpl1(list);    // 多态
        s1.printAllStudentInfos();
        s1.printAllStudentAverageScores();

        System.out.println("-----------------------");

        ClassData s2 = new ClassDataImpl2(list);    // 多态
        s2.printAllStudentInfos();
        s2.printAllStudentAverageScores();
    }
}
