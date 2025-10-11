package com.czm.d1_byte_stream_x;

import java.io.FileInputStream;

public class Test1 {
    /*
     1、IO 流
        用于读写数据的(可以读写文件，或网络中的数据...)。
        I 指 Input，称为输入流：负责把数据读到内存中去。
        O 指 Output，称为输出流：负责写数据出去。

        IO流的分类：
            a、按流出的方向分为：
                输入流、输出流；
            b、按流中数据最小单位分为：
                字节流：适合操作所有类型的文件，如：音频、视频、图片、文本的复制、转移等。
                字符流：只适合操作纯文本文件，如：读写txt、java等文件。

     2、IO流的体系，java.io包下：
        IO体系
            字节流：适合复制文件等，不适合读写文本文件
                InputStream，字节输入流
                OutputStream，字节输出流
            字符流：适合读写文本文件内容
                Reader，字符输入流
                Writer，字符输出流

        以上IO体系中它们都是抽象类，对应的实现类为：
            FileInputStream、FileOutputStream
            FileReader、FileWriter

     3、FileInputStream（文件字节输入流）
        作用:以内存为基准，可以把磁盘文件中的数据以字节的形式读入到内存中去。

        FileInputStream 读取字节 方式 1：
            public int read()：每次读取一个字节返回，如果发现没有数据可读会返回-1.

     */
    public static void main(String[] args) throws Exception {

        // 1、创建字节输入流管道与源文件接通
        // 完整语法
//        FileInputStream fm1 = new FileInputStream(new File("day09-io-code/src/czm01.txt"));
        // 简洁写法
        FileInputStream fm1 = new FileInputStream("day09-io-code/src/czm01.txt");

        // 2、每次读取一个字节
//        int b1 = fm1.read();  // ⚠️：每次读取一个字节返回，如果发现没有数据可读会返回-1.
//        System.out.println(b1);
//        System.out.println((char)b1);
//
//        int b2 = fm1.read();
//        System.out.println((char)b2);
//
//        int b3 = fm1.read();
//        System.out.println((char)b3);

        // 循环读取
        int b;  // 用于记录每次读取的一个字节
        while ((b = fm1.read()) != -1) {
            System.out.println((char)b);
        }

        fm1.close(); // 注意：操作完，关闭管道

        // 此读取方式垃圾，因为：
        // a、一个一个字节读取性能差，因为操作系统从硬盘读取数据到内存时，需要占用IO总线。
        // b、无法避免读取汉字输出乱码的问题，会截断汉字的字节，因为一个汉字占3个字节。

    }
}
