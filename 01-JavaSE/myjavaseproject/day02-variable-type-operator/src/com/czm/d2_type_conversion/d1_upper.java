package com.czm.d2_type_conversion;

public class d1_upper {

    public static void main(String[] args) {
        /*
         1、拓宽基本类型转换（Widening Primitive Conversion）
         数据范围小的可以自动转换（隐式转换）为数据范围大的。例如：byte 变量赋值给 int
         自动转换的形式：
            byte -> short -> int -> long -> float -> double。
            char -> int，因为字符本身是ASCII码编号，所以赋值给 int。

         ⚠️：boolean 类型不能和其它基本数据类型相互转换。
         */
        byte b = 12;
        int i = b;
        System.out.println(b);
        System.out.println(i);

        char c = 'a';
        int iC = c;
        System.out.println(c);  // 打印：a，因为变量的类型，决定了它打印输出的方式
        System.out.println(iC); // 打印：97

        int j = 22;
        double d = j;
        System.out.println("d = " + d);
    }
}
