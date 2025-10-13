package com.czm.d6_threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test01 {

    /*
      1、线程池（Thread Pool）
       线程对象占用大量内存，在大型应用程序中，频繁地创建和销毁线程对象会产生大量内存管理开销（PC寄存器、Java虚拟机栈、本地方法栈）。
       使用 线程池 可以最大程度地减少 线程 创建、销毁所带来的开销。

      2、线程池 由 工作线程（Worker Thread）组成
       普通线程：执行完一个任务后，生命周期就结束了。
       工作线程：可以执行多个任务（任务没来就一直等，任务来了就干活）。

       线程池执行任务的原理：
          先将任务添加到队列（queue）中，再从队列中取出任务提交到线程池中，由线程池把任务分配给对应的工作线程。

      3、固定线程池（Fixed Thread Pool）
       常用的 线程池 类型是 固定线程池（Fixed Thread Pool），即具有固定数量的正在运行的线程。

     */
    public static void main(String[] args) {

        // 创建拥有3条工作线程的固定线程池
        ExecutorService pool =  Executors.newFixedThreadPool(3);
        // 添加执行任务
        pool.execute(() -> {
            System.out.println("--- 1 = " + Thread.currentThread());
        });
        pool.execute(() -> {
            System.out.println("--- 2 = " + Thread.currentThread());
        });
        pool.execute(() -> {
            System.out.println("--- 3 = " + Thread.currentThread());
        });
        pool.execute(() -> {
            System.out.println("--- 4 = " + Thread.currentThread());
        });
        pool.execute(() -> {
            System.out.println("--- 5 = " + Thread.currentThread());
        });

        // 关闭线程池
        pool.shutdown();
    }
}
