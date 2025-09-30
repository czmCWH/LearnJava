package com.czm.d2_demo;

public class TestDemo4 {
    /*
     1、案例：数字加密
        有一个4位数密码，需要加密，加密规则：对密码中每位数先都加5，再对10求余，最后倒序，加密成功。
     2、本案例知识点：

     */
    public static void main(String[] args) {
        System.out.println("--- 加密后的数字 = " + encode(8346));     // 1983
        System.out.println("--- 解密后的数字 = " + encode(1983));     // 8346
    }

    public static String encode(int number) {
        // 1、拆分每位到数组中
        int[] data = new int[4];
        data[0] = number / 1000;
        data[1] = number / 100 % 10;
        data[2] = number / 10 % 10;
        data[3] = number % 10;

        // 2、遍历数组中每一位依次加密
        for (int i = 0; i < data.length; i++) {
            data[i] = (data[i] + 5) % 10;
        }

        // 3、反转数组
        for (int i = 0, j = data.length - 1; i < j; i++, j--) {
            int temp = data[i];
            data[i] = data[j];
            data[j] = temp;
        }
        // 4、定义字符串拼接数组内容
        String result = "";
        for (int i = 0; i < data.length; i++) {
            result += data[i];
        }
        return result;
    }

}
