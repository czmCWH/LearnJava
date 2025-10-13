package com.czm.d5_ReentrantLock;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 使用 ReentrantLock 实现多线程售卖票
 */
public class Station implements Runnable {

    private int tickets = 100;
    private ReentrantLock lock = new ReentrantLock();

    /**
     * 售卖 1 张票
     */
    public boolean salleTicket() {
        try {
            lock.lock();

            // flag：true 表示锁未被其它线程占用，此线程会获取该锁；false：表示锁被其它线程拥有，但代码会继续向下执行。
//            boolean flag = lock.tryLock();

            if (tickets < 1) return false;
            tickets--;
            String name = Thread.currentThread().getName();
            System.out.printf("%s 卖了1张票，还剩下：%d 张\n", name, tickets);
            return tickets > 0;
        } finally {
            lock.unlock();
        }
    }

    /**
     * tryLock 解析
     */
    public boolean salleTicket2() {
        boolean flag = false;
        try {
            // flag：true 表示锁未被其它线程占用，此线程会获取该锁；false：表示锁被其它线程拥有，但代码会继续向下执行。
            flag = lock.tryLock();

            if (tickets < 1) return false;
            tickets--;
            String name = Thread.currentThread().getName();
            System.out.printf("%s 卖了1张票，还剩下：%d 张\n", name, tickets);
            return tickets > 0;
        } finally {
            if (flag) lock.unlock();    // 此线程拥有 lock 才能解锁
        }
    }

    @Override
    public void run() {
        while (salleTicket());
    }
}
