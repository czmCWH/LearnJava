package com.czm.d1_char_stream_x;

import java.io.FileReader;
import java.io.Reader;

public class Test1 {
    /*
     1、FileReader(文件字符输入流)
        作用：以内存为基准，可以把文件中的数据以字符的形式读入到内存中去。

        public int read() 每次读取一个字符返回，如果发现没有数据可读会返回-1

     */
    public static void main(String[] args) throws Exception {
        // 1、创建字符输入流管道与源文件接通
        Reader fr = new FileReader("day10-io-code/src/czm01.txt");

        // 2、public int read() 每次读取一个字符返回，如果发现没有数据可读会返回-1
        // 注意：文件里面包含特殊字符时，在终端里打印会乱码
//        int c = fr.read();
//        System.out.println((char) c);
//
//        int c1 = fr.read();
//        System.out.println((char) c1);
//
//        int c2 = fr.read();
//        System.out.println((char) c2);

        // 使用循环改进
        int code;   // 接收读取的字符编号
        while ((code = fr.read()) != -1) {
            System.out.print((char) code);
        }
        fr.close();   // 关闭输入流

        /*
         FileReader 解决读取汉字字符乱码的问题
            public int read() 一个一个的读取，性能较差！垃圾方式
        */

    }
}
