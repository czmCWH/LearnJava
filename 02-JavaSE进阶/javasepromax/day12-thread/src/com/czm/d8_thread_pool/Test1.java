package com.czm.d8_thread_pool;

import java.util.concurrent.*;

public class Test1 {
    /*
     1、使用 ThreadPoolExecutor 创建线程池对象，执行 Runnable 任务

     2、ThreadPoolExecutor 的新任务拒绝策略
        ThreadPoolExecutor.AbortPolicy，丢弃任务并抛出 RejectedExecutionException 异常。
        ThreadPoolExecutor.DiscardPolicy，丢弃任务，但是不抛出异常 这是不推荐的做法。
        ThreadPoolExecutor.DiscardOldestPolicy，抛弃队列中等待最久的任务 然后把当前任务加入队列中。
        ThreadPoolExecutor.CallerRunsPolicy，由主线程负责调用任务的 run() 方法从而绕过线程池直接执行。

     */
    public static void main(String[] args) {
        /*
        1、创建线程池对象
            参数一: corePoolSize，指定线程池的核心线程的数量。          --- 指定正式工3人
            参数二: maximumPoolSize：指定线程池的最大线程数量。         --- 员工最多5人    临时工可以有2人
            参数三: keepAliveTime：指定临时线程的存活时间。            --- 临时工空闲多久被开除
            参数四: unit：指定临时线程存活的时间单位(秒、分、时、天)
            参数五: workQueue：指定线程池的任务队列。                  --- 客人排队的地方
            参数六: threadFactory：指定线程池的线程工厂，用于创建线程。   --- 负责照片员工的hr
            参数七: handler：指定线程池的任务拒绝策略(即：当线程都在忙，任务队列也满了的时候，新任务来了该怎么处理）。
         */
        ExecutorService pool = new ThreadPoolExecutor(3, 5, 1, TimeUnit.MINUTES, new ArrayBlockingQueue<>(3), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        // 2、处理任务
        Runnable target = new MyRunnable();
        pool.execute(target);   // 自动创建线程，并处理任务
        pool.execute(target);   // 自动创建线程，并处理任务
        pool.execute(target);   // 自动创建线程，并处理任务

        System.out.println("--- 默认3线程都在忙，新任务进入任务队列等待执行");

        pool.execute(target);   // 进入队列，复用线程
        pool.execute(target);   // 进入队列，复用线程
        pool.execute(target);   // 进入队列，复用线程

        System.out.println("--- 默认3线程都在忙，任务队列也满了，开始创建临时线程");

        pool.execute(target);   // 创建临时线程1执行
        pool.execute(target);   // 创建临时线程2执行

        System.out.println("--- 默认3线程都在忙，任务队列也满了，临时线程也满了，拒绝新任务");

        pool.execute(target);   // 此时最多的5个线程都在忙，任务队列也满了，开始执行任务决绝策略，抛出异常

        // 3、线程池不会死亡，需要手动关闭
//        pool.shutdownNow();     // 立即关闭线程池，不管任务是否执行完毕，若没执行完毕则返回没有执行完的任务
//        pool.shutdown();    // 等待全部任务完成才关闭
    }

}
