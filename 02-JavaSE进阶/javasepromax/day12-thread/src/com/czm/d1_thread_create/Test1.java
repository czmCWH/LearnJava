package com.czm.d1_thread_create;

public class Test1 {
    /*
     1、多线程
        多线程是指从软硬件上实现的多条执行流程的技术(多条线程由CPU负责调度执行)。
        Java 是通过 java.lang.Thread 类的对象来代表线程的。

     2、多线程的创建方式1：继承 Thread 类
        定义一个子类MyThread继承线程类java.lang.Thread，重写run()方法
        创建MyThread类的对象
        调用线程对象的start()方法启动线程(启动后还是执行run方法的

        优缺点：
            优点：编码简单。
            缺点：线程类已经继承 Thread，无法继承其他类，不利功能的扩展。
     */
    // main 方法本身就是由一条主线程执行的
    public static void main(String[] args) {

        // 1、通过自定义线程类创建线程对象
        Thread t = new MyThread("");
        // 2、启动线程：会自动执行线程的 run 方法
        t.start();
        // ⚠️：如果直接调用 run，CPU 不会注册新线程执行，此时相当于还是单线程。
        // 只有调用 start 方法才是启动一个新的线程执行。run 是线程的任务方法
//        t.run();

        for (int i = 0; i < 5; i++) {
            System.out.println("main 线程输出 = " + i);
        }

    }
}
