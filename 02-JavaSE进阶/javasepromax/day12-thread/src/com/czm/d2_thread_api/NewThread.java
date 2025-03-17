package com.czm.d2_thread_api;

public class NewThread extends Thread {
    public NewThread() {}

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("子线程输出：" + i);
        }
    }
}
