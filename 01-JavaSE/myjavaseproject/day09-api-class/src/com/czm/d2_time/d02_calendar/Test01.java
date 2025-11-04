package com.czm.d2_time.d02_calendar;

import java.util.Calendar;
import java.util.Date;

public class Test01 {

    /*
     ---------- 第二代日期类
     1、Calendar
     java.util.Calendar 也是开发中经常用到的日期处理类。它是一个 abstract 抽象类。
     功能比 Date 更加丰富，Date 中很多过期的方法都迁移到了 Calendar 中。Calendar 用于替代 Date 类

     Calender 为特定瞬间与一组诸如year、month、day_of_month、hour等日历字段之间的转换提供了一些方法，并为操作日历字段提供了一些方法。

     ⚠️：Java 8+推荐使用java.time包（如 LocalDate、ZonedDateTime）
     */

    public static void main(String[] args) {

        // getInstance 方法返回日历对象，默认表示当前时间
        Calendar calendar = Calendar.getInstance();

        // 1、获取 年月日 时分秒，Calendar 没有专门格式化的方法，需要开发者自己按需组合显示
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH); // 注意：月取值范围：[0, 11]，0表示一月，11表示12月。
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);  // Calendar.HOUR_OF_DAY，24小时制；Calendar.HOUR，12小时制；
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        System.out.printf("当前时间：%d-%02d-%02d %02d:%02d:%02d\n", year, month + 1, day, hour, minute, second);  // 2025-09-15 19:33:30

        System.out.println("\n");

        // 2、获取第几天
        // 一年中第几天，取值范围 [1, 366]
        System.out.println("--- 一年中的第几天：" + calendar.get(Calendar.DAY_OF_YEAR));
        // 当前月中的第几天，取值范围 [1, 31]
        System.out.println("--- 月的第几天：" + calendar.get(Calendar.DAY_OF_MONTH));
        // 一周中的第几天，取值范围 [1, 7]，1是星期天；7是星期六
        System.out.println("--- 一周中的第几天：" + calendar.get(Calendar.DAY_OF_WEEK));

        System.out.println("\n");

        // 3、Calendar 其它常用方法
        Calendar c = Calendar.getInstance();
        c.set(2035, 7, 12);
        System.out.println("--- 通过 数字值 修改日历时间：" + c.getTime());    // Sun Aug 12 19:45:59 CST 2035
        c.add(Calendar.MONTH, 7);
        System.out.println("--- c.getTime 方式获取 增加月数 后的 Date 对象：" + c.getTime());    // Wed Mar 12 19:49:57 CST 2036
        c.add(Calendar.DATE, 3);
        System.out.println("--- c.getTime 方式获取 增加天数 后的 Date 对象：" + c.getTime());    // Sat Mar 15 19:49:57 CST 2036

        c.setTime(new Date());
        System.out.println("--- 通过 Date 对象修改日历时间：" + c.getTime());  // Mon Sep 15 19:49:57 CST 2025

        c.setTimeInMillis(System.currentTimeMillis());
        System.out.println("--- 通过 毫秒数 修改日历时间：" + c.getTime());     // Mon Sep 15 19:49:57 CST 2025

        System.out.println("--- 获取日历时间的毫秒数：" + c.getTimeInMillis());    // 1757936997506

    }
}
