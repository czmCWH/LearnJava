package com.czm.api02string;

public class StringDemo2 {
    /*
     1、String 常用方法
     */
    public static void main(String[] args) {
        // 1、获取字符串的长度返回(就是字符个数)
        String name = "好好学习*abcd*123";
        System.out.println("--- 1、获取字符串长度 = " + name.length());

        // 2、获取某个索引位置处的字符返回
        char ch = name.charAt(2);
        System.out.println("--- 2、获取某个索引位置处的字符返回 = " + ch);
        System.out.println("遍历字符串：");
        for (int i = 0; i < name.length(); i++) {
            System.out.print(name.charAt(i) + ", ");
        }
        System.out.println();

        // 3、将当前字符串转换成字符数组
        char[] chars = name.toCharArray();
        System.out.println("--- 3、将当前字符串转换成字符数组");
        for (int i = 0; i < chars.length; i++) {
            System.out.print(i != chars.length - 1 ? chars[i] + ", " : chars[i]);
        }
        System.out.println();

        // 4、返回判断当前字符串与另一个字符串的内容一样，一样返回 true;
        String n1 = new String("好好学习abcd123");
        System.out.println("--- 4、返回判断当前字符串与另一个字符串的内容一样，一样返回 true;");
        String n2 = name;
        System.out.println(n1 == n2);
        System.out.println(n2 == name);
        System.out.println(n1.equals(name));

        // 5、判断当前字符串与另一个字符串的内容是否一样(忽略大小写)
        String n3 = new String("好好学习abCD123");
        System.out.println("---  5、判断当前字符串与另一个字符串的内容是否一样(忽略大小写)");
        System.out.println(n3.equals(name));
        System.out.println(n3.equalsIgnoreCase(name));

        // 6、根据开始和结束索引进行截取，得到新的字符串(包前不包后)
        System.out.println("--- 6、根据开始和结束索引进行截取，得到新的字符串(包前不包后)");
        String subStr = name.substring(0, 2);
        System.out.println(subStr);

        // 7、从传入的索引处截取，截取到未尾，得到新的字符串返回
        System.out.println("--- 7、从传入的索引处截取，截取到未尾，得到新的字符串返回");
        String subStr2 = name.substring(2);
        System.out.println(subStr2);

        // 8、使用新值，将字符串中的旧值替换，得到新的字符串
        System.out.println("--- 8、使用新值，将字符串中的旧值替换，得到新的字符串");
        String cS1 = "你是个傻逼，大傻逼abcd123";
        String nr = cS1.replace("傻逼", "**");
        System.out.println(nr);

        // 9、判断字符串中是否包含了某个字符串
        System.out.println("--- 9、判断字符串中是否包含了某个字符串");
        System.out.println(name.contains("好好学习"));
        System.out.println(name.contains("天天向上"));

        // 10、判断字符串是否以某个字符串内容开头，开头返回 true
        System.out.println("--- 10、判断字符串是否以某个字符串内容开头，开头返回 true");
        System.out.println(name.startsWith("好好学习"));
        System.out.println(name.endsWith("123"));

        // 11、把字符串按照某个字符串内容分割，并返回字符串数组
        System.out.println("--- 11、把字符串按照某个字符串内容分割，并返回字符串数组");
        String[] strArray = name.split("\\*");
        for (int i = 0; i < strArray.length; i++) {
            System.out.print(i != strArray.length - 1 ? strArray[i] + ", " : strArray[i]);
        }

    }
}
