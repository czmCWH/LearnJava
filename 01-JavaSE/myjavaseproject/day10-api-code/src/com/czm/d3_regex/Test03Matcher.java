package com.czm.d3_regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test03Matcher {

    /*
     1、Pattern、Matcher 类
     String 的 matches 方法是完全匹配，其底层用到了 Pattern、Matcher 两个类。
        java.util.regex.Pattern
        java.util.regex.Matcher

     2、Matcher 常用方法
        boolean matches()，完全匹配，需整个 input 与 regex 匹配，才返回 true。

        boolean find()，如果从 input 中找到了与 regex 匹配的 `子序列`，就返回 true，然后可以使用 start、end、group 方法获取详细的子序列信息。
                        每调用一次 find 其查找范围会先剔除此前已经查找过的范围。

        int start()，返回上一次匹配成功的开始索引

        int end()，返回上一次匹配成功的结束索引

        String group()，返回上一次匹配成功的 input 子序列

     ⚠️ 应用场景：通过正则表达式，搜索出符合条件的字符串。
     */

    public static void main(String[] args) {

        System.out.println("---- 👉 1、String 的 matches 方法展开：");
        String regex = "[0-9]";
        String str = "a";
        // String 的 matches 方法：
        boolean res = str.matches(regex);
        // 等价于：
        boolean res1 = Pattern.matches(regex, str);
        // 等价于：
        Pattern pattern = Pattern.compile(regex);  // 将字符串形式的正则表达式编译为Pattern对象
        Matcher m = pattern.matcher(str);   // 创建匹配器对象，关联目标字符串
        boolean res2 = m.matches();     // 尝试将 整个输入字符串 与 正则表达式匹配
        System.out.printf("res=%s, res1=%s, res2=%s%n", res, res1, res2);

        System.out.println("\n--- 👉 2、Matcher 类的常用方法：");
        String str1 = "123_444_555_666_789";
        String regex2 = "\\d{3}";   // 匹配 3个任意数字
        System.out.println(str1.matches(regex2));    // false，matches 方法表示是否完全匹配 regex。而 regex2 表示 3个任意数字 的字符串，所以返回 false。

        Pattern p2 = Pattern.compile(regex2);
        Matcher m2 = p2.matcher(str1);
        while (m2.find()) {    // find 方法，表示在 str1 中从左往右找是否存在子串完全匹配 regex2
            System.out.println(m2.group());     // group 方法，获取匹配成功的子串
            // 匹配成功子串的范围是 [m.start(), m.end())
            System.out.println(m2.start());     // start 方法，匹配到的开始位置
            System.out.println(m2.end());       // end 方法，匹配的结束位置
        }

        System.out.println("\n--- 👉 3、封装 Matcher 方法，正则匹配出所有的子串：");
        RegexTool.findAll("\\d{3}", "你好213_123&1238123");

        System.out.println("\n--- 👉 4、Matcher 案例：");
        matcherExample();

    }



    // Matcher 使用案例：
    public static void matcherExample() {
        System.out.println("使用 findAll 封装方法，查找 123 子序列：");
        String regex = "123";
        RegexTool.findAll(regex, "123");
        RegexTool.findAll(regex, "6_123_123_abc_12_123");


        System.out.println("使用 findAll 封装方法，查找 连续2个数字 的子序列：");
        String regex2 = "\\d{2}";
        RegexTool.findAll(regex2, "2_123_2390");

        System.out.println("使用 findAll 封装方法，查找 连续3个a|b|c字符 的子序列");
        String regex3 = "[abc]{3}";  // 等价于 [abc][abc][abc]
        RegexTool.findAll(regex3, "abc_aaacccbbb_bbc");

        System.out.println("使用 findAll 封装方法，查找匹配次数：");
        System.out.println("a?，a出现0或1次：");
        RegexTool.findAll("a?", "");
        RegexTool.findAll("a?", "a");
        RegexTool.findAll("a?", "aaa");
        System.out.println("a*，a出现0或n次：");
        RegexTool.findAll("a*", "");
        RegexTool.findAll("a*", "a");
        RegexTool.findAll("a*", "aaa");
        System.out.println("a+，a至少出现1次：");
        RegexTool.findAll("a+", "");
        RegexTool.findAll("a+", "aaa");

        String input = "abbaaa";
        RegexTool.findAll("a?", input);
        RegexTool.findAll("a*", input);
        RegexTool.findAll("a+", input);

    }
}
