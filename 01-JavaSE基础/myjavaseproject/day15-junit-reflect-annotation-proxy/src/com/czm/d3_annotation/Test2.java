package com.czm.d3_annotation;

public class Test2 {
    /*
     1、元注解
        元注解，是指修饰注解的注解。常用的元注解，例如：@Target、@Retention
        使用元注解语法：
            @Retention(RetentionPolicy.RUNTIME)
            @Target(ElementType.METHOD)
            public @interface 自定义注解名称 {
                // 注解内容
            }

     2、@Target，作用是声明被修饰的注释只能在哪些位置使用。
        @Target(ElementType.TYPE)
            ElementType.TYPE：类或接口；
            ElementType.FIELD：成员变量；
            ElementType.METHOD：成员方法；
            ElementType.PARAMETER：方法参数；
            ElementType.CONSTRUCTOR：构造器；
            ElementType.VARIABLE：局部变量；

     3、@Retention，作用是声明注解的保留周期
            RetentionPolicy.SOURCE：仅编译期，即只作用在源码阶段，字节码文件中不存在；
            RetentionPolicy.CLASS（默认值）：仅class文件，即保留到字节码文件阶段，运行阶段不存在；
            RetentionPolicy.RUNTIME（常用）：运行期，即一直保留到运行阶段；

     */
}
