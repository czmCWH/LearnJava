package com.czm.d2_try_finally;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Test2 {

    // 使用 try-catch-finally 方式释放资源

    public static void main(String[] args) {
        InputStream is = null;
        OutputStream os = null;
        try {
            // 1、创建字节输入流管道与源文件接通
            is = new FileInputStream("/Users/chen/Downloads/111图片/IMG_0769.JPG");
            // 2、创建一个字节输出流管道与目标文件接通
            os = new FileOutputStream("day09-io-code/src/img01.jpg");  // ⚠️：目标文件后缀名必须与被复制文件一致

            // 3、准备一个字节数组
            byte[] buffer = new byte[1024];     // 每次复制1kb

            // 4、开始复制
            int length;
            while ((length = is.read(buffer)) != -1) {
                os.write(buffer, 0, length);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            // 如果 try {} 里面抛出异常时，未执行到关闭资源的位置，将直接进入到 catch{}中，这会导致 资源无法释放
            // 因此通常在 finally 中释放资源。
            // finally 中释放资源，代码繁琐！！！

            // 5、释放管道
            try {
                if (os != null) {
                    os.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("---文件复制完毕");
        }
    }
}
