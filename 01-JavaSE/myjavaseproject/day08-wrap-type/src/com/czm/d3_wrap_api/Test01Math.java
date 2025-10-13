package com.czm.d3_wrap_api;

public class Test01Math {
    public static void main(String[] args) {
        /*
         1、java.lang.Math 类提供了常见的数学计算功能。
         */

        System.out.println("--- 自然常数，自然对数函数的底数 E =" + Math.E);
        System.out.println("--- 圆周率 PI = " + Math.PI);

        System.out.println("--- 求绝对值：" + Math.abs(-100));
        System.out.println("--- 求最大值：" + Math.max(100, 300));
        System.out.println("--- 求最小值：" + Math.min(300, 100));

        System.out.println("--- 向下取整3.9：" + Math.floor(3.9));
        System.out.println("--- 向上取整3.1：" + Math.ceil(3.1));
        System.out.println("--- 四舍五入3.5：" + Math.round(3.5));

        System.out.println("--- 求4的3次方：" + Math.pow(4, 3));
        System.out.println("--- 求25的平方根：" + Math.sqrt(25));

        System.out.println("--- 求自然常数 E 的3次方：" + Math.exp(3));

        System.out.println("--- 求ln8的值：" + Math.log(8));

        // 角度转弧度
        double degree = 90;
        double radian = Math.toRadians(degree);

        // 三角函数
        System.out.println(Math.sin(radian));
        System.out.println(Math.cos(radian));
        System.out.println(Math.tan(radian));

    }

}
