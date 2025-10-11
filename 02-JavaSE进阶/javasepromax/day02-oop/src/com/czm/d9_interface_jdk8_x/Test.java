package com.czm.d9_interface_jdk8_x;

public class Test {
    /*
     1、JDK8开始，接口新增了三种形式的方法:
        a、默认方法(实例方法)
            使用用default修饰，默认会被加上 public 修饰。
            注意：只能使用接口的 实现类对象 调用。

        b、私有方法
            必须用 private 修饰(JDK 9开始才支持)
            只能在当前接口内部的默认方法、私有方法中调用。

        c、类方法(静态方法)
            使用static修饰，默认会被加上public修饰。
            注意：只能用接口名来调用。

        ⚠️：基本上没什么用，一般源码会使用。

     2、JDK8开始，接口中为啥要新增这些方法?
        增强了接口的能力，更便于项目的扩展和维护
     */
    public static void main(String[] args) {
        Demo d = new Demo();
        d.run();

        A.doSome();
    }
}
