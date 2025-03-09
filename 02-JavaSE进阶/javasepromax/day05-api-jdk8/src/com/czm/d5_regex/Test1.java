package com.czm.d5_regex;

public class Test1 {
    /*
     1、正则表达式尝试
        正则表达式就是由一些特定的字符组成，代表的是一个规则。

     2、正则表达式的作用：
        1、用来校验数据格式是否合法。
        2、在一段文本中查找满足要求的内容。

     3、String 提供了一个匹配正则表达式的方法
            public boolean matches(string regex)
            判断字符串是否匹配正则表达式，匹配返回true，不匹配返回false。
     */
    public static void main(String[] args) {
        System.out.println(checkQQ("0123123"));
        System.out.println(checkQQ("a123123"));
        System.out.println(checkQQ("123b123"));
        System.out.println(checkQQ("12323"));
        System.out.println(checkQQ("123256"));
        System.out.println("-------------------------------");
        System.out.println(checkQQ2("0123123"));
        System.out.println(checkQQ2("a123123"));
        System.out.println(checkQQ2("123b123"));
        System.out.println(checkQQ2("12323"));
        System.out.println(checkQQ2("123256"));
    }
    // 校验QQ号：必须5位以上，全部是数字，不能以0开头；
    public static Boolean checkQQ(String qq) {
        if (qq == null || qq.length() <= 5 || qq.startsWith("0")) {
            return false;
        }
        // 1、循环检查是否都是数字
        for (int i = 0; i < qq.length(); i++) {
            char c = qq.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }
    // 正则表达式校验
    public static Boolean checkQQ2(String qq) {
        return qq != null && qq.matches("[1-9]\\d{5,}");
    }
}
