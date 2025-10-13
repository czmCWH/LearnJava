package com.czm.d1_thread;

public class Test05Join {

    /*
      1、Thread 的 join、isAlive 方法
        A.join 方法：当前线程 等线程 A 执行完毕后，当前线程再继续执。可以传参指定最长等待时间。
        A.isAlive 方法：查看线程 A 是否还活着。
     */

    public static void main(String[] args) {
        // 如下所示：开启了新线程，1 和 2 会同时打印。
//        Thread thread = new Thread(() -> {
//            System.out.println(1);
//        });
//        thread.start();
//
//        System.out.println(2);


        Thread t1 = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {

            }
            System.out.println(1);
        });
        t1.start();
        // 此时在 main thread 里面调用新线程的 join 方法，2 必须等 1 执行完毕后才打印。
        try {
            t1.join();  // 表示当前线程等待 t1 执行完毕后再继续执行
//            thread.join(1000);  // 表示当前线程等待 t1 1秒后再继续执行
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(2);

        System.out.println(t1.isAlive());   // false

    }
}
