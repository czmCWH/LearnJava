package com.czm.d2_buffer_stream;

import java.io.*;

public class Test1 {
    /*
     1、IO流的体系
        原始流/低级流：FileInputStream、FileOutputStream
                    FileReader、FileWriter
        包装流/处理流：BufferedInputStream、BufferedOutputStream    字节缓冲流
                    BufferedReader、BufferedWriter   字符缓冲流

        缓冲流的作用：对原始流进行包装，以提高原始流读写数据的性能

     2、字节缓冲流
        作用：提高字节流读写数据的性能
        原理：
            字节缓冲输入流自带了 8KB 内存缓冲池；字节缓冲输出流也自带了 8KB 内存缓冲池。
            对于输入流，每次最多可以加载 8KB 的数据到内存；对于输出流，当 8KB 内存缓冲池满了就会同步到磁盘，减少了内存到系统的调用，提高了性能。
     */
    public static void main(String[] args) {
        try (
                // ⚠️：这里只能放置资源对象，用完后会自动调用资源的 close 方法关闭资源!!

                // 1、创建字节输入流管道与源文件接通
                InputStream is = new FileInputStream("/Users/chen/Downloads/111图片/IMG_0769.JPG");
                // 把低级的字节输入流包装成一个高级的缓冲字节输入流，从而提高读数据的性能
                InputStream bis = new BufferedInputStream(is);

                // 2、创建一个字节输出流管道与目标文件接通
                OutputStream os = new FileOutputStream("day10-io-code/src/img01.jpg");  // ⚠️：目标文件后缀名必须与被复制文件一致
                // 把低级的字节输出流包装成一个高级的缓冲字节输出流，从而提高写数据的性能
                OutputStream bos = new BufferedOutputStream(os);
        ) {
            // 3、准备一个字节数组
            byte[] buffer = new byte[1024];     // 每次复制1kb

            // 4、开始复制
            int length;
            while ((length = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, length);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
