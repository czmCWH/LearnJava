package com.czm.thisdemo;

public class Student {
    String name;
    double score;

    // 1、this 表示当前对象
    public void printMy() {
        System.out.println(this);
    }

    public void isSuccess(double score) {
        // 2、通过 this 解决区分是否访问对象的变量
        if (this.score >= score) {
            System.out.println("---你成功了");
        } else {
            System.out.println("---你失败了");
        }
    }
}
