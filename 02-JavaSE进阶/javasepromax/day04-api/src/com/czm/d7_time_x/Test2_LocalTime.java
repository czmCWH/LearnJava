package com.czm.d7_time_x;

import java.time.LocalTime;

public class Test2_LocalTime {
    public static void main(String[] args) {
        // 1、获取本地时间对象
        System.out.println("--------- 1、获取本地时间对象");
        LocalTime lt = LocalTime.now();
        System.out.println("--- 格式为：时：分：秒.纳秒 = " + lt);

        // 2、获取时间中的信息
        System.out.println("--------- 2、获取时间中的信息");
        int hour = lt.getHour();
        int minute = lt.getMinute();
        int second = lt.getSecond();
        int nano = lt.getNano();    // 纳秒
        System.out.println("----" + hour + ":" + minute + ":" + second + ":" + nano);

        // 3、修改时间 时、分、秒、纳秒
        LocalTime lt1 = lt.withHour(15);
        LocalTime lt2 = lt.withMinute(30);
        LocalTime lt3 = lt.withSecond(10);
        LocalTime lt4 = lt.withNano(1000);
        System.out.println("LocalTime 是一个不可变对象 =" + lt);

        // 4、把某个时间信息增加
        System.out.println("--------- 4、把某个信息增加");
        LocalTime lt5 = lt.plusHours(3);
        LocalTime lt6 = lt.plusMinutes(10);
        LocalTime lt7 = lt.plusSeconds(30);
        LocalTime lt8 = lt.plusNanos(50);

        // 5、把某个时间信息减少
        System.out.println("------- 5、把某个时间信息减少");
        LocalTime lt11 = lt.minusHours(3);
        LocalTime lt12 = lt.minusMinutes(10);
        LocalTime lt13 = lt.minusSeconds(30);
        LocalTime lt14 = lt.minusNanos(1000);

        // 6、获取指定时间的对象
        LocalTime lt15 = LocalTime.of(23, 15, 30);
        LocalTime lt16 = LocalTime.of(23, 15, 30);
        System.out.println("------- 判断时间");
        System.out.println(lt15.equals(lt16));
        System.out.println(lt15.isAfter(lt));
        System.out.println(lt15.isBefore(lt));
    }
}
