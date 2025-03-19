package com.czm.branch;

public class Demo6For {
    public static void main(String[] args) {
        /*
         1、for循环案例：水仙花数
          水仙花数是一个三位数；
          水仙花数的个位、十位、百位的数字立方和等于原数；
          比如：153
         */
        System.out.print("水仙花数据 = ");
        int count = 0;
        for (int i = 100; i <= 999; i++) {
            int ge = i % 10;    // 个
            int shi = i / 10 % 10;  // 十
            int bai = i / 100;  // 百
            if (ge*ge*ge + shi*shi*shi + bai*bai*bai == i) {
                System.out.print(i + " ");  // System.out.print 不换行打印
                count++;
            }
        }
        System.out.println();   // 打印换行
        System.out.println("水仙花数的个数：" + count);


    }
}
