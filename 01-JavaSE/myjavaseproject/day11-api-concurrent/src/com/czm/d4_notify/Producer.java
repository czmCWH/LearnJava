package com.czm.d4_notify;

/**
 * 生产者
 */
public class Producer implements Runnable {
    private Drop drop;
    public Producer(Drop drop) {
        this.drop = drop;
    }

    @Override
    public void run() {
        String[] foods = {"🍉🍉🍉", "🍎🍎🍎", "🍔🍔🍔", "🥮🥮🥮", "🧁🧁🧁"};
        for (int i = 0; i < foods.length; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {}

            // 将生产的东西传递给消费者
            System.out.printf("--- 生产者 生产了：%s%n", foods[i]);
            drop.add(foods[i]);
        }

        // 告诉消费者，生产完毕！
        drop.add(null);
    }
}
