package com.czm.time.d01_date;

import java.util.Date;

public class Test01 {

    /*
     1、Date 类
      java.util.Date 是开发中经常用到的日期处理类(⚠️ 不是 java.sql.Date)
      Date 包含了年、月、日、时、分、秒等信息。

        GMT(Greenwich Mean Time) 格林尼治时间。
        CST(China Standard Time UT+8:00) 中国标准时间
        1970-01-01 00:00:00 GMT 与 1970-01-01 08:00:00 CST 表示的是同一个时间。

      ⚠️：Java 8 引入的 java.time 包（如 LocalDate、Instant）已替代
     */
    public static void main(String[] args) {
        // 1、创建 Date 日期对象
        Date date1 = new Date();
        System.out.println("--- 当前时间：" + date1);     // Mon Sep 15 18:55:35 CST 2025

        /*
         new Date(毫秒数)，表示从 1970-01-01 00:00:00 经过 毫秒数 后的时间。
         System.currentTimeMillis()，返回从 1970-01-01 00:00:00 到现在经历的毫秒数。
         */
        Date date2 = new Date(System.currentTimeMillis());
        System.out.println("--- 1970-01-01 00:00:00 + 毫秒参数 的时间：" + date2);

        // 2、Date 常用方法
        Date d1 = new Date();
        Date d2 = new Date();

        d1.setTime(1000);
        d2.setTime(2000);
        System.out.println("--- 设置时间毫秒数 d1 = " + d1);   // Thu Jan 01 08:00:01 CST 1970
        System.out.println("--- 设置时间毫秒数 d2 = " + d2);   // Thu Jan 01 08:00:02 CST 1970

        // 获取 毫秒数
        System.out.printf("d1.getTime = %d, d1.getTime = %d%n", d1.getTime(), d2.getTime());    // d1.getTime = 1000, d1.getTime = 2000

        // 比较时间
        System.out.println("d2 在 d1 的后面，d2比d1晚：" + d2.after(d1));    // true
        System.out.println("d1 在 d2 的前面，d1比d2早：" + d1.before(d2));     // true
        System.out.println("d1 大于 d2 吗？大于0 则 d1 > d2；小于0 则 d1 < d2" + d1.compareTo(d2));      // -1
    }
}
