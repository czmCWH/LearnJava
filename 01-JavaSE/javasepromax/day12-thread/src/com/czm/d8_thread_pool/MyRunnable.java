package com.czm.d8_thread_pool;

// 自定义任务对象，封装任务
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(Thread.currentThread().getName() + "输出:" + i);

            // 模拟线程被占用，使得线程池开启临时线程执行任务
            try {
                Thread.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
