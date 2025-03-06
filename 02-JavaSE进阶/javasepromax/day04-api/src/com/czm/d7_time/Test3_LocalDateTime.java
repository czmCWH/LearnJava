package com.czm.d7_time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Test3_LocalDateTime {
    public static void main(String[] args) {
        // ⚠️：最重要的一个类

        // 1、获取本地时间对象，它是不可变对象
        LocalDateTime ldt = LocalDateTime.now();
        System.out.println("----本地时间对象 = " + ldt);

        // 2、获取单个时间信息
        System.out.println("----------- 2、获取单个时间信息");
        int year = ldt.getYear();
        int month = ldt.getMonthValue();
        int day = ldt.getDayOfMonth();
        int dayOfYear = ldt.getDayOfYear(); // 一年中第几天
        int weak = ldt.getDayOfWeek().getValue();
        System.out.println("--- 年月日星期 = " + year + "-" + month + "-" + day);
        System.out.println("---天 - 星期 = " + dayOfYear + "---" + weak);
        int hour = ldt.getHour();
        int minute = ldt.getMinute();
        int second = ldt.getSecond();
        int nano = ldt.getNano();   // 纳秒

        // 3、修改时间信息
        System.out.println("------ 3、修改时间信息");
        LocalDateTime ldt1 = ldt.withYear(2035);
        LocalDateTime ldt2 = ldt.withMinute(30);

        // 4、增加时间
        LocalDateTime ldt3 = ldt.plusHours(3);
        System.out.println("--- 4、增加时间 = " + ldt3);

        // 5、减时间
        LocalDateTime ldt4 = ldt.minusDays(3);
        System.out.println("---- 5、减时间 = " + ldt4);

        // 6、指定时间创建对象
        LocalDateTime ldt5 = LocalDateTime.of(2025, 3, 23, 18, 30, 20, 100);
        LocalDateTime ldt6 = LocalDateTime.of(2025, 3, 23, 18, 30, 20, 100);

        // 7、判断时间前后
        System.out.println("---- 7、判断时间前后");
        System.out.println(ldt5.equals(ldt6));
        System.out.println(ldt5.isAfter(ldt));
        System.out.println(ldt5.isBefore(ldt));

        // 8、类型转换
        LocalDate ld = ldt.toLocalDate();
        LocalTime lt = ldt.toLocalTime();
        LocalDateTime ldt9 = LocalDateTime.of(ld, lt);

    }
}
