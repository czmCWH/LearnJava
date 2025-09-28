package com.czm.d3_Streams;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Test01Byte {
    /*
     1、字节流（Byte Streams）
      字节流的特点：
        ⚠️ 一次只读写一个字节；
        最终都继承自 InputStream、OutputStream;

      常用的字节流有：FileInputStream（读取数据到程序）、FileOutputStream（写数据到文件）

     2、

     */

    public static void main(String[] args) throws Exception {

        System.out.println("--- 👉 1、OutputStream 写入字节流到文件");

        // 如果 /Users/chen/Desktop/abc/22.txt 路径\文件不存在，会自动创建
        OutputStream fos = new FileOutputStream("/Users/chen/Desktop/abc/22.txt");
        // 将 2个字节 77、66 写入到 22.txt 文件
        fos.write(77);
        fos.write(66);
        fos.write("好好学习".getBytes(StandardCharsets.UTF_8));
        fos.close();

        // 把 "好好学习" 字节流以追加写入的方式到文件
        OutputStream fos2 = new FileOutputStream("/Users/chen/Desktop/22.txt", true);
        fos2.write("好好学习".getBytes());
        fos2.close();

        System.out.println("--- 👉 2、InputStream 读取文件字节流到程序");
        InputStream fis = new FileInputStream("/Users/chen/Desktop/abc/22.txt");
        // 1、read() 每次向前读取一个字节
//        int byte1 = fis.read();
//        int byte2 = fis.read();
//        System.out.printf("byte1 = %d, byte2 = %d%n ", byte1, byte2);   // byte1 = 77, byte2 = 66

        // 2、int read(byte b[])，最多读取数组长度的字节到数组中。返回值为真正读取的有效字节长度，即字节数不够时填不满，用0来填充。
        byte[] bytes = new byte[100];
        int len = fis.read(bytes);
        System.out.printf("读取了 len = %d 个字节到数组中 %n", len);  // 读取了 len = 14 个字节到数组中
        String readStr = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("--- readStr = " + readStr + "***");
        String readStr2 = new String(bytes, 0, len, StandardCharsets.UTF_8);   // 指定需要解析字节的长度
        System.out.println("--- readStr2 = " + readStr2 + "***");

        fis.close();

    }
}
