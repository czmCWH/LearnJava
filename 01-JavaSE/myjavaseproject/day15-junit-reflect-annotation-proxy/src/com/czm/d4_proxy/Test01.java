package com.czm.d4_proxy;

import java.lang.reflect.Proxy;

public class Test01 {

    /*
     1、动态代理 --- 设计模式
      被代理对象具备什么行为，代理对象也一样具备什么行为。
      代理对象并不会真正执行具体的行为，而是增强被代理对象的行为。真正具体的行为还是由被代理对象执行。
      Java 中通过 接口 来确保 被代理对象 与 代理对象 之间有相同的行为。

     2、如何为 Java 对象创建一个代理对象？
      java.lang.reflect.Proxy 类，提供了为对象产生代理对象的方法：

        public static Object newProxyInstance(ClassLoader loader, 用于指定用哪个类加载器，去加载生成的代理类
                                          Class<?>[] interfaces, 指定接口，这些接口用于指定生成的代理长什么，也就是有哪些方法
                                          InvocationHandler h)，用来指定生成的代理对象要干什么事情

     3、
     */

    public static void main(String[] args) {

        Star star = new Star("美女");

        // 为 star 创建一个专属的代理对象
        StarService proxy = ProxyUtil.createProxy(star);
        proxy.sing("《青花瓷》");
        System.out.println(proxy.dance());

    }

}
