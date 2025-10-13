package com.czm.d2_assert;

public class Asserts {
    public static void test(boolean v) {
        if (v) return;
        // 此处抛出的异常立即被处理掉了，这是为了避免 某一个断言 抛出异常后，其后面的 断言 都不执行。
        // 因此采用抛出异常后，立即处理并打印函数调用栈提示，而且保证了后面的所有异常都执行成功。
        try {
            throw new IllegalArgumentException("条件不成立！");
        } catch (Exception e) {
//            e.printStackTrace();  // 打印异常出现的函数调用栈
            System.err.println(e.getStackTrace()[1]);   // 只打印触发异常的地方
        }
    }

    // 优化自定义断言逻辑
    public static void test2(boolean v) {
        if (v) return;
        System.err.println(new RuntimeException().getStackTrace()[1]);
    }
}
