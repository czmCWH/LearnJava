package com.czm.d4_charset;

import java.io.IOException;
import java.util.Arrays;

public class Test1 {

    // 字符集的编解码

    public static void main(String[] args) throws IOException {
        String info = "学好java，好牛逼";

        System.out.println("---编码");
        // 1、使用平台的默认字符集(UTF-8) 将该 字符串编码为一系列字节
        byte[] bytes = info.getBytes(); // 默认平台编码，采用 UTF-8
        System.out.println(Arrays.toString(bytes));

        // 2、使用指定的字符集(GBK)将该 string编码为一系列字节
        byte[] bytes2 = info.getBytes("GBK");
        System.out.println(Arrays.toString(bytes2));

        System.out.println("---解码，用什么字符集编码，就用什么字符集解码");
        // 通过使用平台的默认字符集解码指定的字节数组来构造新的 string
        String str1 = new String(bytes);
        System.out.println(str1);
        // 通过指定的字符集(GBK)解码指定的字节数组来构造新的 string
        String str2 = new String(bytes2, "GBK");
        System.out.println(str2);

    }
}
