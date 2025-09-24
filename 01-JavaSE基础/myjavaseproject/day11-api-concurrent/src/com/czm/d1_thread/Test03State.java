package com.czm.d1_thread;

public class Test03State {

    /*
      1、线程的状态
       通过 Thread.getState 方法可以获得线程的状态，线程一共有6种状态：
        NEW，尚未启动状态，线程尚未启动。
        RUNNABLE，可运行状态，即线程正在JVM中运行（running），或者正在等待操作系统的其他资源（比如CPU调度）(ready 就绪)。
        BLOCKED，阻塞状态，线程正在等待 monitor lock（监视器锁、内部锁）。
        WAITING，等待状态，线程处于无限期等待另一个线程执行特定操作。调用以下方法会处于此状态：
            不设置超时值参数的 Object.wait() 方法；
            不设置超时值参数的 Thread.join() 方法；
            LockSupport.park；

        TIMED_WAITING，定时等待状态，线程处于等待另一个线程执行操作长达指定等待的时间。调用以下方法会处于此状态：
            Thread.sleep;
            设置超时值参数的 Object.wait() 方法；
            设置超时值参数的 Thread.join() 方法；
            LockSupport.parkNanos；
            LockSupport.parkUntil；

        TERMINATED，终止状态，线程执行完毕已退出。

      2、线程的状态切换
        new --->  runnable <---> blocked / waiting / timed_waiting
                     ↓
                  terminated
     */

    public static void main(String[] args) {
        Thread mainThread = Thread.currentThread();
        System.out.println(mainThread.getState());  // RUNNABLE，即

        Thread thread = new Thread(() -> {
            System.out.println("开启新线程");
        });
        System.out.println(thread.getState());  // NEW
        thread.start();
        System.out.println(thread.getState());  // RUNNABLE

    }
}
