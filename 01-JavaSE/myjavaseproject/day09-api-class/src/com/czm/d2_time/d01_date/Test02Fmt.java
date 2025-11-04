package com.czm.d2_time.d01_date;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Test02Fmt {
    /*
     1、SimpleDateFormat 类
       java.text.SimpleDateFormat 用于进行日期格式化和处理处理。

       格式化：日期 -> 文本
       解析：文本 -> 日期

     */

    public static void main(String[] args) throws Exception {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd HH:mm:ss E", Locale.CHINA);

        Date d1 = new Date();
        System.out.println("--- d1 = " + d1);

        String dateStr = sdf.format(d1);
        System.out.println("--- 格式化当前时间 dateStr = " + dateStr); // 2025年09月15 19:18:02


        Date date = sdf.parse(dateStr);
        System.out.println("--- 解析指定格式的字符串为 Date 对象：" + date);

    }
}
