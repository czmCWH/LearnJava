package com.czm.d3_annotation;

import java.lang.reflect.Method;

public class Test04 {
    /*
     1、注解的应用场景
      案例：使用注解开发一个简易版的 JUnit 框架
        定义若干个方法，只要加了 MyTest 注解，就会触发方法执行！

      分析：
        定义一个自定义注解 MyTest，只能注解方法，存活范围是一直都在；
        定义若干个方法，部分方法加上@MyTest注解修饰，部分方法不加；
        模拟一个 iunit 程序，可以触发加了 @MyTest 注解的方法执行；

     2、总结⚠️⚠️⚠️
      注解能控制方法是否执行！
      注解的属性值，控制方法如何执行！

     */

    public static void main(String[] args) throws Exception {

        // 如下模拟自定义 MyTest 的应用场景，程序运行时，只有 test1、test3 执行，而 test2 不会执行

        Test04 obj = new Test04();

        // 1、获取类对象
        Class cls = Test04.class;

        // 2、获取所有方法
//        Method[] methods = cls.getDeclaredMethods();    // 只能有 public 方法
        Method[] methods = cls.getMethods();    // 获取所有方法

        // 3、遍历所有方法
        for (Method method : methods) {
            // 4、判断方法是有 MyTest 注解，如果有注解 则执行
            if (method.isAnnotationPresent(MyTest.class)) {

                // 5、获取方法对象上的注解
                MyTest annotation = method.getDeclaredAnnotation(MyTest.class);
                // 获取注解的 count 属性值
                int count = annotation.count();

                for (int i = 0; i < count; i++) {
                    // 6、如果有注解 则执行
//                method.setAccessible(true);   // 暴力反射
                    method.invoke(obj);
                }

            }
        }

    }

    // 测试方法的要求：public、无参、无返回值
    // 因为便于使用 @Test 注解时，能直接运行测试方法！
    @MyTest(count = 2)
    public void test1() {
        System.out.println("--- test1");
    }

    public void test2() {
        System.out.println("--- test2");
    }

    @MyTest(count = 5)
    public void test3() {
        System.out.println("--- test3");
    }


}
