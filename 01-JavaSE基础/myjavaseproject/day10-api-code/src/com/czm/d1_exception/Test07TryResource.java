package com.czm.d1_exception;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class Test07TryResource {

    /*
     1、try-with-resources 语句
      从 Java7 开始推出 try-with-resources 语句（可以没有 catch、finally）
        try (资源1；资源2；...) {

        } catch (Exception e) {

        } finally {

        }
      可以在 try 后面的小括号中声明一个或多个资源（resource）。资源是指实现了 java.lang.AutoCloseable 接口的实例。
      不管 try 中的语句是正常还是意外结束，最终都会自动按顺序调用每一个资源的 close 方法（close 方法的调用顺序与资源的声明顺序相反）。

      try-with-resources 在处理 File 文件 数据时常用

     2、

     3、
     */
    public static void main(String[] args) {

        File file = new File("/Users/chen/Desktop/abc/aa/1.txt");
        try (FileInputStream fis = new FileInputStream(file);) {    // 使用 try-with-resources 就不必在 finally 对 fis.close;
            byte[] bytes = new byte[(int) file.length()];
            fis.read(bytes);
            System.out.println("读取文件成功：" + new String(bytes, StandardCharsets.UTF_8));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
