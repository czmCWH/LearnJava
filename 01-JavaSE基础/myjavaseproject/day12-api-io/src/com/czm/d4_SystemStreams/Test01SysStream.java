package com.czm.d4_SystemStreams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Test01SysStream {

    /*
      案例：实现终端智能输入问答（系统的标准字节输入\输出流）

      1、如何从控制台读取内容？
       读取控制台的输入，相当于在读取键盘的输入，即标准输入。
       标准输入流 Standard InputStream，控制台输入、键盘的输入。System.in 表示标准输入流对象。
       标准输出流 Standard OutputStream，控制台输出、屏幕的输出。System.out 表示标准输出流对象。
     */

    public static void main(String[] args) {
        // 字节流转换为字符流：
        // System.in 是一个字节流对象 -> InputStreamReader 字符流 -> BufferedReader 带缓冲的字符流
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));) {
            String line;
            // readLine 每次读取控制台一行
            while ((line = reader.readLine()) != null) {
                line = line.replace("你", "朕");
                line = line.replace("您", "朕");
                line = line.replace("吗", "");
                line = line.replace("么", "");
                line = line.replace("？", "！");
                System.out.println("\t" + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
