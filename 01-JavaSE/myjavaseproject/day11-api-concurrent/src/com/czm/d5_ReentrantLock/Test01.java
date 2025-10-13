package com.czm.d5_ReentrantLock;

public class Test01 {

    /*
      1、ReentrantLock（可重入锁、递归锁）
        java.util.concurrent.locks.ReentrantLock，具有跟 同步语句、同步方法 一样的一些基本功能，但功能更加强大。

      2、什么是可重入？
        同一个线程可以重复获取同一个锁。其实 synchronized 也是可重入的。

        synchronized ("1") {  // 假设，线程A 获得 "1" 对象的内部锁
            synchronized ("1") {  // 可重入锁，即此时线程A 可以再次获得 "1" 对象的内部锁；不是可重入锁，此时线程A会等待 "1" 对象的内部锁，即使是它自己此时拥有此锁。
                System.out.println(123);
            }
        }

      3、lock、tryLock 方法
        a、ReentrantLock.lock：获取此锁。
          当前线程当调用 lock 方法时：
            如果此锁没有被另一个线程持有，则当前线程持有此锁，并且将锁的持有计数设置为1，并且此方法立即返回；
            如果此锁被另一个线程持有，则当前线程在获得锁之前将一直处于休眠状态，并且当前线程对锁的持有计数被设为1；
            如果当前线程已经持有此锁，则将当前线程对锁的持有计数加1，并且此方法立即返回；

          lock 与 unlock 是成对的，可以多次 lock 和 unlock。
          每次线程调用 unlock 时，对锁的持有计数就会减1。当线程对锁的持有计数减为0时，线程就会释放锁。

        b、ReentrantLock.tryLock：仅在调用时锁未被其它线程持有的情况下，才获取此锁。
          当前线程调用 tryLock 方法时：
            如果此锁没有被另一个线程持有，则将当前线程对锁的持有计数设为1，并且此方法立即返回true；
            如果当前线程已经持有此锁，则将锁的持有计数加1，并且此方法立即返回true；
            如果锁被另一个线程持有，则此方法立即返回 false；
          boolean flag = lock.tryLock();	// flag：true 表示锁未被其它线程占用，此线程会获取该锁；false：表示锁被其它线程拥有，但代码会继续向下执行。

      4、unlock、isLocked 方法
        a、unlock：尝试释放锁
          线程调用 unlock 方式时，
            如果当前线程持有此锁，则将持有计数减1；
            如果线程对锁的持有计数为0，则释放此锁；
            如果当前线程没有持有此锁，则抛出 java.lang.IllegalMonitorStateException。Monitor lock 内部锁、监视器锁。

        b、isLocked：查看此锁是否被任意线程持有

     */

    public static void main(String[] args) {

        Station s = new Station();

        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(s);
            thread.start();
        }




    }
}
