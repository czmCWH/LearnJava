package com.czm.array;

import java.util.Scanner;

public class d3_example {
    public static void main(String[] args) {

        System.out.println("------------- 1、动态初始化数组案例");

        double[] scores = new double[6];
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < scores.length; i++) {
            System.out.println("请输入第"+ (i+1) + "个评委的分数：");
            scores[i] = sc.nextDouble();
        }
        double sum = 0;
        for (int i = 0; i < scores.length; i++) {
            sum += scores[i];
        }
        System.out.println("平均分：" + sum/scores.length);


        System.out.println("------------- 2、静态初始化数组案例，数组求最值");

        // 1、用数组把数据装起来
        int[] faceScore = {15, 9000, 10000, 20000, 9500, -5};
        // 2、定一个变量记录最大值，默认第一个值为参照物
        int max = faceScore[0];
        // 3、从数组的第二个位置开始遍历数组的每个元素
        for (int i = 1; i < faceScore.length; i++) {
            // 4、判断当前遍历的变量是否大于参照物，若较大则替换

//            if (faceScore[i] > max) {
//                max = faceScore[i];
//            }
            // 优化如下，因为 faceScore[i] 每次访问都是从栈中的地址去堆中找值，会非常消耗性能。
            // 如下方式只需要去堆中找一次，后续访问多次也不会影响性能。
            int score = faceScore[i];
            if (score > max) {
                max = score;
            }
        }
        // 5、输出最大变量
        System.out.println("--- 数组中最大元素 = " + max);
    }
}
