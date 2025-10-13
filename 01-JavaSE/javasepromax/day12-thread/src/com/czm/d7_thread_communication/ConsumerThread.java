package com.czm.d7_thread_communication;

// 消费者线程
public class ConsumerThread extends Thread {

    private Desk desk;

    public ConsumerThread(Desk desk, String name) {
        super(name);
        this.desk = desk;
    }

    @Override
    public void run() {
        // 消费者开始从桌子上拿包子吃
        while (true) {
            try {
                Thread.sleep(1000);
                desk.getFood();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
