package com.czm.d2_buffer_stream;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;

public class Test2 {
    /*
     1、BufferedReader 字符缓冲输入流
        作用：在内存中它自带 8K个（即：8192个）的字符缓冲池，可以提高字符输入流读取字符数据的性能。

     */
    public static void main(String[] args) {
        try (
                // 1、创建字符输入流管道与源文件接通
                Reader fr = new FileReader("day10-io-code/src/czm02.txt");
                // 把低级的字符输入流包装成一个高级的缓冲字符输入流
                BufferedReader br = new BufferedReader(fr);

        ) {
            // 2、定义一个字符数组每次读取几个字符
//            char[] buf = new char[1024];
//            int leng;   // 记录每次实际读取了多少个字符
//            while ((leng = br.read(buf)) != -1) {
//                String str = new String(buf, 0, leng);
//                System.out.print(str);
//            }

            // 3、注意：BufferedReader 缓存字符输入流多了一个按照行读取内容的功能
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
