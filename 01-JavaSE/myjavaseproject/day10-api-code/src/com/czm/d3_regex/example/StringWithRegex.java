package com.czm.d3_regex.example;

public class StringWithRegex {
    /*
      1、String 类与正则表达式
       String 类中接受正则表达式作为参数的常用方法有：
            public String replaceAll(String regex, String replacement)
            public String replaceFirst(String regex, String replacement)
            public String[] split(String regex, int limit)

     */
    public static void main(String[] args) {
        System.out.println("--- 1、替换字符串中的单词：");
        String text2 = "Tomorrow I will wear in brown standing in row 10.";
        String s4 = text2.replaceAll("row", "line");
        System.out.println("--- s4 = " + s4);
        String s5 = text2.replaceAll("\\brow\\b", "line");
        System.out.println("--- s5 = " + s5);




    }
}
