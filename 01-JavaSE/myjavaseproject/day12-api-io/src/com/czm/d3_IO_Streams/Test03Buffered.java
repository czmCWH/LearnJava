package com.czm.d3_IO_Streams;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Test03Buffered {

    /*
     1、缓冲流（Buffered Streams）- 提高效率
      前面的 字节流、字符流，都是无缓冲的 I/O 流，每个读写操作均由底层操作系统直接处理。
      由于每个读写操作通常会触发磁盘访问，因此大量的读写操作，可能会使程序的效率大大降低。

      为了减少读写操作带来的开销，Java 实现了缓冲的 I/O 流：
        缓冲输入流：从缓冲区读取数据，并且只有当缓冲区为空时才调用本地的输入 API（Native Input API）；
        缓冲输出流：将数据写入缓冲区，并且只有当缓冲区已满时才调用本地的输出 API（Native Output API）；
        优点：减少了磁盘读写次数，提高了读写效率。

     2、缓冲流的类型
                      缓冲输入流              缓冲输出流
      缓冲字节流     BufferedInputStream     BufferedOutputStream
      缓冲字符流     BufferedReader          BufferedWriter

      注意：如上缓冲流类的默认缓冲区大小是 8192字节（8KB），可以通过构造方法传参设置缓冲区大小。

     3、缓冲流的使用
      缓冲流的常见使用方式：将无缓冲流传递给缓冲流的构造方法（将无缓冲流包装成缓冲流）。

     4、缓冲流 — close、flush
      只需要执行缓冲流的 close 方法，不需要执行缓冲流内部包装的无缓冲流的 close 方法。

      调用缓冲输出流的 flush 方法，会强制调用本地的输出 API，将缓冲区的数据真正写入到文件中。
      另外，缓冲输出流的 close 方法内部会调用一次 flush 方法。

     */

    public static void main(String[] args) {

        File file = new File("/Users/chen/Desktop/33.txt");

//        testBufferedInput(file);

//        testBufferedWriter(file);

//        testExamplePrint(file);

//        testExampleLinPrint(file);

        testEncodeDecodeFile();
    }

    private static void testBufferedInput(File file) {
        try {
            InputStream inputStream = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(inputStream);
            byte[] bytes = new byte[(int) file.length()];
            int len = bis.read(bytes);
            System.out.println("--- 1、缓冲字节流读读取：" + new String(bytes, 0, len, StandardCharsets.UTF_8));
            bis.close();    // 缓冲流 close 时，它封装的非缓冲流也会进行 close。
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void testBufferedWriter(File file) {
        System.out.println("\n--- 2、缓冲字符输出流写入数据：");
        try {
            BufferedWriter bwr = new BufferedWriter(new FileWriter(file, true));
            bwr.newLine();
            bwr.write("好好学习");
            bwr.newLine();  // 换行
            bwr.write("天天向上");
//            bwr.flush();  // 缓冲输出流需要等缓冲区数据填满后，才写入到磁盘。调用 flush 方法会强制将缓冲区数据写入磁盘。
            bwr.close();    // close 时，内部会调用一次 flush。

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testExamplePrint(File file) {
        System.out.println("\n--- 3、案例-用缓冲流逐个打印字符：");
        try (Reader reader = new BufferedReader(new FileReader(file))) {
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
                Thread.sleep(100);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void testExampleLinPrint(File file) {
        System.out.println("\n--- 4、案例 - 使用缓冲流逐行打印字符串：");
        try (BufferedReader reader = new BufferedReader(new FileReader(file));) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static void testEncodeDecodeFile() {
        System.out.println("--- 5、案例：转换文本文件的编码！");

        System.out.println("\n--- 1、准备好 GBK 编码格式的 gbk.txt 文件：");
        String str = "春晓\n孟浩然\n春眠不觉晓，处处闻啼鸟。\n夜来风雨声，花落知多少。";

        // 把字符串 str 转换为 GBK 编码格式的字节存入到文件，就是 GBK 格式文件。
        byte[] bytes = str.getBytes(Charset.forName("GBK"));

        File file = new File("/Users/chen/Desktop/gbk.txt");

        try (BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(file));) {
            writer.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n--- 2、把 gbk.txt 文件转换为 utf-8 文件：");
        // gbk.txt 文件存储的字节采用的是 GBK 编码，所以读取其字符时需要指定 GBK 编码格式；
        // 写入 utf-8.txt 文件时，需要把字符以 UTF-8 编码写入。
        try (
                // ⚠️：字节流 -> 字符流 -> 带缓冲的字符流
                BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "GBK"));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("/Users/chen/Desktop/utf-8.txt"), "UTF-8"))
            ) {
            char[] chars = new char[1024];
            int len;
            while ((len = reader.read(chars)) != -1) {
                writer.write(chars, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
