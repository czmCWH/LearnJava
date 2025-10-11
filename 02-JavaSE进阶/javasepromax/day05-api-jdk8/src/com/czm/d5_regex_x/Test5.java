package com.czm.d5_regex_x;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test5 {

    /*
     1、把匹配规则使用 () 来分组，这样爬取使可按组取值
     */

    public static void main(String[] args) {
        String data ="你的基本信息，\n" +
                "电话:18512516758\n"+
                "或者联系邮箱: boniu@itcast.cn\n" +
                "座机电话:01036517895，010-98951256\n"+
                "邮箱:cai@czm.cn，\n"+ "邮箱2:dlei0009@163.com，\n" +
                "热线电话:400-618-9090，400-618-4000，\n" +
                "4006184000，4006189090\n";
        System.out.println("-------------- 只爬取邮箱用户名");
        matcherEmailName(data);
        System.out.println();
        matcherPartInfo();
    }

    public static void matcherEmailName(String data) {
        // 把匹配规则使用 () 来分组，这样爬取使可按组取值
        Pattern pattern = Pattern.compile("(\\w{2,30})(@\\w{2,20})(\\.\\w{2,20}){1,2}");
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            // 传入参数1，表示只爬取内容里的第一组 () 内容。
            String info = matcher.group(1);
            System.out.println("---爬取信息 = " + info);
        }
    }

    public static void matcherPartInfo() {
        String data = "欢迎李明光临！欢迎ABCDE光临！欢迎123光临！欢迎wangli光临！欢迎zhaoming光临！";

//        Pattern pattern = Pattern.compile("欢迎(.+)光临！"); // 贪婪匹配！最大范围的去匹配。
        Pattern pattern = Pattern.compile("欢迎(.+?)光临！");  // 非贪婪匹配
        Matcher matcher = pattern.matcher(data);
        while (matcher.find()) {
            // 传入参数1，表示只爬取内容里的第一组 () 内容。
            String info = matcher.group(1);
            System.out.println("---爬取信息 = " + info);
        }
    }
}
