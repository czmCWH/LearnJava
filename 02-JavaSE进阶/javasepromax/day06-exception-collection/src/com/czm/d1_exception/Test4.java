package com.czm.d1_exception;

import java.util.Scanner;

public class Test4 {
    /*
     1、开发中对于异常的常见处理方式 --- 尝试重新修复。

     */
    public static void main(String[] args) {
        while (true) {
            try {
                double price = getPrice();
                System.out.println("---输入的价格 = " + price);
                break;
            } catch (Exception e) {     // 捕获异常，重新修复
                System.out.println("---你输入内容不合法！");
            }
        }
    }

    public static double getPrice() {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- 请输入你的价格：");
        double price = sc.nextDouble();
        return price;
    }
}
