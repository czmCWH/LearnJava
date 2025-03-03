package com.czm.type;

public class TypeConversionDemo2 {
    public static void main(String[] args) {
        /*
         2、表达式的自动类型转换
         在表达式中，小范围类型的变量，会自动转换成表达式中较大范围的类型，再参与运算。
         byte、short、char -> int -> long -> float -> double
         表达式的最终结果类型由表达式中的最高类型决定。
         在表达式中，byte、short、char 是直接转换为 int 类型参与运算的。
         */
        byte a = 10;
        int b = 20;
        long c = 30;
        // int result = a + b + c + 100;    // 报错
        long result = a + b + c + 100;
        System.out.println(result);

        // long result2 = b + c + 3.12;     // 报错
        double result2 = b + c + 3.12;
        System.out.println(result2);

        byte a1 = 10;
        byte a2 = 20;
        // byte a3 = a1 + a2;   // 报错：因为 byte、short、char 是直接转换为 int
        int a3 = a1 + a2;
        System.out.println(a3);
    }
}
