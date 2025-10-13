package com.czm.d2_try_finally_x;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Test3 {
    /*
     简化 try-catch-finally 中释放资源的方式

     1、try-with-resource
        JDK7 开始提供了更简单的资源释放方案：try-with-resource
        该资源使用完毕后，会自动调用其 close() 方法，完成对资源的释放!

        语法格式如下：
            try (自定义资源1, 自定义资源2) {
            } catch(e) {
            }

        try-catch中的 资源对象是指什么？
            注意：资源一般指的是最终实现了 AutoCloseable 接口
     */

    public static void main(String[] args) {
        try (
                // ⚠️：这里只能放置资源对象，用完后会自动调用资源的 close 方法关闭资源!!

                // 1、创建字节输入流管道与源文件接通
                InputStream is = new FileInputStream("/Users/chen/Downloads/111图片/IMG_0769.JPG");
                // 2、创建一个字节输出流管道与目标文件接通
                OutputStream os = new FileOutputStream("day09-io-code/src/img01.jpg");  // ⚠️：目标文件后缀名必须与被复制文件一致
        ) {

            // 3、准备一个字节数组
            byte[] buffer = new byte[1024];     // 每次复制1kb

            // 4、开始复制
            int length;
            while ((length = is.read(buffer)) != -1) {
                os.write(buffer, 0, length);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
