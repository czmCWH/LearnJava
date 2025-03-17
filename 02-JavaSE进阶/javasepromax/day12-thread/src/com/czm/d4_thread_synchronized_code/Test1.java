package com.czm.d4_thread_synchronized_code;

public class Test1 {
    /*
     1、线程同步的常见方案
	    加锁：每次只允许一个线程加锁，加锁后才能进入访问，访问完毕后自动解锁，然后其他线程才能再加锁进来。
	    加锁的方式：同步代码块、同步方法、Lock锁

     2、同步代码块
	    作用：把访问共享资源的核心代码给上锁，以此保证线程安全。
	    语法：
            synchronized(lockObject) {  // 获取锁
                // 访问共享资源的核心代码
            }   // 释放锁

    	原理：每次只允许一个线程加锁后进入，执行完毕后自动解锁，其他线程才可以进来执行。
    	同步锁注意事项：对于当前同时执行的线程来说，同步锁必须是同一把(同一个对象)，否则会出bug。

	 3、锁对象(lockObject) 使用规范
        锁对象(lockObject) 不要使用任意一个唯一对象，因为这会导致任何线程共用这个唯一对象，影响线程执行逻辑。
            a、通常使用 共享资源对象作为 锁对象(lockObject) ，对于实例方法建议使用 this 作为锁对象。
            b、对于静态方法建议使用字节码(类名.class)对象作为锁对象，因为静态方法是针对所有人使用。

     */
    public static void main(String[] args) {
        // 1、创建一个账号对象
        Account abc = new Account("abc", 1000);

        // 2、创建2个线程代表2个人取钱
        new DrawThread("小明", abc).start();
        new DrawThread("小红", abc).start();

    }
}
