package com.czm.d1_method;

public class d2_design {
    /*
     1、设计方法的技巧：
        a、方法是否需要接收数据进行处理?
        b、方法是否需要返回数据?
        c、方法要处理的业务 (编程能力)。
     */
    public static void main(String[] args) {
        System.out.println(sumTo(100));
        checkOddEven(7823);
    }

    // 案例1：求1到n的和
    public static int sumTo(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    // 案例2：判断一个数是奇数还是偶数
    public static void checkOddEven(int num) {
        if (num % 2 == 0) {
            System.out.println(num + "是一个偶数");
        } else {
            System.out.println(num + "是一个奇数");
        }
    }
}
