package com.czm.d3_annotation;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.Arrays;

public class Test03 {

    /*
     1、什么是注解的解析？
        就是判断类上、方法上、成员变量上是否存在注解，并把注解里的内容给解析出来。

     2、如何解析注解？
        指导思想：要解析谁上面的注解，就应该先拿到谁。
	    比如，要解析类上面的注解，则应该先获取该类的Class对象，再通过Class对象解析其上面的注解。
	    比如，要解析成员方法上的注解，则应该获取到该成员方法的 Method对象，再通过 Method对象 解析其上面的注解。

        Class、Method、Field、Constructor、都实现了 AnnotatedElement 接口，它们都拥有解析注解的能力。
     */

    /**
     * 1、解析类上面的注解
     */
    @Test
    public void parseClass() throws Exception {
        // 0、获取类对象
        Class cls = MyUseDemo.class;

        // 1、判断类对象上是否陈列了 MyUseAnnotation 注解
        if (cls.isAnnotationPresent(MyUseAnnotation.class)) {
            // 2、获取指定的注解对象
            MyUseAnnotation myAnnon = (MyUseAnnotation) cls.getDeclaredAnnotation(MyUseAnnotation.class);

            // 3、获取注解属性值
            String value = myAnnon.value();
            double aaa = myAnnon.aaa();
            String[] bbb = myAnnon.bbb();

            System.out.println("value = " + value);
            System.out.println("aaa = " + aaa);
            System.out.println("bbb = " + Arrays.toString(bbb));
        }
    }

    /**
     * 2、解析方法上的注解
     */
    @Test
    public void parseMethod() throws Exception {
        // 1、获取类对象
        Class c = MyUseDemo.class;

        // 2、获取方法对象
        Method method = c.getDeclaredMethod("test");

        // 3、判断方法对象上是否存在 MyUseAnnotation 注解
        if (c.isAnnotationPresent(MyUseAnnotation.class)) {
            // 4、获取方法对象上指定的注解
            MyUseAnnotation myAnnon = method.getDeclaredAnnotation(MyUseAnnotation.class);

            // 5、获取注解属性值
            String value = myAnnon.value();
            double aaa = myAnnon.aaa();
            String[] bbb = myAnnon.bbb();

            System.out.println("value = " + value);
            System.out.println("aaa = " + aaa);
            System.out.println("bbb = " + Arrays.toString(bbb));
        }
    }
}
