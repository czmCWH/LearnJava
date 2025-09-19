package com.czm.d3_regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 封装正则查找 子序列
 */
public class RegexTool {

    /**
     * 在 input 中查找与 regex 匹配的 `子序列`，并打印此子序列，以及索引
     * @param regex 正则表达式
     * @param input 用于匹配的字符串
     */
    public static void findAll(String regex, String input) {
        findAll(regex, input, 0);
    }

    public static void findAll(String regex, String input, int flags) {
        if (regex == null || input == null) return;
        Pattern pattern = Pattern.compile(regex, flags);
        Matcher matcher = pattern.matcher(input);
        boolean found = false;
        while (matcher.find()) {
            found = true;
            System.out.format("\"%s\", [%d, %d)%n", matcher.group(), matcher.start(), matcher.end());
        }
        if (!found) {
            System.out.println("no match");
        }
    }
}
