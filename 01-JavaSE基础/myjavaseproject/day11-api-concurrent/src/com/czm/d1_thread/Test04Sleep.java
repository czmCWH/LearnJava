package com.czm.d1_thread;

public class Test04Sleep {
    /*
      1、线程的 sleep、interrupt 方法
       可以通过 Thread.sleep 方法暂停当前线程，进入 WAITING 状态。
       在暂停期间，若调用线程对象的 interrupt 方法中断线程，会抛出 java.lang.InterruptedException，并且线程会继续往后执行。

     */
    public static void main(String[] args) {

        testSleep();

//        testInterrupt();

    }

    static void testSleep() {
        // Thread.sleep 让当前线程睡眠 2 秒钟再执行
        System.out.println(1);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(2);
    }

    static void testInterrupt() {
        Thread thread = new Thread(() -> {
            System.out.println(1);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(2);
        });
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        System.out.println(3);

        // interrupt，干扰、扰乱；调用此方法后，打印3后，2 会立即被打印；
        thread.interrupt();
    }
}
