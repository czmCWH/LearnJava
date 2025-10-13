package com.czm.d4_notify;

/**
 * 消费者
 */
public class Consumer implements Runnable {
    private Drop drop;
    public Consumer(Drop drop) {
        this.drop = drop;
    }
    public void run() {
        // food 用于接收生产生产的东西
        String food = null;
        while ((food = drop.get()) != null) {   // 获取生产者的东西
            System.out.printf("消费者 消费了: %s\n", food);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}
        }
    }
}
