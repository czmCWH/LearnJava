package com.czm.d1_byte_stream_x;

import java.io.FileOutputStream;
import java.io.OutputStream;

public class Test4 {
    /*
     1、FileOutputStream 文本字节输出流
        作用：以内存为基准，把内存中的数据以字节的形式写出到文件中去
     2、
     */
    public static void main(String[] args) throws Exception {
        // 1、创建一个文件字节输出流管道与目标文件接通
        // 覆盖管道，每次写入都会覆盖所有
//        OutputStream os = new FileOutputStream("day09-io-code/src/out01.txt");
        // 追加管道，每次写入不会覆盖
        OutputStream os = new FileOutputStream("day09-io-code/src/out01.txt", true);

        // 2、开始写字节数据出去

        // public abstract void write(int b)，每次只写一个字节到文件中
        os.write('a');
        os.write(97);
//        os.write('您');    // 会乱码：aa�，因为一个汉字占3个字节，而此 write 方法每次只写入1个字节。

        os.write("\r\n".getBytes());    // \r\n 表示换行，可以兼容更多系统平台。

        // public void write(byte[] b) ，写一个字节数组到文件中
        byte[] buffer = "123我爱你中国6666".getBytes();
        os.write(buffer);

        os.write("\r\n".getBytes());

        // public void write(byte[] b, int off, int len) ，写一个字节数组的一部分出去。
        os.write(buffer, 3, 15);    // 只写入：我爱你中国

        // ⚠️注意：io流管道属于系统资源，会占用内存和相应的I0资源。用完必须关闭管道，释放占用的系统资源。
//        os.flush(); // 刷新缓存中的数据到磁盘文件中去。
        os.close(); // 关闭包含刷新！

    }
}
