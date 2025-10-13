package com.czm.d1_thread_create;

import java.util.concurrent.Callable;

// 定义一个类实现 Callable 接口，封装要做的事情，和要返回的数据
public class MyCallable implements Callable<String> {
    private int num;

    public MyCallable(int num) {
        this.num = num;
    }

    // 重写 call 方法，声明任务和返回结果
    @Override
    public String call() throws Exception {
        int sum = 0;
        for (int i = 1; i <= num; i++) {
            sum += i;
        }
        return "子线程执行1~" + num + "的和 = " + sum;
    }
}
