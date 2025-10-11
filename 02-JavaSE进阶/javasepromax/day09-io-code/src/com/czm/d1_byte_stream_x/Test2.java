package com.czm.d1_byte_stream_x;

import java.io.FileInputStream;

public class Test2 {

    /*
     1、FileInputStream（文件字节输入流）读取字 节方式 2
         public int read(byte[] b) ：每次用一个字节数组去读取数据，返回字节数组读取了多少个字节，如果发现没有数据可读会返回-1
     */
    public static void main(String[] args) throws Exception {

        // 1、创建字节输入流管道与源文件接通
        FileInputStream is = new FileInputStream("day09-io-code/src/czm02.txt");

        // 2、每次用一个字节数组去读取数据，返回字节数组读取了多少个字节，如果发现没有数据可读会返回-1
//        byte[] buf = new byte[3];   // 每次读取3个字节
//        int len = is.read(buf);
//        System.out.println("读取了几个字节 = " + len);
//        // 读取了几个 len，就拿出几个
//        System.out.println("读取的内容 = " + new String(buf, 0, len));
//
//        int len2 = fm1.read(buf);
//        System.out.println("读取了几个字节 = " + len2);
//        System.out.println("读取的内容 = " + new String(buf, 0, len2));


        byte[] buf = new byte[3];   // 每次读取3个字节
        int len;    // 记录每次读取了结果字节
        while ((len = is.read(buf)) != -1) {
            // 读取了几个 len，就拿出几个转换为 String
            String str = new String(buf, 0, len);
            System.out.println(str);
        }

        is.close(); // 注意：操作完，关闭管道

        /*
         * 注意：
         *  性能较好，但是任然无法避免读取汉字乱码的问题
         *
         * 应用：
         *  不会用来读取汉字输出，常用于读字节复制、写入。
         */

    }
}
