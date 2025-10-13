package com.czm.d2_type_conversion;

public class d3_auto {
    public static void main(String[] args) {
        /*
         1、一元数字提升(Unary Numeric Promotion)
         一元数字提升是指，将 byte、short、char 类型的一元数字自动提升为 int 类型。如下场景会执行一元数字提升：
            数组的索引、创建数组时的数组长度；
            一元运算符 +、-；
            按位取反（~）；
            位移运算符 <<、>>、>>>；

         */
        int[] arr = { 10, 20, 30, 40 };
        double idx1 = 1.0;
//        System.out.println(arr[idx1]);      // 报错，因为数组的索引只能接收 int 类型
        byte idx2 = 1;
        System.out.println(arr[idx2]);      // idx2 变量是 byte 类型作为数组索引时，会自动提升为 int 类型。

        char c = 'A';   // char 类型变量存储的是它的 ASCII 值
        System.out.println("c = " + c);     // 打印：c = A
        System.out.println(+c);     // 打印：65，c自动提升为 int 类型
//        char c1 = +c;       // 报错，因为 +c 会自动提升为 int 类型
        char c2 = 65;       // char 可以接受数字字面量。

        System.out.println("\n---------------------------\n");

        /*
         2、二元数字提升(Binary Numeric Promotion)
         二元数字提升是指，提升一个或者两个数字转换成表达式中较大范围的类型（拓宽基本类型转换）。使用如下运算符场景时进行二元数字提升：
            乘（*）、除（/）、取余（%）；
            加法（+）、减法（-）；
            比较（<、<=、>、>=）；
            相等（==、!=）；
            位运算（&、^、|）；
            三目（? : ）；
          byte、short、char -> int -> long -> float -> double
         */

        byte v1 = 10, v2 = 20;
//        byte v3 = v1 + v2;  // 报错，因为 v1 + v2 会执行二元数字提升，v1、v2 都会转换为 int 类型。
//        byte v4 = v1 + 20;  // 报错，因为 v1 + 20 中 v1 参与了二元运算，所以 v1 会自动转换为 int 类型
//        byte v5 = 10 + v2;  // 报错，
        byte v6 = 10 + 20;

        byte b1 = 1;
//        b1 = b1 + 1;    // 报错，因为 执行了 二元数字提升，b1 会转换为 int 类型。
        b1 += 1;    // 复合赋值运算会自动转换类型

        byte b2 = 10;
        short s1 = 20;
        byte res = (byte) (b2 > s1 ? b2 : s1);  // 三目运算要求 ? 两边的数据类型相同，但是此时基本数字类型执行了二元数字提升，b2 和 s1 自动转换成了 int 类型，所以不会报错。
        System.out.println("res = " + res);

        System.out.println("\n---------------------------\n");

    }

    // 案例：
    void exampleConversion() {
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
