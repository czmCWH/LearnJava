package com.czm.d6_bigdecimal_x;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test {
    /*
     1、BigDecimal 浮点数运算
         用于解决浮点型运算时，出现结果失真的问题


     2、
     */
    public static void main(String[] args) {
        System.out.println("---- 浮点数直接 +-*/ 运算时，可能会出现运算结果失真。----");
        System.out.println(0.1 + 0.2);
        System.out.println(1.0 - 0.32);
        System.out.println(1.015 * 100);
        System.out.println(1.301 / 100);

        System.out.println("----------- BigDecimal");
        double a = 0.1;
        double b = 0.2;

        // 1、把2个数据包装成 BigDecimal 对象
        BigDecimal a1 = new BigDecimal(Double.toString(a));
        BigDecimal b1 = new BigDecimal(Double.toString(b));
        /*
          ⚠️：
             new BigDecimal(double val) 构造器保证大数据运算，结果会失真。--- ⚠️禁止
             new BigDecimal(String val) 构造器能保证大数据运算 和 结果不失真。

             阿里巴巴推荐使用 BigDecimal.valueOf 包装浮点型数据为 BigDecimal 对象。这样语法简洁，作用一致。
         */
        BigDecimal a11 = BigDecimal.valueOf(a);
        BigDecimal b11 = BigDecimal.valueOf(b);

        // 2、开始运算
        // BigDecimal 处理数据的手段，结果转换为基本数据类型
//        BigDecimal c11 = a11.add(b11);      // 加
//        BigDecimal c11 = a11.subtract(b11);     // 减
//        BigDecimal c11 = a11.multiply(b11);     // 乘
        BigDecimal c11 = a11.divide(b11);   // 除
        // 开发中，基本类型接收数据，引用类型处理数据。因为基本数据类型在栈里面，不用跨区域去堆里面找数据。
        double res1 = c11.doubleValue();
        System.out.println("-----运算结果 = " + res1);    // 打印：0.3

        System.out.println("----------------------------------------------");
        BigDecimal i = BigDecimal.valueOf(0.1);
        BigDecimal j = BigDecimal.valueOf(0.3);
//        BigDecimal k = i.divide(j);     // ⚠️报错：没有可精确表示的十进制结果。因为 1/3 除不断
        /*
         参数1：除数
         参数2：保留位数
         参数3：舍入模式
         */
        BigDecimal k = i.divide(j, 2, RoundingMode.HALF_UP);
        System.out.println(k);
    }
}
