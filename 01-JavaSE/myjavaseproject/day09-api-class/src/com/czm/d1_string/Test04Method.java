package com.czm.d1_string;

public class Test04Method {
    /*
     1、String 常用方法
     */
    public static void main(String[] args) {

        String name = "好好学习*abcd*123";
        System.out.println("--- length 获取字符串长度 = " + name.length());

        char ch = name.charAt(2);
        System.out.println("---  charAt 获取某个索引位置处的字符返回 = " + ch);   // 学

        System.out.println("遍历字符串：");
        for (int i = 0; i < name.length(); i++) {
            System.out.print(name.charAt(i) + ", ");
        }
        System.out.println("\n");


        System.out.println("--- trim：去除左右的空格：=" + "   123   456  ".trim());    // "   123   456"
        System.out.println("--- toUpperCase：字母转大写: " + "abc123".toUpperCase());     // "ABC123"
        System.out.println("--- toLowerCase：字母转小写: " + "ABC123".toLowerCase());     // "abc123"
        System.out.println("--- contains：是否包含某个字符串：" + "阿道夫你玩儿".contains("你"));   // true
        System.out.println("--- startsWith：是否以某个字符串开头：" + "123456".startsWith("123"));
        System.out.println("--- endsWith：是否以某个字符串结尾：" + "123456".endsWith("456"));

        System.out.println("\n");

        System.out.println("--- split：把字符串按照某个字符串内容分割，并返回字符串数组");
        System.out.print("[");
        String[] strArray = name.split("\\*");
        for (int i = 0; i < strArray.length; i++) {
            System.out.print(i != strArray.length - 1 ? strArray[i] + ", " : strArray[i]);
        }
        System.out.println("]");
        System.out.println("\n");

        System.out.println("--- A.toCharArray() 将当前字符串转换成字符数组");
        char[] chars = name.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            System.out.print(i != chars.length - 1 ? chars[i] + ", " : chars[i]);
        }
        System.out.println("\n");


        String s1 = new String("好好学习abcd123");
        String s2 = new String("好好学习abCD123");
        // ⚠️ s1 == s2 是比较变量存储的值，即 s1、s2 指向对象的引用地址；equals 比较对象的内容是否相等。
        System.out.println("--- equals：查看字符串的内容是否相等：" + s1.equals(s2));
        System.out.println("--- equalsIgnoreCase：查看字符串的内容是否相等(忽略大小写)：" + s1.equalsIgnoreCase(s2));
        System.out.println("--- A.compareTo(B)：比较字符串大小，如果 A > B 返回大于 0 的数；如果 A 相等 B 返回 0： " + "abc".compareTo("ADC"));
        System.out.println("--- A.compareTo(B)：比较字符串大小(忽略大小写)： " + "abc".compareToIgnoreCase("ADC"));
        System.out.println("\n");


        System.out.println("--- ：A.substring(n)：截取 n 索引开始到末尾的字符串：" + "123456".substring(3)); // 456
        System.out.println("--- A.substring(n, m)：截取索引在 [n,m) 区间的字符串：" + "123456".substring(2, 4));     // 34
        System.out.println("--- A.replace(s1, s2)：把字符串中的 s1 字符串替换为 s2 字符串：" + "你好sb".replace("sb", "世界！"));     // 你好世界！
        String str = "abc3ABCDEF3ABC456";
        int startIdx = str.indexOf("A");
        int endIdx = str.lastIndexOf("3");
        System.out.println("---- 获取某个字符串的索引：[startIdx, endIdx] = " + startIdx + "，" + endIdx);     // 4，10
        
    }
}
