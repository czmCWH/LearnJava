package com.czm.d2_thread_api;

import com.czm.d1_thread_create.MyThread;

public class Test1 {
    /*
     1、

     3、
     */
    public static void main(String[] args) throws Exception {
        MyThread t1 = new MyThread("");
        // 3、setName 设置线程名称
        t1.setName("t1");
        t1.start();
        // 1、getName 获取当前线程的名称，线程名称默认是Thread-索引
        System.out.println(t1.getName());

        MyThread t2 = new MyThread("");
        t1.setName("t2");
        t2.start();
        System.out.println(t2.getName());

        // 2、Thread.currentThread 获取当前代码执行所在的线程对象
        Thread m = Thread.currentThread();
        System.out.println(m.getName());

        for (int i = 0; i < 10; i++) {
            System.out.println("输出：" + i);
            // 3、让当前执行的线程休眠多少毫秒后，再继续执行
            Thread.sleep(1000);
        }
    }
}
