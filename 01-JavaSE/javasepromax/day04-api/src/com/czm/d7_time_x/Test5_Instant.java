package com.czm.d7_time_x;

import java.time.Instant;

public class Test5_Instant {

    /*
     1、Instant：表示时间线上的某个时刻/时间戳

        通过获取 Instant 的对象可以拿到此刻的时间，
        该时间由两部分组成：从1970-01-01 00:0000 开始走到此刻的总秒数+不够1秒的纳秒数

    2、Instant 的作用
        用来记录代码的执行时间；
        记录用户操作某个事件的时间点；

     */

    public static void main(String[] args) {
        Instant now = Instant.now();
        System.out.println("--- 世界标准时间 = " + now);

        System.out.println("--- 总秒数 = " + now.getEpochSecond());
        System.out.println("--- 不够1秒的纳秒数 = " + now.getNano());


    }
}
