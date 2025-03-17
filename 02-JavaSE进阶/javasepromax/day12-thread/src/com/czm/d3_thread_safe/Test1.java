package com.czm.d3_thread_safe;

public class Test1 {
    /*
     1、线程安全
         多个线程，同时操作同一个共享资源的时候，可能会出现业务安全问题。

     2、案例一：两个人同时取钱，一个用银行卡，一个用存折，会出现钱的余额为负数，出现线程安全问题！

     */
    public static void main(String[] args) {
        // 1、创建一个账号对象
        Account abc = new Account("abc", 1000);

        // 2、创建2个线程代表2个人取钱
        new DrawThread("小明", abc).start();
        new DrawThread("小红", abc).start();

    }
}
