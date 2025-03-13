package com.czm.d2_buffer_stream;

import java.io.File;
import java.io.*;

public class Test5 {

    // 目标：原始流 和 缓冲流的性能分析。

    public static final String SRC_PATH = "/Users/CZM/学习视频/day10-IO(二)/14、复制文件夹.mp4";
    public static final String DEST_PATH = "/Users/CZM/学习视频/video/";

    public static void main(String[] args) {
        File dir = new File(DEST_PATH);
        if (!dir.exists()) {
            dir.mkdirs();
        }
//        copyTest1();  // 复制速度非常的慢，禁止使用！
        copyTest2();    // 0.996s，相对较慢，速度还可以
//        copyTest3();    // 2.316s，特别慢，不推荐！
        copyTest4();    // 0.91s，复制速度最快，推荐！

        /*
         总结：使用 原始流（FileInputStream、FileOutputStream）复制文件就一定比 缓冲流(BufferedInputStream、BufferedOutputStream) 速度慢吗？
            不一定，如果通过 字节数组的方式去读取数据时，通常我们设置每次读取的字节数为：byte[] buffer = new byte[1024]，即 1024个字节。如果提高每次读取字节数，原始流和缓冲流的读写速度会提高，二者相差不大。
            这是因为每次 读取的字节数增大，这样与系统的交互也就减少了，复制的效率也就提高了。
            一般每次转移的字节数达到 byte[1024*32] 就差不多，因为一次性读取的字节多，那么转的也多，也是需要耗时的。
         */
    }

    // 1、使用低级的 字节流 按照 一个一个字节 的形式复制文件。
    public static void copyTest1() {
        long start = System.currentTimeMillis();
        try (
                // ⚠️：这里只能放置资源对象，用完后会自动调用资源的 close 方法关闭资源!!

                // 1、创建字节输入流管道与源文件接通
                InputStream is = new FileInputStream(SRC_PATH);
                // 2、创建一个字节输出流管道与目标文件接通
                OutputStream os = new FileOutputStream(new File(DEST_PATH + "test01.mp4"));  // ⚠️：目标文件后缀名必须与被复制文件一致
        ) {

            // 3、每次读取1个字节
            int b;
            while ((b = is.read()) != -1) {
                os.write(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("1、使用低级的 字节流 按照 一个一个字节 的形式复制文件耗时 = " + (end - start)/1000.0 + "s");
    }

    // 2、使用低级的 字节流 按照 字节数组 的形式复制文件。
    public static void copyTest2() {
        long start = System.currentTimeMillis();
        try (
                // 1、创建字节输入流管道与源文件接通
                InputStream is = new FileInputStream(SRC_PATH);
                // 2、创建一个字节输出流管道与目标文件接通
                OutputStream os = new FileOutputStream(DEST_PATH + "test02.mp4");
        ) {

            // 3、准备一个字节数组
            byte[] buffer = new byte[1024 * 32];     // 每次读取1kb，1024个字符

            // 4、开始复制
            int length;     // 每次真实读取了几个字符
            while ((length = is.read(buffer)) != -1) {
                os.write(buffer, 0, length);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("2、使用低级的 字节流 按照 字节数组 的形式复制文件 = " + (end - start)/1000.0 + "s");
    }

    // 3、使用高级的 缓冲字节流 按照 一个一个字节 的形式复制文件。
    public static void copyTest3() {
        long start = System.currentTimeMillis();
        try (
                // 1、创建字节输入流管道与源文件接通
                InputStream is = new FileInputStream(SRC_PATH);
                InputStream bis = new BufferedInputStream(is);
                // 2、创建一个字节输出流管道与目标文件接通
                OutputStream os = new FileOutputStream(DEST_PATH + "test03.mp4");
                OutputStream bos = new BufferedOutputStream(os);
        ) {

            // 3、准备一个字节数组
            int b;
            while ((b = bis.read()) != -1) {
                bos.write(b);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("3、使用高级的 缓冲字节流 按照 一个一个字节 的形式复制文件 = " + (end - start)/1000.0 + "s");

    }

    // 4、使用高级的 缓冲字节流 按照 字节数组 的形式复制文件。
    public static void copyTest4() {
        long start = System.currentTimeMillis();
        try (
                // 1、创建字节输入流管道与源文件接通
                InputStream is = new FileInputStream(SRC_PATH);
                InputStream bis = new BufferedInputStream(is);
                // 2、创建一个字节输出流管道与目标文件接通
                OutputStream os = new FileOutputStream(DEST_PATH + "test04.mp4");
                OutputStream bos = new BufferedOutputStream(os);
        ) {

            // 3、准备一个字节数组
            byte[] buffer = new byte[1024 * 32];     // 每次读取1kb，1024个字符

            // 4、开始复制
            int length;     // 每次真实读取了几个字符
            while ((length = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, length);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();
        System.out.println("4、使用高级的 缓冲字节流 按照 字节数组 的形式复制文件 = " + (end - start)/1000.0 + "s");
    }

}
