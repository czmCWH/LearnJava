package com.czm.d3_regex;

public class Test02Syntax {

    /*
      1、正则表达式基础语法
        a、单字符匹配：[] 表示匹配数组中的某个元素；单纯的n个字符需要完全匹配。
            abc1-5，表示完全匹配 "abc1-5"。
            [abc]、[a|b|c]、(a|b|c)，表示该字符为 a 或 b 或 c。
            [^abc]，^异或符号，表示该字符为除了a、b、c 以外任意字符。
            abc[1-5]，表示 "abc" + 1到5之间任意一个数 的字符串。
            abc[^1-5]，表示 "abc" + 除了1到5之间任意一个数 的字符串。
            [a-d[e-f]]，表示 [a-d] 与 [f-l] 的并集，即：[a-df-l]
            [a-z&&[abc]]，表示 [a-z] 与 [abc] 的交集，即：[abc]
            [a-z&&[^bc]]，表示 [a-z] 与 [bc] 的差集，即：[ad-z]
            [a-z&&[^c-g]]，表示 [a-z] 与 [c-g] 的差集，即：[a-bh-z]

        b、预定义字符，用于匹配特殊含义的字符。正则表达式中以2个反斜杠开头来表示预定义字符，因为以1个反斜杠(\)开头的字符会被当作转义字符
            .(点)，表示任意字符。
            \d，表示[0-9]数字。
            \D，表示[^0-9]非数字。
            \s，表示[ \t\n\f\r]空白。
            \S，表示[^\s]非空白。
            \w，表示[a-zA-Z_0-9]单词，即大小写字母、数字、下划线。
            \W，表示[^\w]非单词。

        c、量词（Quantifier），表示匹配字符出现的次数，有3种形式。

     */

    public static void main(String[] args) {

        // 1、匹配单个字符
//        matchesSingle();

        // 2、使用预定义字符匹配
//        matchesPredefined();

        // 3、量词的使用
        matchesNumber();

    }

    // 👉 1、匹配单个字符
    private static void matchesSingle() {
        String regex = "abc";
        System.out.println("abc".matches(regex));
        System.out.println("ab".matches(regex));

        String regex1 = "[bcr]at";
        System.out.println("bat".matches(regex1));  // true
        System.out.println("cat".matches(regex1));  // true
        System.out.println("rat".matches(regex1));  // true
        System.out.println("bcat".matches(regex1)); // false

        String regex2 = "[^bcr]at";
        System.out.println("bat".matches(regex2));
        System.out.println("cat".matches(regex2));
        System.out.println("rat".matches(regex2));
        System.out.println("hat".matches(regex2));

        String regex3 = "foo[1-5]]";
        System.out.println("foo1".matches(regex3));
        System.out.println("foo6".matches(regex3));

        String regex4 = "foo[^1-5]";
        System.out.println("foo1".matches(regex4));
        System.out.println("foo6".matches(regex4));

        String regex5 = "[0-4[6-8]]";
        System.out.println("6".matches(regex5));    // true
        System.out.println("5".matches(regex5));    // false

        String regex6 = "[0-9&&[345]]";
        System.out.println("5".matches(regex6));    // true
        System.out.println("6".matches(regex6));    // false

        String regex7 = "[0-9&&[^345]]";
        System.out.println("0".matches(regex7));    // 0
        System.out.println("3".matches(regex7));    // false
    }

    // 👉 2、使用预定义字符匹配
    private static void matchesPredefined() {
        System.out.println("--- 任意字符：");
        String regex1 = ".";    // 匹配任意单个字符
        System.out.println("好".matches(regex1));     // true
        System.out.println("@".matches(regex1));     // true
        System.out.println("@2123".matches(regex1)); // false

        System.out.println("\n--- 转义字符：");
        String regex2 = "\\[abc\\]";    // 只匹配 [abc] 字符串
        System.out.println("abc".matches(regex2));  // false
        System.out.println("[abc]".matches(regex2));    // true
        String regex3 = "\\.";      // 只匹配 . 点字符
        System.out.println(".".matches(regex3));    // true
        System.out.println("&".matches(regex3));    // false

        System.out.println("\n--- 数字非数字：");
        String regex4 = "\\d";  // 只匹配数字
        System.out.println("0".matches(regex4));    // true
        System.out.println("a".matches(regex4));    // false
        String regex5 = "\\D";  // 只匹配非数字
        System.out.println("0".matches(regex5));    // false
        System.out.println("a".matches(regex5));    // true

        System.out.println("\n--- 空白：");
        String regex6 = "\\s";  // 匹配空白字符，如下都为 true
        System.out.println(" ".matches(regex6));    // true
        System.out.println("\t".matches(regex6));   // true
        System.out.println("\n".matches(regex6));   // true
        System.out.println("\f".matches(regex6));   // true
        System.out.println("\r".matches(regex6));   // true
        String regex7 = "\\S";  // 匹配非空白字符，" "、"\t"、"\n"、"\f"、"\r" 匹配后都是false
        System.out.println(" ".matches(regex7));    //  false

        System.out.println("\n--- 单词（即：大小写字母、数字、下划线）：");
        String regex8 = "\\w";  // 匹配单词
        System.out.println("a".matches(regex8));    // true
        System.out.println("9".matches(regex8));    // true
        System.out.println("_".matches(regex8));    // true
        System.out.println("*".matches(regex8));    // false
        String regex9 = "\\W";  // 匹配非单词
        System.out.println("a".matches(regex9));    // false
        System.out.println("9".matches(regex9));    // false
    }

    // 👉 3、量词的使用
    private static void matchesNumber() {
        System.out.println("--- X{n}，X 出现 n 次：");
        String regex = "6{3}";  // 匹配3个6
        System.out.println("66".matches(regex));    // false
        System.out.println("666".matches(regex));   // true
        System.out.println("6666".matches(regex));  // false

        System.out.println("\n--- X{n,m}，X 出现 n 到 m 次：");
        String regex2 = "6{2,5}";   // 字符6出现2到5次
        System.out.println("666".matches(regex2));      // true
        System.out.println("66666".matches(regex2));    // true
        System.out.println("666666".matches(regex2));   // false

        System.out.println("\n--- X{n,}，X 至少出现 n 次：");
        String regex3 = "6{2,}";    // 6至少出现2次
        System.out.println("6".matches(regex3));    // false
        System.out.println("66666".matches(regex3));    // true

        System.out.println("\n--- X?，X出现0次或1次：");
        String regex4 = "6?";
        System.out.println("66".matches(regex4));    // false
        System.out.println("6".matches(regex4));  // true

        System.out.println("\n--- X*，X出现0次或n次：");
        String regex5 = "6*";
        System.out.println("66".matches(regex5));   // true
        System.out.println("6".matches(regex5));    // true
        System.out.println("7".matches(regex5));    // false
        System.out.println("".matches(regex5));    // true

        System.out.println("\n--- X+，X至少出现1次：");
        String regex6 = "6+";
        System.out.println("66".matches(regex6));   // true
        System.out.println("6".matches(regex6));    // true
        System.out.println("7".matches(regex5));    // false
    }

}
