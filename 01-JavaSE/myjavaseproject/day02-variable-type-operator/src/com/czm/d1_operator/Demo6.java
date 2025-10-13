package com.czm.d1_operator;

public class Demo6 {
    public static void main(String[] args) {
        // ⚠️ 位移运算符
        // 乘/除/取模运算效率都比较低，不如加/减/位运算效率高。

        System.out.println("--- 正数右移：");
        byte a = 127;
        // Integer.toBinaryString：将整数转换为二进制字符串表示
        System.out.println("a = " + a + ", code = " + Integer.toBinaryString(a));      // 1111111
        int a1 = 127 >> 2;
        System.out.println("a1 = " + a1 + ", code >> 为：" + Integer.toBinaryString(a1));
        int a2 = 127 >>> 2;
        System.out.println("a2 = " + a2 + ", code >>> 为：" + Integer.toBinaryString(a2));

        System.out.println("--- 负数右移：");
        byte b = -128;
        System.out.println("b = " + b + ", code = " + Integer.toBinaryString(b));      // 1111111
        int b1 = -128 >> 2;
        System.out.println("b1 = " + b1 + ", code >> 为：" + Integer.toBinaryString(b1));
        int b2 = -128 >>> 2;
        System.out.println("b2 = " + b2 + ", code >> 为：" + Integer.toBinaryString(b2));

        System.out.println("\n--- 右移每多1位就是除以2：");
        byte c = 6;
        System.out.println("c = " + c + ", code = " + Integer.toBinaryString(c));
        int c1 = c >> 1;
        System.out.println("c1 = " + c1 + ", code = " + Integer.toBinaryString(c1));

        commonCalculate();
    }

    // ⚠️ 常用的位运算，提供程序性能！
    public static void commonCalculate() {
        // 1、除以2并取整
        int a = 5;
        int res = a >> 1;
        System.out.println("除以2取整：res = " + res);   // res = 2

        // 2、判断奇偶数
        for (int i = 0; i < 5; i++) {
            int r = i & 1;
            if (r == 0) {
                System.out.printf("i = %d, res = %d，为偶数！\n", i, r);
            } else {
                System.out.printf("i = %d, res = %d，为基数！\n", i, r);
            }
        }

    }
}
