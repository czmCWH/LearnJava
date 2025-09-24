package com.czm.d2_safe;

/**
 * 实现 Runnable 接口来 封装线程执行的任务，售卖100张票，线程一运行就不停的卖，直到卖光！
 * 当多个线程执行同一个 Station 实例卖票时，会导致线程安全问题
 */
public class Station01 implements Runnable {

    private int tickets = 100;

    public boolean salleTicket() {
        if (tickets < 1) return false;
        tickets--;
        String name = Thread.currentThread().getName();
        System.out.printf("%s 卖了1张票，还剩下：%d 张\n", name, tickets);
        return tickets > 0;
    }

    @Override
    public void run() {
        while (salleTicket());
    }
}
