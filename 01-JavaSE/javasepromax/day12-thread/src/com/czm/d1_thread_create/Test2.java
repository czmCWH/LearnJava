package com.czm.d1_thread_create;

public class Test2 {
    /*
     1、多线程的创建方法二：实现 Runnable 接口的方式
        定义一个线程任务类MyRunnable实现Runnable接口，重写run()方法
        创建MyRunnable任务对象
        把MyRunnable任务对象交给Thread处理
        调用线程对象的start()方法启动线程

     2、方式二优缺点
        优点: 任务类只是实现接口，可以继续继承其他类、实现其他接口，扩展性强
        缺点: 需要多一个Runnable对象。
     */
    public static void main(String[] args) {
        // 1、创建一个任务类对象
        Runnable r = new MyRunnable();
        // 2、把任务类对象交个线程对象
        // public Thread(Runnable task)
        Thread t = new Thread(r);
        // 3、启动线程
        t.start();

        for (int i = 0; i < 5; i++) {
            System.out.println("main 线程输出 = " + i);
        }
    }
}
