package com.czm.d4_print_stream;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.PrintWriter;

public class Test1 {
    /*
     1、PrintStream / PrintWriter (打印流)
        打印流 可以实现更方便、更高效的打印数据出去，能实现打印啥出去就是啥出去。

     2、PrintStream / PrintWriter 的区别
        打印数据 的功能上是一模一样的：性能高效（核心优势）都是使用方便；
        PrintStream 继承自 字节输出流 OutputStream，因此支持写字节数据的方法；
        PrintWriter 继承自 字符输出流 Writer，因此支持写字符数据出去，注意：一般不用此功能

        打印流优势：两者在打印功能上都是使用方便，性能高效（核心优势）。

     */
    public static void main(String[] args) {
        try (
                // 打印流 可以 方便、高效的写数据出去！
                // 1、打印流直接通向字节输出流/文件/文件路径
//                PrintStream ps = new PrintStream("day10-io-code/src/print01.txt");
                // 追加打印的内容
                PrintWriter ps = new PrintWriter(new FileWriter("day10-io-code/src/print01.txt", true));
        ) {
            ps.println("打印输出到文件的内容：");
            ps.print("我开心");
            ps.print("🥰😃");
            ps.println("abc");
            ps.println("123");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
