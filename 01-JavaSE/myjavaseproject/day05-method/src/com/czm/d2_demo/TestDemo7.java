package com.czm.d2_demo;

public class TestDemo7 {
    /*
     1、案例：找素数
        除了1和它本身以外，不能被其他正整数整除，就叫素数。找出101～200之间的素数

     2、案例知识点：
        信号位思想
     */
    public static void main(String[] args) {
        findNumber();
    }
    public static void findNumber() {
        System.out.println("101~200之间的素数有：");
        int count = 0;
        for (int i = 101; i <= 200; i++) {

            // 只需要从2开始找到该数的一半，看是否有数据能整除它，能整除则不是素数。

            // 假设默认是素数，再做判断。信号位思想
            boolean flag = true;

            for (int j = 2; j <= i / 2; j++) {
                if (i % j == 0) {
//                    System.out.println(i + "不是素数");
                    flag = false;
                    break;
                }
            }

            if (flag) {
                count++;
                System.out.print(i + ", ");
            }
        }
        System.out.println();
        System.out.println("101~200之间的素数个数有：" + count);
    }
}
