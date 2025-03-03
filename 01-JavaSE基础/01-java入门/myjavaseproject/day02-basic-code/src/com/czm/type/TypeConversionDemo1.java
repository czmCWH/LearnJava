package com.czm.type;

public class TypeConversionDemo1 {

    public static void main(String[] args) {
        /*
         1、自动类型转换
         类型范围小的变量，可以直接赋值给类型范围大的变量。例如：byte 变量赋值给 int
         自定义转换的形式：
            byte -> short -> int -> long -> float -> double
            char -> int，因为字符本身是ASCII码编号，所以赋值给 int
         */
        byte b = 12;
        int i = b;
        System.out.println(b);
        System.out.println(i);

        char c = 'a';
        int iC = c;
        System.out.println(c);  // 打印：a，因为变量的类型，决定了它打印输出的方式
        System.out.println(iC); // 打印：97

    }
}
