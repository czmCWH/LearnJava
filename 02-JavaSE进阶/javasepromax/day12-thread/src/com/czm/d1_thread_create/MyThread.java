package com.czm.d1_thread_create;

public class MyThread extends Thread {

    public MyThread(String name) {
        super(name);    // 设置线程名称
    }

    // run 方法中声明线程需要做的事情
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " 子线程输出 = " + i);
        }
    }
}
