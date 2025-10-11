package com.czm.d2_time.d03_jdk8;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test6_DateTimeFormatter {

    // ⚠️⚠️⚠️：DateTimeFormatter：用于时间的格式化和解析

    public static void main(String[] args) {
        // 1、创建一个日期时间格式化器对象
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 2、对时间进行格式化
        LocalDateTime ldt = LocalDateTime.now();

        String rs = dft.format(ldt);
        System.out.println("--- 格式化本机时间 = " + rs);

        // 3、格式化时间，另一种方式
        String rs2 = ldt.format(dft);
        System.out.println("--- 格式化时间方案2 = " + rs2);

        // 4、解析时间
        String dateStr ="2023-11-11 11:11:11";
        // ⚠️：时间格式化器与时间字符串必须一摸一样
        DateTimeFormatter dft2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt2 = LocalDateTime.parse(dateStr, dft2);
        System.out.println("--- 解析时间 = " + ldt2);

    }
}
