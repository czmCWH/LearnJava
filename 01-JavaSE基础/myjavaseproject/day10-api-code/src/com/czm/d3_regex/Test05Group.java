package com.czm.d3_regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test05Group {

    /*
     1、捕获组（Capturing Group）
        捕获组的表示形式为：(任意个字符)。
        表示小括号内的所有字符为一个整体去匹配。
      注意：(a|b|c)  等价于 [abc]

     2、捕获组 - 反向引用（Backreference）
     反向引用（Backreference）的语法：反斜杠(\) + 组编号(从1开始)，来表示引用某个组的内容到该位置重复一次
     如：(abc)(123)(ABC)\\1\\2 ==> (abc)(123)(ABC)(123)(abc)

     反向引用的编号：((A)(B(C))) 一共有4个组。
        编号1，((A)(B(C)))
        编号2，(A)
        编号3，(B(C))
        编号4，(C)

     */

    public static void main(String[] args) {

        // 1、捕获组
//        regexGroup();

        // 2、捕获组 - 反向引用
        regexGroupBackReference();

        // 3、获取捕获组内容
        String str = "aa12+bb11_cc22";
        // [a-z]{2} 表示任意字母出现2次；\\d(\\d) 表示2个任意数字，由于末尾的数字我需要提取，所以单独分组
        String regex = "[a-z]{2}\\d(\\d)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(str);
        while (m.find()) {
            System.out.print(m.group(1) + ", ");   // m.group(1) 获取捕获组中的内容
        }
        System.out.println("\n");

    }

    // 👉 1、正则表达式的 捕获组
    private static void regexGroup() {
        String regex = "dog{3}";    // 匹配 `do + 3次g` 的字符串
        System.out.println("doggg".matches(regex));      // true

        String regex1 = "[dog]{3}";     // 匹配 `d或o或g 出现3次` 的字符串
        System.out.println("ddd".matches(regex1));   // true
        System.out.println("ooo".matches(regex1));   // true
        System.out.println("dog".matches(regex1));   // true

        System.out.println("--- 捕获组：");
        // 捕获组
        String regex2 = "(dog){3}";     // 匹配 `dog出现3次` 的字符串
        System.out.println("dog".matches(regex2));  // false
        System.out.println("dogdogdog".matches(regex2));    // true

        String regex3 = "(d|o|g){3}";  // ==> ⚠️ 等价于：[dog]{3}
        System.out.println("ddd".matches(regex3));  // true
        System.out.println("doo".matches(regex3));  // true
    }

    // 👉 2、捕获组 - 反向引用
    private static void regexGroupBackReference() {
        System.out.println("--- 捕获组 - 反向引用：");
        String regex = "(abc)(123)(ABC)\\2\\1";
        System.out.println("abc123ABC123abc".matches(regex));   // true
        System.out.println("abc123ABCabc123".matches(regex));   // false

        String regex1 = "(\\d\\d)\\1";
        System.out.println("1212".matches(regex1));     // true
        System.out.println("1234".matches(regex1));     // false

        String regex2 = "([a-z]{2})([A-Z]{2})\\2\\1";
        System.out.println("abPKPKab".matches(regex2));     // true
        System.out.println("abPKabPK".matches(regex2));     // false

        System.out.println("--- 捕获组 - 反向引用的编号：");
        String regex3 = "((I)( Love( You)))\\3{2}";
        System.out.println("I Love You Love You Love You".matches(regex3));     // true
    }
}
