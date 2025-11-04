package com.czm.d2_time.d03_jdk8;

import java.time.Instant;
import java.util.Date;

public class Test5_Instant {

    /*
     1、Instant，时间戳，表示时间线上的某个时刻/时间戳。
        通过获取 Instant 的对象可以拿到此刻的时间，
        该时间由两部分组成：从1970-01-01 00:0000 开始走到此刻的总秒数+不够1秒的纳秒数

     2、Instant 应用场景
        用来记录代码的执行时间；
        记录用户操作某个事件的时间点；
     */

    public static void main(String[] args) {
        Instant now = Instant.now();    // 获取当前时间戳
        System.out.println("--- 世界标准时间 = " + now);

        System.out.println("--- 总秒数 = " + now.getEpochSecond());
        System.out.println("--- 毫秒数 = " + now.toEpochMilli());
        System.out.println("--- 毫秒数 = " + System.currentTimeMillis());
        System.out.println("--- 不够1秒的纳秒数 = " + now.getNano());



        // Instant 和 Date 相互转换
        Date date = Date.from(now);
        System.out.println("date = " + date);

        Instant instant = date.toInstant();
        System.out.println("instant = " + instant);

    }
}
