package com.czm.d1_exception_x;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test3 {
    /*
     1、开发中对于异常的常见处理方式
        方法A --(调用)--> 方法B --(调用)--> 方法C
                       ||
                       \/
        方法C --(异常)--> 方法B --(异常)--> 方法A

        a、在方法A中集中捕获异常，记录异常并响应合适的信息给用户；
        b、在方法A中集中捕获异常，尝试重新修复；

     */
    public static void main(String[] args) {
//        try {
//            parseDate("2024-03-19 13:20:21");
//        } catch (ParseException e) {
//            e.printStackTrace();
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        System.out.println("---每抛出一个异常就要处理一个");

        try {
            parseDate2("2024-03-19 13:20:21");
        } catch (Exception e) {     // 集中捕获异常，然后处理
            e.printStackTrace();    // 然后记录到日志文件中
            System.out.println("--失败了");
        }
        System.out.println("---每抛出一个异常就要处理一个");

    }

    // 模拟抛出多个异常
    public static void parseDate(String str) throws ParseException, FileNotFoundException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = sdf.parse(str);    // 编译时异常，写代码时就报错。
        System.out.println(d);

        InputStream is = new FileInputStream("/D");     // 抛出编译时异常
    }

    // 使用父类 Exception 抛出一切异常
    public static void parseDate2(String str) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date d = sdf.parse(str);    // 编译时异常，写代码时就报错。
        System.out.println(d);

        InputStream is = new FileInputStream("/D"); // 抛出编译时异常
    }

}
