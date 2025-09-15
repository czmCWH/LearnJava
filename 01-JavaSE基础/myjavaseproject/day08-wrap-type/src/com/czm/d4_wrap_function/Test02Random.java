package com.czm.d4_wrap_function;

import java.util.Random;

public class Test02Random {
    public static void main(String[] args) {
        /*
         1、Math 生成随机数
         */
        // 生成 [0.0, 1.0) 范围的随机数
        System.out.println("Math 类生成随机数：" + Math.random());
        int i1 = (int) (Math.random() * 100);
        System.out.println("--- Math 类生生成 [0, 99] 内的整数 = " + i1);
        int i2 = (int) (Math.random() * 90) + 10;   // 先生成 [0, 89] 的整数，再加 10，就是 [10, 99]
        System.out.println("--- Math 类生成 [10, 99] 内的整数 = " + i2);

        /*
         2、java.util.Random 可以更方便的生成各种随机数
         Random 类生成随机数 比 Math 更方便
         */
        Random r = new Random();
        System.out.println("--- 生成 Boolean 类型的随机数: " + r.nextBoolean());
        System.out.println("--- 生成 Int 类型取值范围内的随机数：: " + r.nextInt());
        System.out.println("--- 生成 Long 类型取值范围内的随机数：: " + r.nextLong());
        System.out.println("--- 生成 Float 类型取值范围内的随机数：: " + r.nextFloat());

        System.out.println("------ Random 类生成指定范围内的随机数：");
        System.out.println("--- Random 生成 [0, 99] 范围内的整数：" + r.nextInt(100));
        System.out.println("--- Random 生成 [10, 99] 范围内的整数：" + r.nextInt(90) + 10);  // 先生成 [0, 89] 的整数，再加 10，就是 [10, 99]

        /*
         3、案例：随机生成4位的大写字母验证码
         */
        System.out.println("--- A = " + ((int) 'A'));
        System.out.println("--- Z = " + (+'Z'));    // ⚠️，字符类型 转换为 int 类型，直接在前面添加 + 即可。
        // A~Z 的取值范围为：[65, 90] <---- [0, 25] + 65
        for (int i = 0; i < 4; i++) {
            int c = 'A' +  new Random().nextInt(26);
            System.out.println("--- char = " + ((char) c));
        }

    }
}
