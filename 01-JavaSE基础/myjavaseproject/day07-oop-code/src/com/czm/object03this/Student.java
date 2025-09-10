package com.czm.object03this;

public class Student {
    String name;
    double score;

    public Student(String name, double score) {
        this.name = name;
        this.score = score;
    }

    public Student() {
//        new Student("", 0.0);     // ⚠️，在其它构造器中，直接 new 的方式调用其它构造器方法是错误的，因为这样会创建一个新对象，而不是重复使用其它构造器中的代码。
        this("", 0.0);  // 在构造器中使用 this 调用其它构造器方法，使得可以重复利用其它构造器。
    }

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
