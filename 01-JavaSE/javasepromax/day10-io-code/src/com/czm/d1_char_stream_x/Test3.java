package com.czm.d1_char_stream_x;

import java.io.FileWriter;
import java.io.Writer;

public class Test3 {
    /*
     1、FileWriter 文件字符输出流
        以内存为基准，把内存中的数据以字符的形式写出到文件中去。

        注意：字符输出流写出数据后，必须刷新流，或者关闭流，写出去的数据才能生效

     */
    public static void main(String[] args) {
        try (
                // 1、创建字节输出流 管道与源文件对象接通
//                Writer fw = new FileWriter("day10-io-code/src/write.txt");  // 每次写入时会覆盖
                Writer fw = new FileWriter("day10-io-code/src/write.txt", true);  // 没次写入时追加

        ) {
            // 2、开始写入字符到文件

            // a、写一个字符
            fw.write('A');
            fw.write(97);
            fw.write('😺');

            fw.write("\r\n");   // 换行，兼容更多平台

            // b、写一个字符串
            fw.write("我爱😍你中国😍😍");
            fw.write("\r\n");

            // c、写入字符串的一部分
            fw.write("你好，天天开心✌️",6, 1);
            fw.write("\r\n");

            // d、写一个字符数组
            char[] chars = "abc🏃哈哈哈哈".toCharArray();
            fw.write(chars);
            fw.write("\r\n");

            // e、把字符串的一部分写入文件
            fw.write(chars, 3, 1);
            fw.write("\r\n");

            // 3、刷新
            // ⚠️⚠️⚠️ 必须刷新，因为 FileWriter 写入时有缓冲区，如果不刷新会写不完整
            fw.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
