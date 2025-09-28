package com.czm.d3_Streams;

import java.io.*;
import java.util.Arrays;

public class Test02Character {

    /*
     1、字符流（Character Streams）
      字符流的特点：
        ⚠️ 一次只读写一个字符；
        最终都继承自 Reader、Writer
      常用的字符流有：FileReader、FileWriter，这2个类只适合文本文件，如：.txt、.java 等这类文件。

     2、字节流 可以转换为 字符流
        InputStreamReader，用于把【字节输入流】转为【字符输入流】
        OutputStreamWriter，用于把【字节输出流】转为【字符输出流】


     */

    public static void main(String[] args) {

        System.out.println("--- 1、输出流：向文件中写入字符");
        try (Writer writer = new FileWriter("/Users/chen/Desktop/22.txt");) {
            writer.write("国");
            writer.write("庆");
            writer.write("节");
            writer.write("快");
            writer.write("乐！");
            writer.write("好开心啊");
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n--- 2、输入流：从文件中读取字符");
        try (Reader reader = new FileReader("/Users/chen/Desktop/22.txt")) {
            char[] buf = new char[1024];    // 一次性读取 1024 个字符
            // read 方法执行后返回实际读取的字符个数
            int len = reader.read(buf);
            System.out.println("--- 读取了：" + Arrays.toString(buf));
            System.out.println("--- 读取内容：" + new String(buf, 0, len));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("\n--- 3、案例-将文本文件逐个字符打印：");
        try (Reader reader = new FileReader(new File("/Users/chen/Desktop/33.txt"))) {
            int c;
            while ((c = reader.read()) != -1) {
                System.out.print((char) c);
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }




    }
}
