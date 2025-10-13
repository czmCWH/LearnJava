package com.czm.d1_byte_stream_x;

import java.io.FileInputStream;
import java.io.InputStream;

public class Test3 {
    /*
     1、FileInputStream（文件字节输入流）读取字节方式3
         public byte[] readAllBytes()，JDK9，读取全部内容！

     */
    public static void main(String[] args) throws Exception {

        // 1、创建字节输入流管道与源文件接通
        InputStream is = new FileInputStream("day09-io-code/src/czm02.txt");

        // 一次性读取，方法1
//        File f = new File("day09-io-code/src/czm02.txt");
//        // 定义字节数组，表示每次读取数组大小的字节数。
//        // 接收参数为 int 类型，避免一次性读取的字节数太多，导致把内存占满
//        byte[] buffer = new byte[(int)f.length()];
//
//        // 2、读取全部字节
//        int len = is.read(buffer);
//        System.out.println("读取了的字节数 = " + len);
//        System.out.println("读取的内容 = "+ new String(buffer,0,len));

        // 一次性读取，方法2
        byte[] buf = is.readAllBytes();    // 读取大文件会报：内存异常
        System.out.println("读取的字节数 = " + buf.length);
        System.out.println("读取的全部内容 = " + new String(buf));

        is.close(); // 注意：操作完，关闭管道

    }
}
