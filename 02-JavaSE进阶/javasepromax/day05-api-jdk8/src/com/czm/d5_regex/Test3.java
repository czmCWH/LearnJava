package com.czm.d5_regex;

import java.util.Scanner;

public class Test3 {
    /*
     1、案例
        a、校验邮箱地址
        b、校验手机号
     2、
     */
    public static void main(String[] args) {
//        checkEmail();
        checkPhone();
    }

    // 使用正则表达式校验邮箱
    public static void checkEmail() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入您的邮箱地址：");
            String email = sc.next();
            // dlei00090@163.com
            // 5423253@qq.com
            // xulei2@czm.com.cn
            // \\.表示 . 点字符
            if (email.matches("\\w{2,}@\\w{2,20}(\\.\\w{2,20}){1,2}")) {
                System.out.println("邮箱合法，录入成功！");
                break;
            } else {
                System.out.println("您输入的邮箱错误，请重新输入");
            }
        }
    }

    // 校验手机号码
    public static void checkPhone() {
        while (true) {
            Scanner sc = new Scanner(System.in);
            System.out.println("请输入您的手机号：");
            String email = sc.next();
            if (email.matches("1[3-9]\\d{9}")) {
                System.out.println("手机号合法，录入成功！");
                break;
            } else {
                System.out.println("您输入的手机号错误，请重新输入");
            }
        }
    }
}
