package com.czm.d5_AnonymousClass;

import com.czm.d5_AnonymousClass.util.Files;
import com.czm.d5_AnonymousClass.util.Networks;
import com.czm.d5_AnonymousClass.util.Times;

import java.io.File;
import java.io.FileFilter;

public class Test02 {

    /*
     1、匿名类的常见用途
      代码传递
      过滤器
      回调，与代码传递一样

     2、

     */

    public static void main(String[] args) {

        // 1、代码传递 - 案例：实现一个工具类，统计某一段代码执行的时间
        Times.test(new Times.Block() {
            // 把需要统计时间的代码放到 execute 中即可！
            @Override
            public void execute() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(500);
                        System.out.println("--- i = " + i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // 2、回调 - 案例：网络请求
        Networks.get("https://www.baidu.com", new Networks.Block() {
            @Override
            public void success(Object response) {
                System.out.println("--- 网络请求成功！");
            }

            @Override
            public void failure(String msg) {
                System.out.println("--- 网络请求失败：" + msg);
            }
        });

        // 3、过滤器 - 案例：过滤文件名
        String[] files = Files.getAllFilenames("D:/", new Files.Filter() {

            @Override
            public boolean accept(String fileName) {
                return fileName.endsWith(".txt");
            }
        });


    }
}
