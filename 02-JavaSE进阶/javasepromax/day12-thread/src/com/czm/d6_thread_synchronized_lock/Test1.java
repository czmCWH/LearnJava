package com.czm.d6_thread_synchronized_lock;

public class Test1 {
    /*
     1、Lock 锁
        Lock 锁是 jdk5 开始提供的一个新的锁定操作，通过它可以创建出锁对象进行加锁和解锁，更灵活、更方便、更强大。
        Lock 是接口，不能直接实例化，可以采用它的实现类 ReentrantLock 来构建 Lock锁 对象。

     2、ReentrantLock 的方法

     */
    public static void main(String[] args) {
        // 1、创建一个账号对象
        Account abc = new Account("abc", 1000);

        // 2、创建2个线程代表2个人取钱
        new DrawThread("小明", abc).start();
        new DrawThread("小红", abc).start();

    }
}
