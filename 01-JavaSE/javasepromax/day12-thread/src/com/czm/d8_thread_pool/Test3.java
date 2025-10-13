package com.czm.d8_thread_pool;

import java.util.concurrent.*;

public class Test3 {
    /*
     1、创建线程池方式二：Executors
        Executors 是一个线程池的工具类，提供了很多静态方法用于返回不同特点的线程池对象。
        注意：Executors 的这些方法的底层，都是通过线程池的实现类 ThreadPoolExecutor 创建的线程池对象

     2、Executors 使用可能存在的陷阱
        大型并发系统环境中使用 Executors 如果不注意可能会出现系统风险。
        OOM 内存溢出！

        Executors.newFixedThreadPool，创建固定线程数量的线程池，如果某个线程因为执行异常而结束，那么线程池会补充一个新线程替代它。
        Executors.newSingleThreadExecutor，创建只有一个线程的线程池对象，如果该线程出现异常而结束，那么线程池会补充一个新线程。
        Executors.newCachedThreadPool，线程数量随着任务增加而增加，如果线程任务执行完毕且空闲了60s则会被回收掉。
        Executors.newScheduledThreadPool，创建一个线程池，可以实现在给定的延迟后运行任务，或者定期执行任务，

     */
    public static void main(String[] args) {
        // 1、创建线程池对象
        ExecutorService pool = Executors.newFixedThreadPool(3);

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
