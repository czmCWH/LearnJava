package com.czm.d4_print_stream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class Test2 {
    /*
     1、打印流的一种应用：输出语句的重定向
     */
    public static void main(String[] args) throws Exception {

        System.out.println("红豆生南国，春来发几枝");

        // 把系统的输出语句重定向：
        PrintStream ps = new PrintStream(new FileOutputStream("day10-io-code/src/print02.txt", true));
        System.setOut(ps);

        System.out.println("愿君多采撷，此物最相思");


    }
}
