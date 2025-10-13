package com.czm.d1_thread;

public class Test02Create {

    /*
      1、创建线程的几种方式
        方式1，使用 Thread 的 Runnable 函数式接口方式开启新线程；
        方式2，继承 Thread 重写 run 方法；

        如上2种方式创建的线程对象，启动新线程需注意：
            直接调用 Thread 的 run 方法并不能开启新线程；
            调用线程的 start 方法才能成功开启新线程；

        Thread 类实现了 Runnable 接口。

      private native void start0(); native 关键字，表示本地方法，即方法的实现是闭源的。

     */

    public static void main(String[] args) {
        System.out.println("--- main 方法所在的线程：" + Thread.currentThread());   // Thread[main,5,main]：[Name + Priority + GroupName]

        System.out.println("\n--- 1、使用 Runnable 函数式接口 创建新线程：");
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("--- 新创建线程的任务执行的位置：" + Thread.currentThread());   // Thread[线程666,10,main]
//            }
//        });
        // Lambda 表达式简写：
        Thread thread = new Thread(() -> {
            System.out.println("--- 新创建线程的任务执行的位置：" + Thread.currentThread());   // Thread[线程666,10,main]
        });
        thread.setName("线程666"); // 设置线程名
        thread.setPriority(10); // 设置线程优先级
        thread.start(); // 启动线程
        // 注意，不要通过调用 run 方法启动线程，Thread 对象执行 run 方法仅仅是在当前线程上执行此方法。
        // 调用 start 方法内部会调用 native void start0(); 方法来向 CPU 申请一个新线程，并在此线程中执行任务。
//        thread.run();

        System.out.println("\n--- 2、使用 自定义 Thread 创建新线程：");
        Thread thread2 = new MusicThread();
        thread2.start();
    }
}
