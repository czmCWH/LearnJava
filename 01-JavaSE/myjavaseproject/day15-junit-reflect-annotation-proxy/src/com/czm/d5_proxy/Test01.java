package com.czm.d5_proxy;

import java.util.Arrays;

public class Test01 {
    /*
     1、动态代理的好处
      动态代理增强被代理对象的功能！

     2、动态代理应用场景
      案例：某系统有一个用户管理类，包含用户登录，删除用户，查询用户等功能，系统要求统计每个功能的执行耗时情况，以便后期观察程序性能。
      需求：系统中这些模块已经开发好了，要求观察该模块的代码，并找出存在的问题进行改造。

     Spring 框架
      Spring，可以理解为一个 动态代理 工厂，可以管理所有的 Java 对象，增强管理对象的功能。

     AOP 切面编程思想
      使用 动态代理 实现 AOP！

     */

    public static void main(String[] args) throws Exception {

        // 1、创建用户业务对象
        UserService userService = new UserServiceImpl();

        // 2、调用用户业务的功能
        userService.login("admin", "123456");

        userService.deleteUsers();

        String[] names = userService.selectUsers();
        System.out.println("查询到的用户是:"+ Arrays.toString(names));




        System.out.println("===================");

        // --- 使用动态代理实现 统计方法执行耗时
        UserService proxy = ProxyUtil.createProxy(new UserServiceImpl2());

        // 2、调用用户业务的功能
        proxy.login("admin", "123456");

        proxy.deleteUsers();

        String[] names2 = proxy.selectUsers();
        System.out.println("查询到的用户是:"+ Arrays.toString(names2));

    }
}
