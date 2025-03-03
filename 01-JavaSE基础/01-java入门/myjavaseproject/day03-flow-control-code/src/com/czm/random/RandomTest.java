package com.czm.random;

import java.util.Random;
import java.util.Scanner;

public class RandomTest {
    public static void main(String[] args) {
        // 实现猜数游戏
        Random r = new Random();
        // 1、随机生成 1～100 随机数
        int luckyNumber = r.nextInt(100) + 1;

        Scanner sc = new Scanner(System.in);
        // 2、使用一个死循环让游戏开始
        while (true) {
            // 3、接收用户输入的数据
            System.out.println("请输入你猜测的数据：");
            int guestNum = sc.nextInt();
            // 4、判断猜测的情况
            if (guestNum > luckyNumber) {
                System.out.println("您猜大了");
            } else if (guestNum < luckyNumber) {
                System.out.println("您猜小了");
            } else {
                System.out.println("恭喜你，猜对了！");
                break;
            }
        }
        System.out.println("结束游戏！");
    }
}
