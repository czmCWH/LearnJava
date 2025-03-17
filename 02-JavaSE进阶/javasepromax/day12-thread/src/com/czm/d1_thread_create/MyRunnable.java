package com.czm.d1_thread_create;

// 定义一个任务类实现 Runnable 接口
public class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println("子线程输出 = " + i);
        }
    }
}
