package com.czm.d1_literal;

/**
 * Java 的字面量
 * 字面量是指数据在程序中的书写格式。
 */
public class LiteralDemo {
    // main 方法，程序的入口
    public static void main(String[] args) {
        // 1、整数
        System.out.println(10);
        System.out.println(-20);

        // 2、小数
        System.out.println(12.12);
        System.out.println(-5.3);

        // 3、字符，必须使用单引号，且只有一个字符
        System.out.println('A');
        System.out.println('我');
//        System.out.println('🥰');  // char 内存占用2个字节，emoji字符占用3个字节，所以不能用char表示。
        // 字符串，必须使用双引号
        System.out.println("我爱中国");

        // 4、布尔值，只有2个值：true、false
        System.out.println(true);

        // 5、空值
//        System.out.println(null);

        // 6、转义字符，如：\t 空格，(\n 、\r)换行
        System.out.println("你好" + '\t' + '帅');

    }
}
