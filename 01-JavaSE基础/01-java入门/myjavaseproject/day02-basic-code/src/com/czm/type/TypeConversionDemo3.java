package com.czm.type;

public class TypeConversionDemo3 {
    public static void main(String[] args) {
        /*
        1、强制类型转换
        强行将类型范围大的变量、数据赋值给类型范围小的变量。
        */
        int a = 20;
//        byte b = a; // 报错：大范围类型的变量直接赋值给小范围的变量报错，避免类型溢出
        byte b = (byte)a;  // 强制类型转换
        System.out.println(b);

        /*
        强制类型转换可能造成数据(丢失)溢出;
        浮点型强转成整型，直接丢掉小数部分，保留整数部分返回；
        */
        int i = 1500;
        byte b2 = (byte)i;
        System.out.println(i);      // 打印：1500
        System.out.println(b2);     // 打印：-36，因为数据溢出，导致类型转换失真

        double d = 3.14;
        int i2 = (int)d;
        System.out.println(i2); // 打印：3

    }
}
