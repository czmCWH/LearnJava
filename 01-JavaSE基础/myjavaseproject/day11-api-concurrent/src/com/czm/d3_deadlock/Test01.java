package com.czm.d3_deadlock;

public class Test01 {

    /*
      1、死锁（Deadlock）
       死锁，是指2个或多个线程永远阻塞，永远相互等待 对方线程使用的锁。

      2、
     */

    public static void main(String[] args) {

        // 死锁案例2：
        Person p1 = new Person("张三");
        Person p2 = new Person("翠花");

        /*
         p1 给 p2 打招呼，p2 反应微笑
          [张三] hello to [翠花]
          [翠花] smile to [张三]
         */
        p1.hello(p2);

        /*
         如下，p1 和 p2 同时 hello 后，他们 simle 会同时等待自己的同步锁 做出微笑反应，导致死锁。
         */
        new Thread(() -> {  // 此时线程1拿到 张三的锁
            p1.hello(p2);   // 在 hello 中让 翠花 simle，线程1需要拿到 翠花的锁
        }).start();

        new Thread(() -> {  // 此时线程2拿到 翠花的锁
            p2.hello(p1);   // 在 hello 中让 张三 simle，线程2需要拿到 张三的锁
        }).start();

    }

    // 死锁案例1：两个线程相互等待对方使用对象的锁，造成死锁！
    static void example01() {
        new Thread(() -> {
            synchronized ("1") {
                System.out.println("1 - 1");
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized ("2") {    // 等待 下面 Thread 的 "2" 对象的内部锁，此时不会释放 "1" 对象的内部锁。
                    System.out.println("1 - 2");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized ("2") {
                System.out.println("2 - 1");
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized ("1") {    // 等待 上面 Thread 的 "1" 对象的内部锁，此时不会释放 "2" 对象的内部锁。
                    System.out.println("2 - 2");
                }
            }
        }).start();
    }
}
