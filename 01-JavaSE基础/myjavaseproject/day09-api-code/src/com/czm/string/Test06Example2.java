package com.czm.string;

import java.util.Random;

public class Test06Example2 {
    /*
     案例2: 使用 String 开发验证码
        实现随机产生验证码，验证码的每位可能是数字、大写字母、小写字母
     */
    public static void main(String[] args) {
        System.out.println(createCode(4));
        System.out.println(createCode(6));
    }
    public static String createCode(int n) {
        String code = "";
        String data = "abCdFGHIJKLMNOPQRSTUVWXYZ01234evwxyzABcDfghijklmnopgrstuE56789";
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            int index = r.nextInt(data.length());
            char c = data.charAt(index);
            code += c;
        }
        return code;
    }
}
