package com.czm.d1_operator;

public class Demo6 {
    public static void main(String[] args) {
        // 位移运算符

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

    }
}
