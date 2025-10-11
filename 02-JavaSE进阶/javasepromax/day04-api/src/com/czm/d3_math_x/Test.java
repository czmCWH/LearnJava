package com.czm.d3_math_x;

public class Test {
    /*
     1、Match
        代表数学，是一个工具类，里面提供的都是对数据进行操作的一些静态方法。

     2、

     */
    public static void main(String[] args) {
        System.out.println("----- 1、取绝对值");
        System.out.println(Math.abs(-12));
        System.out.println(Math.abs(12));
        System.out.println(Math.abs(12.12));

        System.out.println("----- 2、向上取整");
        System.out.println(Math.ceil(1.0000001));
        System.out.println(Math.ceil(1.0));

        System.out.println("----- 3、向下取整");
        System.out.println(Math.floor(1.9));
        System.out.println(Math.floor(2.0));

        System.out.println("----- 4、四舍五入");
        System.out.println(Math.round(1.234));  // 打印：1
        System.out.println(Math.round(1.235));  // 打印：1
        System.out.println(Math.round(1.5));    // 打印：2

        System.out.println("----- 5、最大小值");
        System.out.println(Math.max(2.111112, 2.111111));
        System.out.println(Math.min(2.111112, 2.111111));

        System.out.println("----- 6、取次方");
        System.out.println(Math.pow(2, 3)); // 2的3次方
        System.out.println(Math.pow(3, 2)); // 3的2次方

        System.out.println("----- 7、随机数，范围：[0.0, 1.0) 包前不包后");
        System.out.println(Math.random());

    }
}
