package com.czm.d8_thread_pool;

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
        return Thread.currentThread().getName() + "求1到" + num + "的和 = " + sum;
    }
}
