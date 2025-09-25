package com.czm.d4_notify;

/**
 * 传递数据的对象
 */
public class Drop {
    private String food;
    // empty 为 true，表示消费者需要等待生产者生产
    // empty 为 false，表示生产者需要等待消费者消费完
    private Boolean empty = true;

    /**
     * 获取生产的东西
     * 此方法在消费者线程中执行
     */
    public synchronized String get() {
        while (empty) {     // 此处用 while 是为了避免 wait() 时 抛出异常
            try {
                // wait() 执行后，会释放 drop 对象的内部锁，然后进入 WAITING 状态。这就导致其它线程可以拿到内部锁，继续执行
                wait(); // 消费者获取东西，先等待生产者生产
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        empty = true;
        notifyAll();
        return food;
    }

    /**
     * 生产了一个东西
     * 此方法在生产者线程中执行
     */
    public synchronized void add(String food) {
        while (!empty) {
            try {
                wait();     // 等待 消费者 可以接收生产的东西
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        empty = false;
        this.food = food;
        notifyAll();    // 通知所有的 wait，生产了东西
    }


    /*
     1、解析：
        synchronized 的作用，让 生产者 add 生产时获取到 drop 对象的内部锁；消费者 get 消费时获取到 drop 对象的内部锁。
    */
}
