package com.czm.d5_proxy;

import com.czm.d4_proxy.StarService;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyUtil {
//    public static UserService createProxy(UserServiceImpl2 obj) {
//        UserService proxy = (UserService) Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
//            @Override
//            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
//                long start = System.currentTimeMillis();
//
//                // 真正调用业务对象被代理的方法
//                Object result = method.invoke(obj, args);
//
//                long end = System.currentTimeMillis();
//                System.out.println(method.getName() + "方法耗时："+(end-start)/1000.0+"秒");
//                return result;
//            }
//        });
//        return proxy;
//    }



    public static <T> T createProxy(T obj) {
        @SuppressWarnings("unchecked")
        T proxy = (T) Proxy.newProxyInstance(ProxyUtil.class.getClassLoader(), obj.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                long start = System.currentTimeMillis();

                // 调用被代理对象的方法
                Object result = method.invoke(obj, args);

                long end = System.currentTimeMillis();
                System.out.println(method.getName() + "方法耗时："+(end-start)/1000.0+"秒");
                return result;
            }
        });
        return proxy;
    }
}
