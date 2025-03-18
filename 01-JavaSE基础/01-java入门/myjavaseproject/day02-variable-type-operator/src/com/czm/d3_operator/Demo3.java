package com.czm.d3_operator;

public class Demo3 {
    public static void main(String[] args) {
        /*
         1、基本赋值运算符
         =，从右边往左边看，把谁赋值给谁。

         2、扩展赋值运算符
         +=，a += b，底层代码形式 -->，a = (⚠️a的类型)(a + b)
         -+，a -= b，底层代码形式 -->，a = (a的类型)(a - b)
         *=，a *= b，底层代码形式 -->，a = (a的类型)(a * b)
         /+，a /= b，底层代码形式 -->，a = (a的类型)(a / b)
         %=，a %= b，底层代码形式 -->，a = (a的类型)(a % b)

         注意:扩展的赋值运算符隐含了强制类型转换。
         */
        double total = 10;
        int b = 5;
        total += b;
        System.out.println(total);  // 打印：15.0

        char i = 'a';
        double j = 5;
        i += j;
        System.out.println(i);  // 打印：f

        byte b1 = 110;
        byte b2 = 120;
//        byte t3 = b1 + b2;  // 报错
        byte b3 = (byte)(b1 + b2);
        System.out.println(b3); // 打印：-26
        b1 += b2;
        System.out.println(b1); // 打印：-26
    }
}
