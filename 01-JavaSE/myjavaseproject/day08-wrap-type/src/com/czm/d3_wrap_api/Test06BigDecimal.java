package com.czm.d3_wrap_api;

import java.math.BigDecimal;

public class Test06BigDecimal {
    public static void main(String[] args) {
        /*
         1、高精度计算
         float、double 存储的只是小数的近似值，并非精确值，因此不建议进行高精度计算。
         计算机表示小数：

         ⚠️，建议使用 java.math.BigDecimal 来进行高精度计算。
         */
        double d1 = 0.7;
        double d2 = 0.7;
        System.out.println(d1 * d2);    // 0.48999999999999994，计算后得到的是一个近似值。

        /*
         2、BigDecimal 的使用
            建议使用字符串初始化 BigDecimal，因为 float、double 在计算机存储的是近似值，不是精确值。
            、
         */
        BigDecimal b1 = new BigDecimal("0.7");      // {'0', '.', '7'}
        BigDecimal b2 = new BigDecimal("0.7");      // {'0', '.', '7'}

        /*
          BigDecimal 计算的原理：它是把每一个数值当作字符存储，而不是以二进制方式存储。
          如： new BigDecimal("0.7");   ==>   {'0', '.', '7'}
          然后根据 小学计算规则 让每一个字符之间运算，最后得出计算结果。
         */

        System.out.println("--- 加法 " + b1.add(b2));     // 1.4
        System.out.println("--- 减法 " + b1.subtract(b2));    // 0.0
        BigDecimal v3 = b1.multiply(b2);
        System.out.println("--- 乘法 " + v3);    // 0.49
        System.out.println("--- 除法 " + b1.divide(b2));     // 1
        System.out.println("--- setScale 保留多少位小数 = " + b1.setScale(3));     // 0.700

    }
}
