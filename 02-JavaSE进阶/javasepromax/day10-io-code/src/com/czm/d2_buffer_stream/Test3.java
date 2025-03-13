package com.czm.d2_buffer_stream;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.Writer;

public class Test3 {
    /*
     1、BufferedWriter（字符缓冲输出）
        作用：在内存中它自带了 8K 个 的字符缓冲池，可以提高字符输出流写字符数据的性能。

     2、
     */
    public static void main(String[] args) {
        try (
                // 1、创建字节输出流 管道与源文件对象接通
//                Writer fw = new FileWriter("day10-io-code/src/write.txt");  // 每次写入时会覆盖
                Writer fw = new FileWriter("day10-io-code/src/write.txt", true);  // 没次写入时追加
                // 把低级的字符输出流包装成一个高级的缓冲字符输出流管道
                BufferedWriter bw = new BufferedWriter(fw);

        ) {
            // 2、开始写入字符到文件

            // a、写一个字符
            bw.write('A');
            bw.write(97);
            bw.write('😺');

//            bw.write("\r\n");   // 换行，兼容更多平台
            bw.newLine();   // 注意：BufferedWriter 封装了换行功能

            // b、写一个字符串
            bw.write("我爱😍你中国😍😍");
//            bw.write("\r\n");
            bw.newLine();

            // c、写入字符串的一部分
            bw.write("你好，天天开心✌️",6, 1);
//            bw.write("\r\n");
            bw.newLine();

            // d、写一个字符数组
            char[] chars = "abc🏃哈哈哈哈".toCharArray();
            bw.write(chars);
//            bw.write("\r\n");
            bw.newLine();

            // e、把字符串的一部分写入文件
            bw.write(chars, 3, 1);
//            bw.write("\r\n");
            bw.newLine();

            // 3、刷新
            // ⚠️⚠️⚠️ 必须刷新，因为 FileWriter 写入时有缓冲区，如果不刷新会写不完整
            bw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
