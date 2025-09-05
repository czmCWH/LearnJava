package com.czm.demo;

public class TestDemo8 {
    /*
     1、案例：找素数，方法2
     */
    public static void main(String[] args) {
        findNumber();
    }

    // 独立功能，独立成方法，使功能变得非常简单。

    public static void findNumber() {
        // 1、遍历 101～200 之间的数
        for (int i = 101; i <= 200; i++) {
            // 2、遍历到的数，通过特定方法判断是否为素数
            if (isSuShu(i)) {
                System.out.print(i + ", ");
            }
        }
    }

    public static boolean isSuShu(int num) {
        // 从2开始找到该数的一半，如果能够被该数整除，就不是素数
        for (int i = 2; i <= num / 2; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true ;
    }
}

