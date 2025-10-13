package com.czm.d1_thread;

/**
 * 继承 Thread 实现自定义线程
 */
public class MusicThread extends Thread {
    // 重写 run 方法编写开启线程中所需执行的任务
    @Override
    public void run() {
        System.out.println("--- 自定义 Thread 任务执行的位置：" + Thread.currentThread());
    }
}
