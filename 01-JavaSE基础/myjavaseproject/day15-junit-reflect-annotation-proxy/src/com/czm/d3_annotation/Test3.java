package com.czm.d3_annotation;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Test3 {

    /*
     1、什么是注解的解析?
        就是判断类上、方法上、成员变量上是否存在注解，并把注解里的内容给解析出来。
     2、如何解析注解?
        指导思想：要解析谁上面的注解，就应该先拿到谁。
	    比如要解析类上面的注解，则应该先获取该类的Class对象，再通过Class对象解析其上面的注解。
	    比如要解析成员方法上的注解，则应该获取到该成员方法的 Method对象，再通过 Method对象 解析其上面的注解。

        注意：Class、Method、Field、Constructor、都实现了 AnnotatedElement 接口，它们都拥有解析注解的能力。
     */

    // 定义的单元测试方法，必须是公开的、无参数、无返回值；
    @Test
    public void parseClass() throws Exception {

        System.out.println("--- 1、解析类上面的注解 ---");

        Class cls = MyUseDemo.class;

        // 1、判断类对象上是否存在某个注解
        if (cls.isAnnotationPresent(MyUseAnnotation.class)) {
            // 2、得到注解对象
            MyUseAnnotation myAnnon = (MyUseAnnotation) cls.getDeclaredAnnotation(MyUseAnnotation.class);
            System.out.println("value = " + myAnnon.value());
            System.out.println("aaa = " + myAnnon.aaa());
            System.out.println("bbb = " + Arrays.toString(myAnnon.bbb()));
        }
    }

    @Test
    public void parseMethod() throws Exception {
        Class c = MyUseDemo.class;
        Method method = c.getDeclaredMethod("test");
        // 1、判断方法对象上是否存在某个注解
        if (c.isAnnotationPresent(MyUseAnnotation.class)) {
            // 2、得到方法对象上的注解对象
            MyUseAnnotation myAnnon = (MyUseAnnotation) method.getDeclaredAnnotation(MyUseAnnotation.class);
            System.out.println("value = " + myAnnon.value());
            System.out.println("aaa = " + myAnnon.aaa());
            System.out.println("bbb = " + Arrays.toString(myAnnon.bbb()));
        }
    }
}
