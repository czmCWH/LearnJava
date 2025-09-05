package com.czm.demo;

import java.util.Random;
import java.util.Scanner;

public class TestDemo6 {
    /*
     1、案例：抢红包
        有红包 9, 666, 188, 520, 99999 共5个，先来先得，随机抽取，抽完为止。
     2、案例知识点：
        流程控制语句，数组的使用
     */
    public static void main(String[] args) {
        int[] moneys = { 9, 666, 188, 520, 99999 };
//        getReward(moneys);
        getReward2(moneys);
    }

    /*
     方案一：
        1、按照奖项的个数遍历开始抽奖
        2、根据随机索引获取对应的金额，如果金额不为0，则表示抽中，并把抽中的元素置为0，防止后续抽到
        3、后续抽到0，则死循环重新抽取。
     问题：死循环会造成性能问题。
     */
    public static void getReward(int[] arr) {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        for (int i = 0; i < arr.length; i++) {
            // 1、提示用户开始
            System.out.println("请第" + (i+1) + "粉丝输入，并按下回车参与抽奖：");
            sc.next();  // 等待输入后按了回车

            // 2、开始随机抽奖

//            int index = r.nextInt(arr.length);
//            int redMoney = arr[index];
//            // 2、判断是否抽到奖
//            if (redMoney == 0) {
//                System.out.println("---您未抽到奖，请重试！");
//                i--;
//                continue;
//            }
//            System.out.println("---恭喜你，抽到：" + redMoney + "元");
//            arr[index] = 0;

            // 3、循环强制抽到
           while (true){
               int index = r.nextInt(arr.length);
               int redMoney = arr[index];
               // 2、判断是否抽到奖
               if (redMoney != 0) {
                   System.out.println("---恭喜你，抽到：" + redMoney + "元");
                   arr[index] = 0;
                   break;
               }
           }
        }
    }

    /*
     方案2:
        1、先把金额数组顺序打乱，打乱后的顺序就是中奖的顺序。
        2、接着for循环依次派发中奖
     */
    public static void getReward2(int[] array) {
        // 1、使用随机数打乱数组
        Random r = new Random();
        for (int i = 0; i < array.length; i++) {
            int tmp = array[i];
            int idx = r.nextInt(array.length);
            // 让当前i位置元素与随机idx位置元素交换
            array[i] = array[idx];
            array[idx] = tmp;
        }
        // 2、开始依次输出抽奖：
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < array.length; i++) {
            // 1、提示用户开始
            System.out.println("请第" + (i + 1) + "粉丝输入，并按下回车参与抽奖：");
            sc.next();  // 等待输入后按了回车
            System.out.println("---恭喜你，抽到：" + array[i] + "元");
        }
    }
}
