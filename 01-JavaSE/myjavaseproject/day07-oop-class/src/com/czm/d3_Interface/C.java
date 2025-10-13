package com.czm.d3_Interface;

/**
 * 接口的静态方法与继承
 */
public interface C extends A, B {
    static void doSomething() {
        System.out.println("C do");
    }
}
