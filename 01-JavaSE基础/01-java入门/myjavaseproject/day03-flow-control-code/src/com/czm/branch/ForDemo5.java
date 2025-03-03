package com.czm.branch;

public class ForDemo5 {
    public static void main(String[] args) {
        // for 循环案例
        // 1、求和
        var total = 0;
        for (int i = 1; i <= 5; i++) {
            total += i;
        }
        System.out.println(total);

        // 2、求奇数和
        // 方法1：先产生数据，再筛选出奇数，然后求和。
        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            if (i % 2 == 1) {
                sum += i;
            }
        }
        System.out.println("sum = " + sum);

        // 方式2：直接for循环产生奇数求和。
        int sum2 = 0;
        for (int i = 1; i <= 10; i+=2) {
            sum2 += i;
        }
        System.out.println("sum2 = " + sum2);


    }
}
