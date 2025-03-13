package com.czm.d8_demo;

public class Test3 {

    // 题目：啤酒问题:啤酒一瓶2元，4个盖子换一瓶，2个空瓶换一瓶，10元可以喝多少瓶，剩余多少盖子和空瓶。

    // 一共买了多少瓶酒
    public static int totalNumber = 0;
    // 剩余空瓶数
    public static int lastBottleNumber = 0;
    // 剩余瓶盖数
    public static int lastCoverNumber = 0;

    public static void main(String[] args) {
        buy(10);
        System.out.println("总共买了 = " + totalNumber);
        System.out.println("剩余空瓶 = " + lastBottleNumber);
        System.out.println("剩余瓶盖 = " + lastCoverNumber);
    }

    public static void buy(int money) {
        // 1、那钱买酒的数量
        int numbers = money / 2;
        totalNumber += numbers;

        // 2、计算出本轮空瓶、瓶盖，换成钱买酒，递归买酒
        int totalBottleNumber = numbers + lastBottleNumber;
        int totalCoverNumber = numbers + lastCoverNumber;

        // 3、换成钱继续买酒
        int allMoney = 0;
        if (totalBottleNumber >= 2) {
            allMoney += (totalBottleNumber / 2) * 2;
        }
        lastBottleNumber = totalBottleNumber % 2;   // 记录剩余的空瓶

        if (totalCoverNumber >= 4) {
            allMoney += (totalCoverNumber / 4) * 2;
        }
        lastCoverNumber = totalCoverNumber % 4; // 记录剩余的瓶盖

        if (allMoney >= 2) {
            buy(allMoney);  // 递归买酒
        }
    }
}
