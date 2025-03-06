package com.czm.d2_static_method;

public class Student {

    double score;

    // 静态方法，属于类
    public static void printSay() {
        for (int i = 0; i < 3; i++) {
            System.out.println("hello world!");
        }
    }
    // 实例方法，属于对象
    public void printScore() {
       if (score >= 60) {
           System.out.println("--- 成绩通过！");
       } else {
           System.out.println("--- 你挂科了");
       }
    }
}

