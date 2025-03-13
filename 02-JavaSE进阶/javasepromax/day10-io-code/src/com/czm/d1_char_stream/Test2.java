package com.czm.d1_char_stream;

import java.io.FileReader;
import java.io.Reader;

public class Test2 {
    public static void main(String[] args) {
        /*
         1、FileReader 每次读取多个字符
             public int read(char[] cbuf)
                每次用一个字符数组去读取数据，返回字符数组读取了多少个字符，如果发现没有数据可读会返回-1
         */
        try (
                // 1、创建字符输入流管道与源文件接通
                Reader fr = new FileReader("day10-io-code/src/czm01.txt");

                ) {
            // 2、定义每次读取几个字符
            char[] buf = new char[3];
            int leng;   // 记录每次实际读取了多少个字符
            while ((leng = fr.read(buf)) != -1) {
                String str = new String(buf, 0, leng);
                System.out.print(str);
            }

            // FileReader 读取方式可以避免读取汉字乱码
            // read(char[] cbuf) 读取方式可以，是截止目前最佳的方式

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
