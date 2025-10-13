package com.czm.d2_time.d02_calendar;

import java.util.Calendar;
import java.util.Date;

public class Test02 {
    public static void main(String[] args) {

        // 1、打印格式化（很少用）
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        System.out.format("%tB %te, %tY%n",  date, date, date);     // 九月 15, 2025
        System.out.format("%tl:%tM %tp%n",  c, c, c);   // 7:54 下午
        System.out.format("%tD%n",  c);     // 09/15/25

    }
}
