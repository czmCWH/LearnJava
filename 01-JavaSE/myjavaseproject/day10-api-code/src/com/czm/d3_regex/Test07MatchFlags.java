package com.czm.d3_regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test07MatchFlags {

    /*
      1、Pattern 的 flags 参数常用值
        DOTALL，单行模式进行匹配，且 预定义字符.(点) 可以匹配任意字符，包括终止符：\n\r。
        MULTILINE，多行模式进行匹配，(在此模式下^、$ 边界匹配符才能真正匹配一行的开头和结尾)。
        CASE_INSENSIVE，不区分大小写进行匹配。
     */

    public static void main(String[] args) {
        System.out.println("--- 👉 1、正则表达式的编译表示对象 Pattern 的 flags 参数：");
        String regex = "[0-9]";
        String str = "a";
        boolean res = str.matches(regex);
        // 等价于：
        Pattern pattern = Pattern.compile(regex, 0);  // 将字符串形式的正则表达式编译表示为Pattern对象
        Matcher m = pattern.matcher(str);   // 创建匹配器对象，关联目标字符串
        boolean res1 = m.matches();     // 尝试将 整个输入字符串 与 正则表达式匹配
        System.out.printf("res=%s, res1=%s%n", res, res1);

        System.out.println("\n--- 👉 2、MULTILINE 以多行模式进行匹配：");
        String regex1 = "^dog$";    // 匹配 d开头 中间为o g结尾的 子序列
        /*
            "dog", [0, 3)
            "dog", [11, 14)
         */
        RegexTool.findAll(regex1, "dog\n123dog\ndog", Pattern.MULTILINE);
        // 等价写法：
//        RegexTool.findAll("(?m)" + regex1, "dog\n123dog\ndog");

        System.out.println("--- Pattern 的 flags 默认为 0 时，无法匹配到换行:");
        RegexTool.findAll(regex1, "dog\n123dog\ndog");

        System.out.println("\n--- 👉 3、CASE_INSENSIVE 不区分大小写进行匹配：");
        String regex2 = "dog";
        /*
         "dog", [0, 3)
         "Dog", [6, 9)
         "DOG", [9, 12)
         */
        RegexTool.findAll(regex2, "dog123DogDOG", Pattern.CASE_INSENSITIVE);
        // 等价写法：
//        RegexTool.findAll("(?i)" + regex2, "dog123DogDOG");

        System.out.println("--- Pattern 的 flags 默认为 0 时，会区分大小写匹配:");
        RegexTool.findAll(regex2, "dog123DogDOG");

        System.out.println("\n--- 👉 4、DOTALL 单行模式 + 预定义字符.(点) 生效：");
        String regex3 = ".";
        RegexTool.findAll(regex3, "\n\r");  // no match，没有匹配到任何东西

        System.out.println("--- Pattern.DOTALL 预定义点.匹配任意字符：");
        /*
          匹配到了：
            "\r"，[0, 1)
            "\n"，[1, 2)
         */
        RegexTool.findAll(regex3, "\r\n", Pattern.DOTALL);
    }
}
