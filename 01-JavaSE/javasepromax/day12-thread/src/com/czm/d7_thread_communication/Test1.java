package com.czm.d7_thread_communication;

public class Test1 {
    /*
     1、线程通信
        当多个线程共同操作共享的资源时，线程间通过某种方式互相告知自己的状态，以相互协调，并避免无效的资源争夺。

     2、线程通信的常见模型(生产者与消费者模型)
        a、生产者线程负责生产数据
        b、消费者线程负责消费生产者生产的数据。

        注意：生产者生产完数据应该等待自己，通知消费者消费；消费者消费完数据也应该等待自己，再通知生产者生产!

     3、案例：
        有3个厨师做包子，每个厨子每次只能做好一个放在桌子上；2个人吃包子，每人每次只能拿一个吃。

     4、Object 类的等待和唤醒方法：
        void wait()，让当前线程等待并释放所占锁，直到另一个线程调用 notify() 方法或 notifyA11()方法
        void notifyAll()，唤醒正在等待的单个线程
        void notifyAll()，唤醒正在等待的所有线程
        注意：上述方法应该使用当前同步锁对象进行调用。

     */
    public static void main(String[] args) {

        // 1、竞争一个桌子
        Desk desk = new Desk("abc");

        // 5个竞争一把锁！！！

        // 2、创建2个消费者
        new ConsumerThread(desk, "吃货1").start();
        new ConsumerThread(desk, "吃货2").start();

        // 3、创建3个生产者
        new MakeThread(desk, "粤师傅").start();
        new MakeThread(desk, "湘师傅").start();
        new MakeThread(desk, "川师傅").start();

    }
}
