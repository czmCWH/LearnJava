package com.czm;

/**
 * 测试类：⚠️ ThreadLocal 存/取/删 操作在同一个线程中进行，不同线程之间的操作相互隔离，如下验证所示。
 */
public class ThreadLocalTest {
    private static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        threadLocal.set("Main Message");
        System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());    // main: Main Message

        // 创建一个新线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());    // Thread-0: null
                threadLocal.set("Sub Message");
                System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());    // Thread-0: Sub Message
            }
        }).start();

        threadLocal.remove();
        System.out.println(Thread.currentThread().getName() + ": " + threadLocal.get());    // main: null
    }
}
