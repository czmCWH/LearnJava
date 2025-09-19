package com.czm.d3_regex;

public class Test04Quantifier {

    /*
     1、量词 的三种形式/模式
        a、Greedy(贪婪)
        匹配步骤：
            1、先对整个 input 匹配，如果匹配失败，则去掉末尾一个字符再次匹配；
            2、如果匹配成功，则去掉
            2、重复此匹配过程，直到匹配成功，则结束；
        总结：按照最长的子串来匹配。

        b、Reluctant(勉强)
        匹配步骤：
            1、从左到右，先对第一个字符匹配；
            2、如果匹配失败，再添加下一个字符匹配；如果匹配成功，则从下一个字符重新开发匹配；
            3、重复此过程，直到全部匹配完毕；
        总结：按照最短的子串来匹配

        c、Possessive(独占) - 了解
           对整个 input 进行唯一的一次匹配。

     */

    public static void main(String[] args) {
        String input = "afooaaaaaafooa";
        System.out.println("--- 👉 1、贪婪");
        RegexTool.findAll(".*foo", input);  // ".*foo" 匹配 `任意n字符 + foo` 的子串，"afooaaaaaafoo", [0, 13)

        /*
        为什么此处不是直接打印一个： "dd", [0, 2) ？？？既然是 Greedy 模式，那么匹配成功就该结束。
        因为 findAll 方法中，进行多次 find，所以会多次进行 Greedy 形式的匹配。
        "dd", [0, 2)
        "ddd", [3, 6)
        "dddd", [7, 11)
         */
        RegexTool.findAll("d+", "dd_ddd_dddd"); // "d+" 匹配至少1个连续的d

        System.out.println("\n--- 👉 2、勉强");
        RegexTool.findAll(".*?foo", input);     // ".*?foo"，量词后面加了 ?

        System.out.println("\n--- 👉 3、独占");
        RegexTool.findAll(".*+foo", input);     // ".*+foo"，量词后面加了 +

    }
}
