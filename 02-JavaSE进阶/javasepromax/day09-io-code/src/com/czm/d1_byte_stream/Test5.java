package com.czm.d1_byte_stream;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Test5 {
    /*
     1、字节流非常适合做一切文件的复制操作
        任何文件的底层都是字节，字节流做复制，是一字不漏的转移完全部字节，只要复制后的文件格式一致就没问题!

     2、
     */
    public static void main(String[] args) {

        try {
            // 1、创建字节输入流管道与源文件接通
            InputStream is = new FileInputStream("/Users/chen/Downloads/111图片/IMG_0769.JPG");

            // 2、创建一个字节输出流管道与目标文件接通
            OutputStream os = new FileOutputStream("day09-io-code/src/img01.jpg");  // ⚠️：目标文件后缀名必须与被复制文件一致

            // 3、准备一个字节数组
            byte[] buffer = new byte[1024];     // 每次复制1kb

            // 4、开始复制
            int length;
            while ((length = is.read(buffer)) != -1) {
                os.write(buffer, 0, length);
            }

            // 5、释放管道
            os.close();
            is.close();
            System.out.println("---文件复制完毕");

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
