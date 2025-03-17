package com.czm.d8_thread_pool;

import java.util.concurrent.*;

public class Test2 {
    /*
     1、使用 ThreadPoolExecutor 创建线程池对象，执行 callable 任务，返回未来任务对象，用于获取线程返回的结果。
     */
    public static void main(String[] args) {
        // 1、创建线程池对象
        ExecutorService pool = new ThreadPoolExecutor(3, 5, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        // 2、处理任务
        Future<String> f1 = pool.submit(new MyCallable(100));
        Future<String> f2 = pool.submit(new MyCallable(200));
        Future<String> f3 = pool.submit(new MyCallable(300));

        // 3、获取任务处理结果
        try {
            String s = f1.get();
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String s = f2.get();
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            String s = f3.get();
            System.out.println(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
