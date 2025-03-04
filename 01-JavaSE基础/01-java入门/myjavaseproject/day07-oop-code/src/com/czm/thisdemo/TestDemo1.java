package com.czm.thisdemo;

public class TestDemo1 {
    /*
     1、对象的 this 变量
        this 就是一个变量，可以用在对象的方法中，来拿到当前对象。

     2、this 的应用
         this主要用来解决: 变量名称冲突问题的，
     */
    public static void main(String[] args) {
        Student st1 = new Student();
        System.out.println(st1);
        st1.printMy();
    }
}
