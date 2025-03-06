package com.czm.d7_time;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

public class Test7_Period_Duration {

    /*
     1、Period
        一段时间，时间间隔(年，月，日)
	    可以用于计算两个 LocalDate 对象 相差的年数、月数、天数。

     2、Duration
	    持续时间，时间间隔(时、分、秒，纳秒)。
	    可以用于计算两个时间对象相差的天数、小时数、分数、秒数、纳秒数;
	    支持LocalTime、LocalDateTime、Instant等时间;


     */
    public static void main(String[] args) {

        System.out.println("----- Period 计算时间差");

        LocalDate start = LocalDate.of(2024, 3, 19);
        LocalDate end = LocalDate.of(2024, 10, 13);

        // 1、创建一个 period 对象
        Period period = Period.between(start, end);

        // 2、通过 period 对象获取2个时间相差的时间信息
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());   // 打印：24，11 + 13，⚠️⚠️⚠️，是按照30天每月来换算的。

        System.out.println("----- Duration 计算时间差");
        LocalDateTime start1 = LocalDateTime.of(2025, 11, 11, 11, 10, 10);
        LocalDateTime end1 = LocalDateTime.of(2025, 11, 11, 11, 11, 11);

        // 1、创建一个 Duration 对象
        Duration duration = Duration.between(start1, end1);

        // 2、获取两个时间的间隔
        System.out.println(duration.toDays());
        System.out.println(duration.toHours());
        System.out.println(duration.toMinutes());
        System.out.println(duration.toSeconds());
        System.out.println(duration.toMillis());
        System.out.println(duration.toNanos());




    }
}
