package com.czm.d7_time_x;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Test4_ZoneId_ZonedDateTime {

    // ZoneId：时区
    // ZonedDateTime：带时区的时间

    public static void main(String[] args) {
        // 1、ZoneId 表示时区
        ZoneId zoneId = ZoneId.systemDefault();
        String name = zoneId.getId();
        System.out.println("----获取本地时区标识 = " + name);

        System.out.println("=========== Java 支持的全部时区");
        System.out.println(ZoneId.getAvailableZoneIds());
        System.out.println("=========");

        // 根据时区ID创建时区
        ZoneId zoneId1 = ZoneId.of("America/New_York");

        // 2、获取带时区的时间
        ZonedDateTime americaNow = ZonedDateTime.now(zoneId1);
        System.out.println("---- 2、获取带时区的时间 = " + americaNow);

        // 3、世界标准时间，⚠️：很多服务器需要获取时间时间
        ZonedDateTime now = ZonedDateTime.now(Clock.systemUTC());
        System.out.println("--- 世界时间 = " + now);

        // 4、获取 ZonedDateTime 的单个时间
        int year = now.getYear();
        int month = now.getMonthValue();
        int day = now.getDayOfMonth();
        int weak = now.getDayOfWeek().getValue();   // 星期几
        int dayOfYear = now.getDayOfYear(); // 一年的第几天
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();



    }
}
