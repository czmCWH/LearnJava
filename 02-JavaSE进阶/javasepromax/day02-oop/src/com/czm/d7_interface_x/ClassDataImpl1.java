package com.czm.d7_interface_x;

import java.util.ArrayList;

// 第1套方案，实现类
public class ClassDataImpl1 implements ClassData {
    private ArrayList<Student> students;
    // 构造器
    public ClassDataImpl1(ArrayList<Student> lis) {
        this.students = lis;
    }

    @Override
    public void printAllStudentInfos() {
        System.out.println("========展示学生全部信息=========");
        for (int i = 0; i < this.students.size(); i++) {
            Student s = this.students.get(i);
            System.out.println(s.getName() + ", " + s.getSex() + ", " + s.getScore());
        }
    }

    @Override
    public void printAllStudentAverageScores() {
        System.out.println("========展示全部学生平均分=========");
        double sum = 0;
        for (int i = 0; i < this.students.size(); i++) {
            Student s = this.students.get(i);
            sum += s.getScore();
        }
        System.out.println("--- 平均成绩 = " + (sum/this.students.size()));
    }
}
