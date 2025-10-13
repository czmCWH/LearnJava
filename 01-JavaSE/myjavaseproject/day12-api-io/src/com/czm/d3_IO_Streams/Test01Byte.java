package com.czm.d3_IO_Streams;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Test01Byte {
    /*
     1、字节流（Byte Streams）
      字节流的特点：
        ⚠️ 一次只读写一个字节；
        最终都继承自 InputStream、OutputStream;

      常用的字节流有：FileInputStream（读取数据到程序）、FileOutputStream（写数据到文件）

     2、FileInputStream、FileOutputStream 的缺点
      一个一个字节读取性能差，因为操作系统从硬盘读取数据到内存时，需要占用IO总线。
      无法避免读取汉字输出乱码的问题，会截断汉字的字节，因为一个汉字占3个字节。

     */

    public static void main(String[] args) throws Exception {

        System.out.println("--- 👉 1、OutputStream 写入字节流到文件");

        // 如果 /Users/chen/Desktop/abc/22.txt 路径\文件不存在，会自动创建
        // 完整写法：
//        OutputStream fos = new FileOutputStream(new File("/Users/chen/Desktop/abc/22.txt"));
        // 简写：
        OutputStream fos = new FileOutputStream("day12-api-io/src/11.txt");
        // 将 2个字节 65、66 写入到 22.txt 文件
        fos.write(65);
        fos.write(66);
        fos.write('您');     // 会乱码：aa�，因为一个汉字占3个字节，而此 write 方法每次只写入1个字节。
        fos.write("好好学习".getBytes(StandardCharsets.UTF_8));
        fos.close();

        // 把 "好好学习" 字节流以追加写入的方式到文件
        OutputStream fos2 = new FileOutputStream("day12-api-io/src/22.txt", true);
        fos2.write("好好学习".getBytes());
        fos2.close();

        System.out.println("--- 👉 2、InputStream 读取文件字节流到程序");
        InputStream fis = new FileInputStream("day12-api-io/src/11.txt");
        // 1、read() 每次向前读取一个字节
//        int byte1 = fis.read();
//        int byte2 = fis.read();
//        System.out.printf("byte1 = %d, byte2 = %d%n ", byte1, byte2);   // byte1 = 77, byte2 = 66

        // 2、int read(byte b[])，最多读取数组长度的字节到数组中。返回值为真正读取的有效字节长度，即字节数不够时填不满，用0来填充。
        byte[] bytes = new byte[100];   // 每次读取100个字节
        int len = fis.read(bytes);  // len 为 -1 表示没有数据可读；其它值表示实际读取了多少个字节
        System.out.printf("读取了 len = %d 个字节到数组中 %n", len);  // 读取了 len = 14 个字节到数组中
        String readStr = new String(bytes, StandardCharsets.UTF_8);
        System.out.println("--- readStr = " + readStr + "***");
        String readStr2 = new String(bytes, 0, len, StandardCharsets.UTF_8);   // 指定需要解析字节的长度
        System.out.println("--- readStr2 = " + readStr2 + "***");

        // 3、循环读取
//        byte[] buf = new byte[3];   // 每次读取3个字节
//        int len2;    // 记录每次读取了结果字节
//        while ((len2 = fis.read(buf)) != -1) {
//            // 读取了几个 len2，就拿出几个转换为 String
//            String str = new String(buf, 0, len2);
//            System.out.println(str);
//        }

        fis.close();

    }

    /**
     * FileInputStream 一次性读取文件内容
     * public byte[] readAllBytes()，JDK9，一次性读取全部内容！
     */
    public static void readFileAll() throws Exception {
        // 1、创建字节输入流管道与源文件接通
        InputStream is = new FileInputStream("day09-io-code/src/czm02.txt");

        // 方法1
//        File f = new File("day09-io-code/src/czm02.txt");
//        // 定义字节数组，表示每次读取数组大小的字节数。
//        // 接收参数为 int 类型，避免一次性读取的字节数太多，导致把内存占满
//        byte[] buffer = new byte[(int)f.length()];
//
//        // 2、读取全部字节
//        int len = is.read(buffer);
//        System.out.println("读取了的字节数 = " + len);
//        System.out.println("读取的内容 = "+ new String(buffer,0,len));

        // 方法2
        byte[] buf = is.readAllBytes();    // 注意：读取大文件会报：内存异常
        System.out.println("读取的字节数 = " + buf.length);
        System.out.println("读取的全部内容 = " + new String(buf));

        is.close(); // 注意：操作完，关闭管道
    }

    /**
     * 案例：字节流非常适合做一切文件的复制操作
     */
    public static void copyFile() {
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
