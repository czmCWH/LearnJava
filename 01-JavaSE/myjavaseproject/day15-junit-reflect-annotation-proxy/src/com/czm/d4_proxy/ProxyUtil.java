package com.czm.d4_proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 代理工具类 - 专门负责创建代理对象并返回给别人使用
 */
public class ProxyUtil {

    /**
     *  创建 obj 对象的代理对象并返回给别人使用
     * @param obj
     * @return obj 的代理对象
     */
    public static StarService createProxy(Star obj) {
        /**
         * newProxyInstance 方法的参数：
         * 参数1，类加载器，用于执行哪个类加载器去加载生成的代理类。代理类加载后，才能创建代理对象。
         * 参数2，指定代理类需要实现的接口。一般为被代理对象实现的所有接口
         * 参数3，用来指定生成的代理对象要干什么事情
         */

        // ⚠️：一般 被代理类实现了哪些接口，代理类就应该实现哪些接口
//        Class[] cls = new Class[] {StarService.class};
        Class[] cls = obj.getClass().getInterfaces();

        StarService proxy = (StarService) Proxy.newProxyInstance(ProxyUtil.class.getClassLoader(), cls, new InvocationHandler() {
            /**
             * invoke 方法用于指定代理对象要做的事情
             * @param proxy 指当前代理对象本身。
             * @param method 接收到代理对象调用的方法。即，代理对象正在被代理的方法。
             * @param args 是指正在被代理的方法的参数。
             */
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 此方法中 声明代理对象要做的事情，如：
                // 1、代理对象先执行自己的事情
                // 2、在执行被代理的对象要做的事情

                String methodName = method.getName();
                if ("sing".equals(methodName)) {
                    System.out.println("--- 准备话筒，收款20万！");
                } else if ("dance".equals(methodName)) {
                    System.out.println("--- 准备场地，收款100万！");
                }

                // 触发真正的方法，即被代理对象的方法
                Object result = method.invoke(obj, args);

                return result;
            }
        });
        return proxy;
    }
}
