package com.czm.d1_string;

import java.util.Scanner;

public class Test05Example1 {
    /*
     1、案例：用户登录
        正确的账号密码：czmJava/123456，用户有3次机会。

     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("开始第" + (i+1) + "次登录");
            // 1、等待用户输入
            System.out.println("请输入登录名称：");
            String userName = sc.next();
            System.out.println("请输入登录密码：");
            String password = sc.next();

            // 2、开始执行登录
            String result = login(userName, password);
            if (result.equals("success")) {
                System.out.println("恭喜你，登录成功！");
                break;
            } else {
                System.out.println(result);
            }
        }
    }

    public static String login(String loginName, String password) {
        // 1、拿到正确的登录名和密码
        String okLoginName = "czmJava";
        String okPassword = "123456";
        if (okLoginName.equals(loginName)) {
            if (okPassword.equals(password)) {
                return "success";
            } else {
                return "登录密码不正确，请检查！";
            }
        } else {
            return "登录用户名不正确，请检查！";
        }

    }
}
