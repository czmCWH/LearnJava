package com.czm.d1_thread_create;

public class Test3 {
    /*
     1、多线程的创建方法二：实现 Runnable 接口的匿名内部类写法
     2、
     */
    public static void main(String[] args) {
        // 完整写法
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    System.out.println("1子线程输出 = " + i);
                }
            }
        };
        Thread t = new Thread(r);
        t.start();


        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 3; i++) {
                    System.out.println("2子线程输出 = " + i);
                }
            }
        }).start();

        // 使用 Lambda 表示简化
        new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                System.out.println("3子线程输出 = " + i);
            }
        }).start();
    }
}
