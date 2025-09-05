package com.czm.create;
import java.util.Scanner;

public class ArrayDemo3 {
    public static void main(String[] args) {

        // 动态初始化数组案例

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

    }
}
