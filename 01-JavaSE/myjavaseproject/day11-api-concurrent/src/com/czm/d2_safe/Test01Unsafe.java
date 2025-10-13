package com.czm.d2_safe;

public class Test01Unsafe {

    /*
      1、线程安全问题
       多个线程可能会共享（访问）同一个资源，比如：访问同一个对象、同一个变量、同一个文件。
       当多个线程访问同一块资源时，很容易引发数据错乱和数据安全问题，称为线程安全问题。

      2、什么情况下会出现线程安全问题？
       多个线程共享同一个资源；
       且至少有一个线程正在进行写的操作；

      3、线程安全问题解决方案 - 线程同步
       线程同步，有2种方式：
        同步语句（Synchronized Statement）
        同步方法（Synchronized Method）

       Thread.join 只是单方面的等待，无法达到通知的效果。

     */

    public static void main(String[] args) {

        // 线程安全案例：多个线程同时卖票

        // 初始化一个卖票任务，提供给3个线程执行，导致线程安全问题
        Station01 s = new Station01();

        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(s);
            thread.start();
        }

        /*
          执行结果：
           Thread-3 卖了1张票，还剩下：3 张
           Thread-3 卖了1张票，还剩下：2 张
           Thread-3 卖了1张票，还剩下：1 张
           Thread-3 卖了1张票，还剩下：0 张
           Thread-2 卖了1张票，还剩下：97 张
           Thread-1 卖了1张票，还剩下：98 张
           Thread-0 卖了1张票，还剩下：71 张
          为什么 Thread-3 卖完后，Thread-2、Thread-1、Thread-0 还在售卖，并且 ticket 值不对？
          因为 多个线程 同时去 读取 ticket 或者 写入 ticket 时，会导致操作相互覆盖，导致 ticket 值访问逻辑错乱。
         */

    }
}
