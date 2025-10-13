package com.czm.d2_safe;

public class Station02 implements Runnable {

    private int tickets = 100;

    // 使用 线程同步 让同一时刻只能有一个线程进入卖票
    public boolean salleTicket() {
        // 线程同步 - 同步语句
        synchronized (this) {
            if (tickets < 1) return false;
            tickets--;
        }
        String name = Thread.currentThread().getName();
        System.out.printf("%s 卖了1张票，还剩下：%d 张\n", name, tickets);
        return tickets > 0;
    }

    // 2、线程同步 - 同步方法
//    public synchronized boolean salleTicket() {
//        if (tickets < 1) return false;
//        tickets--;
//        String name = Thread.currentThread().getName();
//        System.out.printf("%s 卖了1张票，还剩下：%d 张\n", name, tickets);
//        return tickets > 0;
//    }

    @Override
    public void run() {
        while (salleTicket());
    }
}
