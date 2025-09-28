package com.czm.d4_SystemStreams;

import java.io.*;

public class Test03Print {
    /*
      1、格式化输出
       PrintStream、PrintWriter 用于格式化输出。常用方法：
        print、println、format

      2、PrintStream - 输出到 控制台
       System.out、System.err 是 PrintStream 类型的实例，它们属于标准输出流（Standard Output Stream），比如输出到屏幕、控制台。

       PrintStream 是字节流，但它内部利用字符流对象来模拟字符流的许多功能。
       PrintStream 一般用于输出到控制台，不建议使用它 new 输出到文件。

      3、PrintWriter - 输出到 文件
       ⚠️ 平时若要创建格式化的输出流，一般使用 PrintWriter，它是字符流。
       设置 PrintWriter 的构造方法 autoFlush 参数为 true，那么它在执行 println、printf、format 方法内部就会自动调用 flush 方法（自动刷新内存缓冲区到文件）。

     */
    public static void main(String[] args) {

        System.out.println();


        // 1、PrintWriter 格式化输出到文件
        try {
            // 方式1，会直接覆盖文件中的内容
//            PrintWriter pw = new PrintWriter("/Users/chen/Desktop/33.txt");
            // 方式2，是否以追加的方式
            PrintWriter pw = new PrintWriter(new FileOutputStream(new File("/Users/chen/Desktop/33.txt"), false), true);
            String name = "翠花";
            int age = 18;
            pw.format("%s 永远 %d 岁！", name, age);
            int a = 97;
            pw.print(a);   // 写入数字 97，print 使用字节流直接输出
            pw.write(a);   // 写入字符：a，write 默认实现了格式化输出
            pw.write("\n");
//            pw.flush();   // 刷新缓冲区
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }



    }
}
