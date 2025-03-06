package com.czm.d7_time;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test8 {

    // 案例：高考倒计时

    public static void main(String[] args) {
        // 1、实例化高考时间
        String startTime = "2025-06-07 09:00:00";
        DateTimeFormatter dft = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime ldt = LocalDateTime.parse(startTime, dft);

        // 2、本机时间
        LocalDateTime now = LocalDateTime.now();

        // 3、计算时间差
        Duration duration = Duration.between(now, ldt);
        String day = duration.toDays() + "天";
        String hour = duration.toHoursPart() + "时";
        String minute = duration.toMinutesPart() + "分";
        String second = duration.toSecondsPart() + "秒";
        System.out.println("---距离高考 = " + day + " " + hour + " " + minute + " " + second);


    }
}
