package com.czm.d3_transfer_stream;

import java.io.*;

public class Test2 {
    /*
    控制写入到文件中的字符采用指定的字符集编码，该如何处理？

     1、直接调用 String 的 getBytes 方法
        String data = "好好学习java";
        byte[] bytes = data.getBytes("GBK");

     2、OutputStreamWriter 字符输出转换流
        作用：可以控制写出去的字符使用什么字符集编码。
        解决思路：获取字节输出流，再按照指定的字符集编码将其转换成字符输出流，以后写出去的字符就会用该字符集编码了

     */
    public static void main(String[] args) {
        try (
                // 1、创建一个文件字节输出流接通目标文件
             OutputStream os = new FileOutputStream("day10-io-code/src/transfer_out.txt");
             // 指定鞋出去的编码是 GBK
             Writer w = new OutputStreamWriter(os, "GBK");
             // 2、把字节输出流包装成缓冲输出流
             BufferedWriter bw = new BufferedWriter(w);
             ){
            bw.write("清明时节雨纷纷，路上行人欲断魂");
            bw.newLine();
            bw.write("借问酒家何处有，牧童遥指杏花村");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
