package com.czm.d5_regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test4 {
    /*
     1、正则表达式查找信息

     */
    public static void main(String[] args) {
        String data ="你的基本信息，\n" +
                "电话:18512516758\n"+
                "或者联系邮箱: boniu@itcast.cn\n" +
                "座机电话:01036517895，010-98951256\n"+
                "邮箱:cai@czm.cn，\n"+ "邮箱2:dlei0009@163.com，\n" +
                "热线电话:400-618-9090，400-618-4000，\n" +
                "4006184000，4006189090\n";
        System.out.println(data);
        System.out.println("-------------- 1、从中间爬取出邮箱 手机号码 座机号码 400号码");
        matcherInfo(data);

    }

    // 从中间爬取出邮箱 手机号码 座机号码 400号码
    public static void matcherInfo(String data) {
        // 从中间爬取出邮箱 手机号码 座机号码 400号码。

        // 1、定义规则对象，封装规则格式
        Pattern pattern = Pattern.compile("(\\w{2,30}@\\w{2,20}(\\.\\w{2,20}){1,2})|" + "(1[3-9]\\d{9})|" + "(0\\d{2,6}-?[1-9]\\d{3,10})|" + "(400-?[1-9]\\d{2,6}-?[1-9]\\d{2,6})");

        // 2、通过匹配规则对象与内容建立联系得到一个匹配器对象
        Matcher matcher = pattern.matcher(data);

        // 3、使用匹配器对象爬取内容
        while (matcher.find()) {
            String info = matcher.group();
            System.out.println("---爬取信息 = " + info);
        }
    }
}
