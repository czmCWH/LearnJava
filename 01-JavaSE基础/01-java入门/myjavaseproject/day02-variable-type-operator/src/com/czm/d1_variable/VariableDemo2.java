package com.czm.d1_variable;

public class VariableDemo2 {
    public static void main(String[] args) {

        // 目标：⚠️⚠️⚠️ java 基本数据类型：4大类，总共8种。

        // 1、byte 字节整型
        byte b = 127;
        System.out.println(b);

        // 2、short 短整型
        short s = 127;
        System.out.println(s);

        // 3、int 整型（默认类型）
        int i = 127;
        System.out.println(i);

        // 4、long 长整型
        // 注意：随便写的整数字面量默认是 int 类型，如果需要让其表示 long 类型需要在其后面带上 L/l
        long l = 127L;
        System.out.println(l);

        // 5、float 单精度浮点型，基本淘汰，因为其表示的数据精度不准。
        // 注意：随便写的小数字面量默认是 double 类型，如果希望是 float 需要加上 F/f
        float f = 127.5F;
        System.out.println(f);

        // 6、double 双精度浮点型
        double d = 127.5D;
        System.out.println(d);

        // 7、char 字符型
        char c = '我';
        System.out.println(c);

        // 8、boolean 布尔型
        boolean bool = true;
        System.out.println(bool);

        // 9、⚠️ String 字符串类型，是引用数据类型，不是基本数据类型
        String str = "好好学习";
        System.out.println(str);

    }
}
