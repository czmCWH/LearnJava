package com.czm.d3_wrap_api;

public class Test04PrintFmt {
    public static void main(String[] args) {
        /*
         1、数字格式化
         可以使用 System.out.printf 或者 System.out.format 输出格式化的字符串。
         ⚠️ 可以使用 String.format 创建格式化字符串。

            转换符     作用
              %d    十进制整数
              %f      浮点数
              %n    换行，与 \n 效果一样

            标记       作用
            08     8个字符的宽度，前面用0补齐
             +     显示符号（正数+，负数-）
             ,     显示分组字符（本地化）
             -     左对齐
             .3    保留3位小数
             10.3   10个字符的宽度，保留3位小数

         */
        System.out.printf("my name is %s, and age is %d \n", "张三", 18);
        System.out.format("my name is %s, and age is %d \n", "李四", 28);

        long n = 325421;
        System.out.format("%d%n", n);           // "325421"
        System.out.format("%08d%n", n);         // "00325421"   显示8位，不够用0补齐
        System.out.format("%+08d%n", n);        // "+0325421"   显示符号
        System.out.format("%+,8d%n%n", n);      // "+325,421"

        double pi = Math.PI;
        System.out.format("%f%n", pi);          // "3.141593"
        System.out.format("%.3f%n", pi);        // "3.142"
        System.out.format("%8.3f%n", pi);       // "   3.142"  显示8位，左边不够用空格补齐。并且小数点后保留3位
        System.out.format("%08.3f%n", pi);      // "0003.142"  显示8位，左边不够用 0 补齐。并且小数点后保留3位
        System.out.format("%-8.3f%n%n", pi);      // "3.142".   显示8位，左边不够用空格补齐。并且靠左对齐，所以补齐的空格移除了。

        System.out.println("----- 使用 String.format");
        String str = String.format("pi is %.3f", pi);   // pi is 3.142
        System.out.println(str);
    }
}
