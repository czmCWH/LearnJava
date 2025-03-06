package com.czm.d7_interface;

import java.util.ArrayList;

public class ClassDataImpl2 implements ClassData {
    ArrayList<Student> students;
    // 构造器
    public ClassDataImpl2(ArrayList<Student> list) {
        this.students = list;
    }

    @Override
    public void printAllStudentInfos() {
        System.out.println("========展示学生全部信息=========");
        int count = 0;
        for (int i = 0; i < this.students.size(); i++) {
            Student s = this.students.get(i);
            System.out.println(s.getName() + ", " + s.getSex() + ", " + s.getScore());
            if (s.getSex() == '男') {
                count++;
            }
        }
        System.out.println("--- 男生人数 = " + count);
        System.out.println("--- 女生人数 = " + (this.students.size() - count));
    }

    @Override
    public void printAllStudentAverageScores() {
        System.out.println("========展示全部学生平均分=========");
        Student s1 = this.students.get(0);
        double score = s1.getScore();
        double allScore = score;
        double min = score;
        double max = score;
        for (int i = 1; i < this.students.size(); i++) {
            Student s = this.students.get(i);
            double sc = s.getScore();
            allScore += sc;
            if (min > sc) {
                min = sc;
            }
            if (max < sc) {
                max = sc;
            }
        }
        System.out.println("--- 最高分 = " + max);
        System.out.println("--- 最低分 = " + min);
        System.out.println("--- 平均成绩 = " + ((allScore - max - min)/(this.students.size() - 2)));
    }
}
