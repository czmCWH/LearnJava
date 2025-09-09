package com.czm.d1_operator;

public class d4_logic {
    public static void main(String[] args) {
        /*
         1、逻辑运算符
         把多个条件放在一起运算，最终返回布尔类型的值：true、false

         &，按位(、逻辑)与，例如：2>1 & 3>2，多个条件必须都是true，结果才是true;有一个是false，结果就是false
         |，按位(、逻辑)或，例如：2>1 | 3>5，多个条件中只要有一个是true，结果就是true
         ^，按位(、逻辑)异或，例如：2>1 ^ 3>1，前后条件的结果相同，就直接返回false，前后条件的结果不同，才返回true

         ⚠️：&、|、^ 运算符既可以用于整数的位运算，也可以用在 boolean 类型上。


         && 短路与，例如：2>10 && 3>2，判断结果与“&”一样，过程不同：左边为 false，右边则不执行。
         || 短路或，例如：2>1 || 3<5，判断结果与“|”一样，过程不同：左边为 true，右边则不执行，
         !，逻辑非，例如：!(1>2)，对 boolean 类型逻辑取反，!true == false，!false == true

         & 和 &&、 | 和|| 对比，少了短路功能，即运算符左右两边条件必须都执行。

         ⚠️：实际开发中、常用的逻辑运算符是：&&、‖、!。
             逻辑运算符优先级：! > & > ^ > | > && > ||。
         */

        System.out.println("1、--- &、|、^ 运算符用于 整数类型 位运算：");
        int a1 = 0b10010;
        int a2 = 0b11111;
        int a3 = a1 & a2;
        System.out.println("a3 = " + Integer.toBinaryString(a3));   // a3 = 10010
        int a4 = a1 | a2;
        System.out.println("a4 = " + Integer.toBinaryString(a4));   // a4 = 11111
        int a5 = a1 ^ a2;
        System.out.println("a5 = " + Integer.toBinaryString(a5));   // a5 = 01101

        System.out.println("2、--- &、|、^ 运算符用于 boolean 类型运算：");
        boolean flag1 = true, flag2 = false;
        System.out.println("flag1 & flag2 = " + (flag1 & flag2));
        System.out.println("flag1 | flag2 = " + (flag1 | flag2));
        System.out.println("flag1 ^ flag2 = " + (flag1 ^ flag2));

        double height = 160.0;
        char sex = '女';
        System.out.println(height >= 160 & sex == '女');     // true

        System.out.println(height >= 160 ^ sex == '女');     // false
        System.out.println(height >= 160 ^ sex == '男');     // true

        System.out.println("3、--- 短路运算符：");
        int i = 10;
        int j = 22;
        System.out.println(i < 5 & ++j > 33);
        System.out.println(j);  // 打印：23
        int m = 10;
        int n = 22;
        System.out.println(m < 5 && ++n > 33);
        System.out.println(n);  // 打印：22
    }
}
