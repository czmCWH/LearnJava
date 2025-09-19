package com.czm.d3_regex.example;

import com.czm.d3_regex.RegexTool;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test01 {

    /*
     1、常用正则表达式
        a、正则表达式在线：https://www.jyshare.com/

     */

    public static void main(String[] args) {
        // 1、匹配 18 位身份证号码
        String regex = "\\d{17}[\\dXx]";
        System.out.println("--- 身份证号：" + "42112999950820211X".matches(regex));

        // 2、匹配中文字符
        String regex2 = "[\\u4e00-\\u9fa5]+";
        System.out.println("--- 中文字符：" + "哈哈哈你".matches(regex2));

        // 3、替换字符串中的数字
        String numStr = "ab1 sdfs12 aaa 1 bbb123";
        System.out.println("--- 将连续的数字替换为**: " + numStr.replaceAll("\\d+", "**"));

        // 4、使用数字分割字符串
        String numStr2 = "ab1sdfs12aaa1bbb123";
        String[] numList = numStr2.split("\\d+");
        for (String num : numList) {
            System.out.print(num + ", ");
        }
        /* 结果：
            ab, sdfs, aaa, bbb,
        */

        // 5、提取重叠的字母和数字格式的字母、数字
        System.out.println("\n\n--- 使用捕获组：");
        // 比如：“aa33”，提取出 a、3
        String str = "aa11_bb23-mj33*dd44/5566%ff77-88gg";
        String regex3 = "([a-zA-Z])\\1(\\d)\\2";    // ([a-zA-Z])\\1，表示 单个字母为一组，并且重复1次；(\d)\\2，表示单个数组为一组，并重复一次
        Pattern p = Pattern.compile(regex3);
        Matcher m = p.matcher(str);
        while (m.find()) {
//            System.out.println(m.group());  // m.group() 获取的是整个匹配结果
            System.out.print(m.group(1) + "_" + m.group(2) + ", ");  // m.group(n) 捕获组，即获取第 n 组数据
        }
        System.out.println("\n");
        /*
          结果：a_1, d_4, f_7,
         */

        // 6、提取出“字母字母数字数字”格式的最后一个数字
        // 比如：ab12 提取 2
        String str2 = "aa12+bb34-mj56*dd78/9900";
        String regex4 = "[a-z]{2}\\d(\\d)";  // [a-z]{2} 表示任意字母出现2次；\\d(\\d) 表示2个任意数字，由于末尾的数字我需要提取，所以单独分组
        Pattern p2 = Pattern.compile(regex4);
        Matcher m2 = p2.matcher(str2);
        while (m2.find()) {
            System.out.print(m2.group(1) + ", ");
        }
        System.out.println("\n");
        /* 结果：
            2, 4, 6, 8,
         */


    }
}
