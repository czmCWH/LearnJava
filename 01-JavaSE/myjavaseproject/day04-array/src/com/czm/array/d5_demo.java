package com.czm.array;

import java.util.Random;
import java.util.Scanner;

public class d5_demo {
    public static void main(String[] args) {
        /*
         1、随机索引进行排名
         案例：有5个人的工号，需要让他们随机上台汇报，请排列他们的顺序。
         实现：依次遍历数组中的每个数据，然后随机一个索引，再把随机索引与此时遍历的数据交换位置。
         */
        // 1、动态初始化一个数组，存储录入的数据
        int[] codes = new int[5];

        // 2、录入员工工号
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < codes.length; i++) {
            System.out.println("请输入第" + (i+1) + "名员工工号: ");
            codes[i] = sc.nextInt();
        }

        // 3、输入员工的原始工号
        System.out.print("输入的工号：");
        for (int i = 0; i < codes.length; i++) {
            System.out.print(codes[i] + " ");
        }


        // 4、打乱员工的工号
        Random r = new Random();
        for (int i = 0; i < codes.length; i++) {
            int tmp = codes[i];
            int idx = r.nextInt(codes.length);
            // 让当前i位置元素与随机idx位置元素交换
            codes[i] = codes[idx];
            codes[idx] = tmp;
        }

        // 5、打乱之后的结果：
        System.out.println();
        System.out.print("打乱之后的结果：");
        for (int i = 0; i < codes.length; i++) {
            System.out.print(codes[i] + " ");
        }

    }
}
