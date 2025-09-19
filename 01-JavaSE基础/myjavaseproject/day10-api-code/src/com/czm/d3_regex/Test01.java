package com.czm.d3_regex;

public class Test01 {
    /*
      1、正则表达式 Regex Expression
       正则表达式用于字符串的合法验证。
       正则表达式用非常精简的语法取代了复杂的验证逻辑，极大地提高了开发效率。
       正则表达式是一种通用的技术，适用于绝大多数流行编程语言。

      2、正则表达式基础语法
        a、单字符匹配：[] 表示匹配数组中的某个元素；单纯的n个字符需要完全匹配。
            abc1-5，表示完全匹配 "abc1-5"。
            [abc]、[a|b|c]、(a|b|c)，表示该字符为 a 或 b 或 c。
            [^abc]，表示该字符为除了a、b、c 以外任意字符。⚠️^在[]表示异或符号，^在任意字符前面表示以此字符开头。
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
            \w，表示[a-zA-Z_0-9]单词，⚠️即大小写字母、数字、下划线。
            \W，表示[^\w]非单词。

        c、量词（Quantifier），表示字符出现的次数，有3种形式/形态。

        d、
     */
    public static void main(String[] args) {
        System.out.println("--- 自己编写字符串合法校验：");
        validate(null);
        validate("afdsa");
        validate("_12312313afdsa");

        System.out.println("--- 使用正则表达式校验：");
        String regex = "[a-zA-Z][a-zA-Z0-9_]{5,17}";    // 这是一个正则表达式
        System.out.println("_sdfsdfsdfd".matches(regex));   // false
        System.out.println("sdf1233243sdf".matches(regex));     // true

    }

    // 实现文本校验：6~18个字符，可使用字母、数字、下划线，需以字母开头
    public static boolean validate(String email) {
        if (email == null) {
            System.out.println("--- 不能为空");
            return false;
        }
        char[] chars = email.toCharArray();
        if (chars.length < 6 || chars.length > 18) {
            System.out.println("--- 必须 6~18 个字符");
            return false;
        }
        if (!isLetter(chars[0])) {
            System.out.println("--- 必须以字母开头");
            return false;
        }
        for (int i = 1; i < chars.length; i++) {
            char c = chars[i];
            if (isLetter(c) || isDigit(c) || c == '_') continue;
            System.out.println("--- 必须以字母、数字、下划线组成");
            return false;
        }
        return true;
    }

    // 判断是否是字母
    private static boolean isLetter(char ch) {
        return (ch >= 'A' && ch <= 'Z') || (ch >= 'a' && ch <= 'z');
    }

    // 判断是否是数字
    private static boolean isDigit(char ch) {
        return (ch >= '0' && ch <= '9');
    }
}
