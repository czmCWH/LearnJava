package com.czm.d4_notify;

public class Test01 {

    /*
      1、线程间通信
       可以使用 Object.wait、Object.notify、Object.notifyAll 方法实现线程之间的通信。

       若想在线程 A 中成功调用 obj.wait、obj.notify、obj.notifyAll 方法。线程A必须要持有 obj 的内部锁。

       obj.wait：释放 obj 的内部锁，当前线程进入 WAITING 或 TIMED_WAITING 状态。
       Object.notifyAll：唤醒所有因为 obj.wait 进入 WAITING 或 TIMED_WAITING 状态的线程。
       Object.notify：随机唤醒 1 个因为 obj.wait 进入 WAITING 或 TIMED_WAITING 状态的线程。

      2、案例：消费者\生产者
        消费者调用 wait、生产者调用 notify 用的必须是同一个对象。
        消费者\生产者 必须要拥有 obj 的内部锁。

        实现逻辑：
          消费者\生产者 相互等待；
          消费者等待生产者生产；生产者等待消费者消费结束；

     */

    public static void main(String[] args) {

        Drop drop = new Drop();
        // Producer 在一个线程上生产内容后存入 drop
        (new Thread(new Producer(drop))).start();
        // Consumer 在一个线程上，从 drop 中获取内容
        (new Thread(new Consumer(drop))).start();

    }
}
