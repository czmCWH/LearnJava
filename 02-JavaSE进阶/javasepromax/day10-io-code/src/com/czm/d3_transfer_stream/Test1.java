package com.czm.d3_transfer_stream;

import java.io.*;

public class Test1 {
    /*
     1、读取文件时乱码问题
        如果 代码编码 和 被读取的文本文件的编码 是一致的，使用 字符流 读取文本文件时 不会出现乱码!
        如果 代码编码 和 被读取的文本文件的编码 是不一致的，使用 字符流 读取文本文件时就会 出现乱码!

     2、InputStreamReader 字符输入转换流
        作用：解决不同编码时，字符流读取文本内容乱码的问题。
        解决思路：先获取文件的原始字节流，再将其按真实的字符集编码转成字符输入流，这样字符输入流中的字符就不乱码了

     */
    public static void main(String[] args) {
        // GBK 编码的文件
        String filePath = "day10-io-code/src/transfer.txt";

        System.out.println("--- 1、直接读取 GBK 编码格式的文件，会乱码");

        try (
                Reader r = new FileReader(filePath);
                BufferedReader br = new BufferedReader(r);
             ){

            String line;    // 记录每次读取的一行数据
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("--- 2、采用字符输入转码流，才不会乱码：");
        try (
                // 1、得到 GBK 文件的原始字节输入流
                InputStream is = new FileInputStream(filePath);
                // 2、通过字符输入转换流把原始字节流按照指定编码转换成字符输入流
                Reader r = new InputStreamReader(is, "GBK");    // 重点：抓码
                // 3、把字符输入流包装成高级的缓冲字符输入流
                BufferedReader br = new BufferedReader(r);

                ) {

            // 4、按照行读取
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
