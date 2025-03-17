package com.czm.d7_thread_communication;

// 生产者线程
public class MakeThread extends Thread {

    private Desk desk;

    public MakeThread(Desk desk, String name) {
        super(name);
        this.desk = desk;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 开始做包子放到桌子上
                this.desk.putFood();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
