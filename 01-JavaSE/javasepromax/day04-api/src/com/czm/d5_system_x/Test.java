package com.czm.d5_system_x;

public class Test {
    /*
     1、System
        System 代表程序所在的系统，也是一个工具类。

     2、
     */
    public static void main(String[] args) {

        // 终止当前运行的 Java 虚拟机
//        System.exit(0); // 0 代表是正常终止 JVM 虚拟机

        // 返回当前系统的时间毫秒值形式
        // 返回的是从1970-1-1 00:00:00走到此刻的总毫秒值(1s=1000ms)
        // 常用于性能统计
        long time = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            System.out.println(i);
        }
        long time2 = System.currentTimeMillis();
        System.out.println("----" + (time2 - time)/1000.0 + "s");


    }
}
