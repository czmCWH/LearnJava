package com.czm.d5_regex_x;

import java.util.Arrays;

public class Test6 {
    /*
     1、搜索、分割
        正则表达式用于 搜索替换、分割内容 ，需要结合 String 提供如下方法完成：

        a、对 String 按照正则表达式匹配的内容进行替换，返回一个新的字符串；
        b、按照正则表达式匹配的内容进行分割字符串，返回一个字符串数组；
     2、
     */
    public static void main(String[] args) {
        // 案例1：把字符串中非中文替换为
        String str = "你好abfsd123好看些fsdfsa123好好学习adfsdf123";

        // a、对 String 按照正则表达式匹配的内容进行替换；
        String s1 = str.replaceAll("\\w+", "+");
        System.out.println(s1);

        // b、按照正则表达式匹配的内容进行分割字符串，返回一个字符串数组；
        String[] array = str.split("\\w+");
        System.out.println(Arrays.toString(array));

    }
}
