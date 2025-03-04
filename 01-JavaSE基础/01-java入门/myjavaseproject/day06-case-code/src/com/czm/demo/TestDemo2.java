package com.czm.demo;

import java.util.Random;

public class TestDemo2 {
    /*
     1、案例
        开发一个程序，可以生成指定位数的验证码，每位可以是数字、大小写字母
     2、本案例知识点：

     */
    public static void main(String[] args) {
        System.out.println("--- 4位验证码 = " + randomCode(4));
        System.out.println("--- 5位验证码 = " + randomCode(5));
    }

    public static String randomCode(int length) {
        String code = "";

        Random r = new Random();
        for (int i = 0; i < length; i++) {
            // 1、先随机出类型：数字、大小写字母
            int type = r.nextInt(3);
            // 2、根据类型，随机出对应类型的数
            switch (type) {
                case 0: // 随机数字
                    int num = r.nextInt(10);
                    code += num;
                    break;
                case 1: // 随机大写字母：A~Z， 65 -> 65+25
                    char c1 = (char) (r.nextInt(26) + 65);
                    code += c1;
                    break;
                case 2: // 随机小写字母：a~z，97 -> 97+25
                    char c2 = (char) (r.nextInt(26) + 97);
                    code += c2;
                    break;
            }

        }
        return code;
    }
}
