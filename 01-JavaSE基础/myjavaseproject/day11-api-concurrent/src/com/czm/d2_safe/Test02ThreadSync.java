package com.czm.d2_safe;

public class Test02ThreadSync {

    /*
      1、线程同步 - 同步语句
       synchronized (obj) {
         // 只有获取 obj 的内部锁，才能执行 同步语句中的代码！

         // obj 通常使用 this，保证每一个线程只能从当前对象获取内部锁；
         // 如果 obj 值为 new Object();，则线程每次向不同的对象获取内部锁，因此无法实现线程同步；
         // 如果 obj 值为 "abc" 字符串字面量对象，此对象也是唯一的，存储在字符串常量池，因此可以保证线程同步。
       }
       原理⚠️：
        每个对象都有一个与它相关的 内部锁（intrinsic lock）或者叫 监视器锁（monitor lock）。
        第一个执行到 [同步语句] 的线程可以获得 obj 的内部锁，在执行完同步语句中的代码后释放此锁。
        只要一个线程持有了内部锁，那么其它线程在同一时刻将无法再获得此锁。当其它线程试图获取此锁时，将会进入 [BLOCKED] 状态。

       注意：
        多个线程访问同一个 synchronized (obj) 语句时，obj 必须是同一个对象，才能起到同步的作用。

      2、线程同步 - 同步方法
        public synchronized boolean saleTicket() { }
        public static synchronized boolean saleTicket() { }

        注意：synchronized 不能修饰构造方法！

        原理：同步方法 本质 是同步语句 锁住方法内所有代码
          实例方法等价于：synchronized (this) { }
          静态方法等价于：synchronized (类对象) { }

          每一个类都有一个类对象，每个类的类对象只有一份内存。获取类对象有如下2种方式：
             Class cls = Class.forName("com.czm.d6_safe.Station02");
             Class cls = Station02.class;

      3、总结
        同步语句 比 同步方法 更灵活一点，同步语句可以精确控制需要加锁的代码范围；
        使用了线程同步技术后，虽然解决了线程安全问题，但是降低了程序的执行效率。所以在真正有必要的时候，才使用线程同步技术。

     */

    public static void main(String[] args) {

        Station02 s = new Station02();

        for (int i = 0; i < 4; i++) {
            Thread thread = new Thread(s);
            thread.start();
        }
    }
}
