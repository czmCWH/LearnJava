package com.czm.d3_regex;

public class Test06Boundary {

    /*
     1、边界匹配符一些概念：
        a、终止符(Final Terminator、Line Terminator)，如：\r、\n、\r\n，即回车/换行。
        b、输入，表示整个字符串。
        c、一行，以终止符（或整个输入的结尾）结束的字符串片段
            如果输入是 "dog\ndog\rdog"，那个3个 dog 都是一行。

     2、边界匹配符（Boundary Matcher）
       ⚠️ 边界匹配符表示匹配的位置，不是某个字符。
        \b，单词的边界。指单词开头和结尾的位置。
        ^X，即输入以 X 开头。
        X$，即输入以 X 结尾。

     */

    public static void main(String[] args) {
        System.out.println("--- 👉 1、单词的边界：\\b");
        String regex = "\\bdog\\b";     // 匹配 左右都有单词边界的 dog
        RegexTool.findAll(regex, "abc dog.");   // "dog", [4, 7)
        RegexTool.findAll(regex, "abc dog1.");  // no match
        RegexTool.findAll(regex, "dog 123");    // "dog", [0, 3)
        RegexTool.findAll(regex, "abc,dog,123");    // "dog", [4, 7)

        System.out.println("\n--- 👉 2、非单词的边界：\\B");
        String regex1 = "\\bdog\\B";    // 匹配 左边有单词边界，右边无单词边界的 dog
        RegexTool.findAll(regex1, "abc dog.");      // no match
        RegexTool.findAll(regex1, "abc dog1.");     // "dog", [4, 7)
        RegexTool.findAll(regex1, "dog 123");       // no match

        System.out.println("\n--- 👉 3、匹配开头结尾：^、$");
        String regex2 = "^dog$";    // 匹配 d开头 中间为o g结尾的 子序列
        RegexTool.findAll(regex2, "dog");   // "dog", [0, 3)
        RegexTool.findAll(regex2, " dog");  // no match
        RegexTool.findAll(regex2, "dog\n123");  // no match  为什么没匹配到？因为 Pattern 的 flags 参数值不对，具体看下一节
//        RegexTool.findAll(regex2, "dog\n123", Pattern.MULTILINE);

        System.out.println("\n--- 👉 4、上一次匹配的结尾：\\G");
        String regex3 = "\\Gdog";
        RegexTool.findAll(regex3, "dog");   // "dog", [0, 3)
        RegexTool.findAll(regex3, "dog dog");   // "dog", [0, 3)
        /*
         "dog", [0, 3)
         "dog", [3, 6)
         */
        RegexTool.findAll(regex3, "dogdog");

        System.out.println("\n--- 👉 5、输入的开头和结尾：");
        String regex4 = "\\Adog\\z";
        RegexTool.findAll(regex4, "dog");   // "dog", [0, 3)
        RegexTool.findAll(regex4, "dog\n");  // no match
        RegexTool.findAll(regex4, "dog\ndog\rdog"); // no match

        System.out.println("输入结尾可以为终止符：");
        String regex5 = "\\Dog\\Z";
        RegexTool.findAll(regex5, "dog\n"); //"dog", [0, 3)

    }
}
