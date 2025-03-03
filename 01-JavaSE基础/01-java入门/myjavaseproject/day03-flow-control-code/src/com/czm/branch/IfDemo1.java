package com.czm.branch;

import java.sql.SQLOutput;
import java.util.Scanner;

public class IfDemo1 {
    public static void main(String[] args) {
        /*
         程序流程控制，是指控制程序的执行顺序。
            1、顺序结构，自上而下的执行代码。
            2、分支结构，根据条件，选择对应代码执行。if、switch
            3、循环结构，控制某段代码的重复执行。for、while、do-while

         1、if 分支结构
         根据条件(true 或 false)来决定执行某段代码。
         */

        // if分支写法1
        double t = 38;
        if (t > 37) {
            System.out.println("你的体温异常，需要就医！");
        }
        // 注意：if语句结构中，如果只有一行代码，可以省略{}，但不建议。

        // if分支写法2
        double money = 100;
        if (money > 90) {
            System.out.println("可以发红包！");
        } else {
            System.out.println("余额不足，不可以发红包！");
        }

        // if分支写法3
        Scanner sc = new Scanner(System.in);
        System.out.println("请录入员工的得分：");
        double score = sc.nextDouble();
        if (score > 0 & score < 60) {
            System.out.println("你的绩效级别是：D");
        } else if (score >= 60 & score < 80) {
            System.out.println("你的绩效级别是：C");
        } else if (score >= 80 & score < 90) {
            System.out.println("你的绩效级别是：B");
        } else if (score >= 90 & score <= 100) {
            System.out.println("你的绩效级别是：A");
        } else {
            System.out.println("输入分数错误！");
        }
    }
}
