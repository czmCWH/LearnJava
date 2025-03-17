package com.czm.d1_thread_create;

import java.util.concurrent.FutureTask;

public class Test4 {
    /*
     1、方式一(继承 Thread 类) 和 方式二(现 Runnable 接口) 存在的问题?
        假如线程执行完毕后有一些数据需要返回，他们重写的run方法均不能直接返回结果。

     2、多线程的创建方式三：利用  JDK5.0 的 Callable接口、FutureTask类来实现。
        这种方式最大的优点:可以返回线程执行完毕后的结果。

        步骤1: 创建任务对象
            定义一个类实现 Callable 接口，重写 call 方法，封装要做的事情，和要返回的数据；
            把 Callable 类型的对象封装成 FutureTask (线程任务对象)；

        步骤2：把线程任务对象交给 Thread 对象。

        步骤3：调用 Thread 对象的 start 方法启动线程。

        步骤4：线程执行完毕后、通过FutureTask对象的的get方法去获取线程任务执行的结果

     3、多线程的创建方式三 的优缺点
        优点：线程任务类只是实现接口，可以继续继承类和实现接口，扩展性强;可以在线程执行完毕后去获取线程执行的结果。
        缺点：编码复杂一点。
     */
    public static void main(String[] args) {
        // 1、创建 Callable 任务对象
        MyCallable c = new MyCallable(100);

        // 2、把任务对象封装为 FutureTask 对象，它是一个 Runnable 对象
        FutureTask<String> f = new FutureTask<>(c);

        // 3、把未来任务对象交给 Thread 对象
        Thread t = new Thread(f);
        t.start();
        System.out.println("---任务1 执行");

        MyCallable c1 = new MyCallable(200);
        FutureTask<String> f1 = new FutureTask<>(c1);
        Thread t1 = new Thread(f1);
        t1.start();
        System.out.println("---任务2 执行");

        MyCallable c2 = new MyCallable(300);
        FutureTask<String> f2 = new FutureTask<>(c2);
        Thread t2 = new Thread(f2);
        t2.start();
        System.out.println("---任务3 执行");

        System.out.println("-------------------------------------");
        System.out.println("--- 获取执行结果：");
        try {
            // ⚠️⚠️⚠️ 如果子线程没执行完毕，会在这儿等待，所以一定能拿到执行结果
            // 获取线程执行ca11方法返回的结果。
            String res1 = f.get();
            System.out.println("--- 线程1结果 = " + res1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // 等待获取结果
            String res2 = f1.get();
            System.out.println("--- 线程2结果 = " + res2);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            // 如果第一个线程没执行完毕，会在这儿等待
            String res3 = f2.get();
            System.out.println("--- 线程3结果 = " + res3);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
