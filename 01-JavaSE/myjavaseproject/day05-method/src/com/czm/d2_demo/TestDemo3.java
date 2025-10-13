package com.czm.d2_demo;

import java.util.Scanner;

public class TestDemo3 {
    /*
     1、评委打分
        分数范围0~10，去掉最低分、最高分，最后平均得分
     2、本案例知识点：

     */
    public static void main(String[] args) {
        double score = startScore(5);
        System.out.println("--- 5位评委打分结果 = " + score);
    }

    public static double startScore(int number) {
        // 1、定义数组接收分数
        double[] scores = new double[number];
        // 2、依次录入评委打分
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < number; i++) {
            System.out.println("请第" + (i+1) + "位评委打分：");
            double score = sc.nextDouble();
            if (score < 0 || score > 100) {
                System.out.println("--- 你打分错误，请重新打分");
                i--;    // 循环退一步
                continue;
            }
            // 录入评委分数
            scores[i] = score;
        }
        // 3、找出数组中最高分、最低分、总分
        double max = scores[0];
        double min = scores[0];
        double sum = scores[0];
        for (int i = 1; i < scores.length; i++) {
            double score = scores[i];
            if (score > max) {
                max = score;
            }
            if (score < min) {
                min = score;
            }
            sum += score;
        }
        System.out.println("--- 去掉最高分 = " + max);
        System.out.println("--- 去掉最低分 = " + min);

        return (sum - max - min) / (scores.length - 2);
    }
}
