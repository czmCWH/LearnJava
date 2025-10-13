package com.czm.d2_thread_api;

public class Test2 {
    public static void main(String[] args) throws Exception {

        NewThread thread = new NewThread();
        thread.start();
        for (int i = 0; i < 10; i++) {
            System.out.println("main 线程输出 = " + i);
           if (i == 2) {
               thread.join();   // 让调用当前这个方法的线程先执行完!
           }
        }

    }
}
