package com.czm.random;
import java.util.Random;

public class RandomDemo1 {
    public static void main(String[] args) {
        System.out.println("------ 1、生成随机数");
        // 1、创建一个 Random 对象
        Random r = new Random();

        // 2、返回一个0～9的随机数，java中大多数范围都是包前不包后
        int num = r.nextInt(10);
        System.out.println(num);

        System.out.println("------ 2、JDK17+生成指定区间随机数");
        // 生成指定区间的随机数
        Random r1 = new Random();
        // JDK17以上才可用
        int num1 = r1.nextInt(11, 21);
        System.out.println(num1);

        System.out.println("------- 3、传统方式生成指定区间随机数");
        Random r2 = new Random();
        int num2 = r2.nextInt(10) + 1;
        System.out.println("--- 1~10之间随机数 = " + num2);

        // 生成3～17之间的随机数，技巧：减加法
        // 3~17 -> -3 -> 0~14 + 3
        int num3 = r2.nextInt(15) + 3;
        System.out.println("--- 3~17之间的随机数 = " + num3);

        /*
         写代码技巧，无从下手时：
         1、用户思维：用户怎么想，代码就怎么写；
         2、线性思维：顺着一条线顺藤摸瓜写下来，程序就是一条线；
         */
    }
}
