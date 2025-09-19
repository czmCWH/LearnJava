package com.czm.d2_assert;

public class Test01 {
    /*
      1、编写断言类
        断言类用于测试业务代码逻辑执行后，执行结果是否为预期结果，如果结果正确不做任何事情，如果错误则抛出异常提示。
        点击控制台的异常信息，可以定位到异常出现的位置。

        断言主要用于开发和测试阶段，生产环境建议禁用以避免性能开销。
        Java 开发主要使用 JUnit 框架的 Assert 类。
     */

    public static void main(String[] args) {
        Asserts.test(sum(10, 20) == 30);    // sum(10, 20) 执行后的预期结果是否为：30
        Asserts.test(sub(10, 20) == 10);    // sub(10, 20) 执行后的预期结果是否为：10
        Asserts.test(divide(10, 20) == 10);

        // 异常的优化
        Asserts.test2(sum(10, 20) == 30);
        Asserts.test2(sub(10, 20) == 10);
        Asserts.test2(divide(10, 20) == 10);
    }

    static int sum(int a, int b) {
        return a + b;
    }

    static int sub(int a, int b) {
        return a - b;
    }

    static int divide(int a, int b) {
        return a / b;
    }
}
