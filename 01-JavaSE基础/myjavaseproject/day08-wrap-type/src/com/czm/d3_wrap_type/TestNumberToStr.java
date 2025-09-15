package com.czm.d3_wrap_type;

public class TestNumberToStr {
    /*
	  1、包装类的其他常见操作

	    a、字符串类型的数值 转换成 数值本身对应的 数字类型（⚠️ 很有用）
            public static int parseInt(String x);       parse 转换为基本类型
            public static Integer valueOf(String str);   valueOf 转换为包装类型的对象

        b、可以把 数字 转换  String 包装类型
            public static String toString(T x);
            public String toString();


     */
    public static void main(String[] args) {
        int a = 18;

        // 1、把基本类型通过包装类包装成对象。
        Integer a1 = new Integer(a);    // 生成一个新的对象
        Integer a2 = Integer.valueOf(a);    // 会根据 Integer 缓存判断是否生成新的对象

        System.out.println("\n\n");

        // 2、字符串转数字
        String scoreStr = "99.123123";
        // 方法1:
        double s1 = Double.parseDouble(scoreStr);
        // 方法2：
        Double s2 = Double.valueOf(scoreStr);
        System.out.println("s1 = " + s1);
        System.out.println("s2 = " + s2);

        Character c1 = 'A';
        System.out.println("⚠️ 字符类型 转换为 int 类型，直接在前面添加 + 即可 = " + (+c1));      // 打印：65

        // 解析十六进制
        int i1 = Integer.parseInt("FF", 16);
        System.out.println("--- 解析16进制字符串 FF = " + i1);     // 255

        // 3、基本类型的数字 转换  String 包装类型
        String str1 = String.valueOf(12.34);
        System.out.println("str1 = " + str1);

        int num = 255;
        String str2 = Integer.toString(num);
        System.out.println("str2 = " + str2);

        Integer n1 = num;   // 自动包装
        String str3 = n1.toString();
        System.out.println(str3);    // 打印："231"

        String str4 = Integer.toString(num, 16);
        System.out.println("--- 把 255 转换为16进制字符串 = " + str4);   // ff，⚠️是小写的

        String str5 = num + "";
        System.out.println("--- 使用 + 转换 str5 = " + str5);


    }
}
