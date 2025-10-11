package com.czm.d7_time_x;

// ⚠️ JDK8 新增的时间

import java.time.LocalDate;

public class Test1_LocalDate {
    /*
     1、

     2、

     3、
     */
    public static void main(String[] args) {
        // 1、获取 LocalDate 对象，代表此刻日期，它是个不可变对象
        LocalDate ld = LocalDate.now();
        System.out.println("1、当前日期 = " + ld.toString());     // 2024-03-19
        // 2、单独获取 年、月、日
        int year = ld.getYear();
        int month = ld.getMonthValue();
        int day = ld.getDayOfMonth();   // 当月的第几天
        int dayOfYear = ld.getDayOfYear();  // 当年的第几天
        int dayOfWeek = ld.getDayOfWeek().getValue();   // 当前星期的第几天，即星期几
        System.out.println("--- 2、单独获取年月日 = " + year + "-" + month + "-" + day + "-" + dayOfYear + "-" + dayOfWeek);

        // 3、修改某个日期，产生新的对象
        System.out.println("----3、把某部分信息修改");
        LocalDate ld2 = ld.withYear(2035);
        LocalDate ld3 = ld.withMonth(4);
        System.out.println("修改年 = " + ld2);
        System.out.println("修改月 =" + ld3);

        // 4、把某个信息增加多少
        System.out.println("----4、增加日期");
        LocalDate ld4 = ld.plusYears(2);
        LocalDate ld5 = ld.plusMonths(10);
        System.out.println(ld4);
        System.out.println(ld5);

        // 5、获取指定日期的 LocalDate 对象
        System.out.println("-------5、获取指定日期的 LocalDate 对象");
        LocalDate ld6 = LocalDate.of(2035, 3, 25);
        LocalDate ld7 = LocalDate.of(2035, 3, 25);
        System.out.println("----日期判断");
        System.out.println(ld6.equals(ld7));    // true
        System.out.println(ld6.isAfter(ld));    // true
        System.out.println(ld6.isBefore(ld));   // false

    }
}
