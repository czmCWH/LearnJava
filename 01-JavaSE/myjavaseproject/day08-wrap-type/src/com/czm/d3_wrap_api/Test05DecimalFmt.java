package com.czm.d3_wrap_api;

import java.text.DecimalFormat;

public class Test05DecimalFmt {
    public static void main(String[] args) {
        /*
         1、DecimalFormat
         使用 java.text.DecimalFormat 可以更好地控制前 0、后 0、前缀、后缀、分组分隔符、十进制分隔符等。

         */

        customFormat("###,###.###", 123456.789);    // 123,456.789  本地化
        customFormat("###.##", 123456.789);     // 123456.79  保留小数点后2位
        customFormat("000000.000", 123.78);     // 000123.780  小数点前显示6位，不够用0补齐
        customFormat("$###,###.###", 123456.78);    // $123,456.78
    }

    public static void customFormat(String pattern, double value) {
        DecimalFormat df = new DecimalFormat(pattern);
        System.out.println(df.format(value));
    }
}
