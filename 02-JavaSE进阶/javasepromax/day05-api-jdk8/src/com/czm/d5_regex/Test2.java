package com.czm.d5_regex;

public class Test2 {
    /*
     1、正则表达式书写规则
     [abc]      只能是a 或 b 或 c
     [^abc]     除了a,b,c之外任何字符
     [a-zA-Z]   包括 a到z，A到Z 的范围


     2、
     */
    public static void main(String[] args) {
        System.out.println("----- 1、字符类（只能匹配单个字符）");
        System.out.println("a".matches("[abc]]"));      // 只能是 a/b/c 的字符
        System.out.println("e".matches("[abcd]"));

        System.out.println("a".matches("[^abc]]"));     // 不能是 a/b/c 的字符
        System.out.println("d".matches("[^abc]"));

        System.out.println("f".matches("[a-zA-Z]"));    // 只能是 a-zA-Z 范围内的字符
        System.out.println("2".matches("[a-zA-Z]"));

        System.out.println("k".matches("[a-z&&[^bc]]"));    // a到z，除了bc
        System.out.println("b".matches("[a-z&&[^bc]]"));

        System.out.println("b".matches("[a-zA-Z0-9]"));    // 只能是 字母、数字 的字符

        // ⚠️：以上带 [内容] 的规则都只能用于匹配单个字符
        System.out.println("ab".matches("[a-zA-Z0-9]"));

        // 2、预定义字符（只能匹配单个字符）
        System.out.println("您".matches("."));       // . 匹配任意字符
        System.out.println("你好".matches("."));    // false

        // 在Java中，\是有特殊用途的，一般作为特殊字符使用不能独立存在，例如：特殊字符 \n（空格） \t(换行)
        // \ 需要使用 \ 转义
        System.out.println("-----  \\d 或者 \\D ");
        System.out.println("1".matches("\\d"));
        System.out.println("12".matches("\\d"));

        System.out.println("---- \\s 或者 \\S ");
        // \s：代表一个空白字符
        // \S：代表一个非空白字符
        System.out.println(" ".matches("\\s"));
        System.out.println("a ".matches("\\s"));

        System.out.println(" ".matches("\\S"));
        System.out.println("a ".matches("\\S"));

        System.out.println("---- \\w 或者 \\W");
        // \w ：代表 [a-zA-Z0-9]
        // \W ：代表不能是  [a-zA-Z0-9] 的字符
        System.out.println("a".matches("\\w"));
        System.out.println("_".matches("\\w"));
        System.out.println("你".matches("\\w"));

        System.out.println("你".matches("\\W"));
        System.out.println("a".matches("\\W"));

        System.out.println("以上预定一字符都只能匹配单个字符");

        System.out.println("------------------ 3、数量词");
        System.out.println("a".matches("\\w?"));        // ? 代表0次或1次
        System.out.println("".matches("\\w?"));
        System.out.println("abc".matches("\\w?"));

        System.out.println("".matches("\\w*"));      // * 代表0次或多次
        System.out.println("abcZA98".matches("\\w*"));
        System.out.println("abc你".matches("\\w*"));

        System.out.println("".matches("\\w+"));      // + 代表1次或多次
        System.out.println("abcZ12".matches("\\w+"));
        System.out.println("abc你".matches("\\w+"));

        System.out.println("ab1".matches("\\w{3}"));    // {n} 代表正好是n次
        System.out.println("ab".matches("\\w{3}"));

        System.out.println("ab".matches("\\w{3,}"));    // {n,} 代表 >= n次
        System.out.println("ab12".matches("\\w{3,}"));

        System.out.println("asdf123".matches("\\w{3,9}"));  // {n,m} 代表 大于等于n次，小于等于m次

        System.out.println("----------------------- 4、其它几个常用的符号：");
        System.out.println("abc".matches("(?i)abc"));       // (?i) 忽略大小写
        System.out.println("ABC".matches("(?i)abc"));
        System.out.println("aBc".matches("a((?i)b)c"));
        System.out.println("bAc".matches("a((?i)b)c"));

        System.out.println("案例1 ： 要求要么是3个小写字母，要么是3个数字");
        System.out.println("980".matches("(\\d{3})|([a-z]{3})"));
        System.out.println("adf".matches("(\\d{3})|([a-z]{3})"));
        System.out.println("ad1".matches("(\\d{3})|([a-z]{3})"));

        System.out.println("案例2 ： 必须是‘爱我’开头，中间可以是至少一个‘编程'，最后至少是一个666 ");
        System.out.println("我爱编程编程".matches("我爱(编程)+(666)+"));








    }
}
