package com.czm.d2_type_conversion;

public class d2_lower {
    public static void main(String[] args) {
        /*
        2、窄化基本类型转换（Narrowing Primitive Conversion）
        数据范围大的转为数据范围小的，可能会丢失精度和范围，需要 强制转换。
        强制类型转换：
            double -> float -> long -> int -> short -> byte
            char -> short、byte
            short -> byte、char
        */
        int a = 20;
//        byte b = a; // 报错：大范围类型的变量直接赋值给小范围的变量报错，避免类型溢出
        byte b = (byte) a;  // 强制类型转换
        System.out.println(b);

        // 强制类型转换可能造成数据(丢失)溢出;
        int i = 1500;
        byte b2 = (byte) i;
        System.out.println("int -> byte = " + b2);  // 打印：-36，因为数据溢出，导致类型转换失真

        // 浮点型强转成整型，直接丢掉小数部分，保留整数部分返回；
        double d = 3.14;
        int i2 = (int) d;
        System.out.println("double -> int = " + i2); // 打印：3

        // char 转换
        short s = 512;
        char c = (char) s;
        System.out.println("short -> char = " + c);     // short -> char = Ȁ

        byte bb = (byte) c;
        System.out.println("char -> byte = " + bb); // char -> byte = 0

    }
}
