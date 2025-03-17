package com.czm.d3_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})     // 定义注解使用的位置：类或接口、成员方法上。
@Retention(RetentionPolicy.RUNTIME)     // 定义注解的有效范围：一直到运行时。
public @interface MyUseAnnotation {
    String value();
    double aaa() default 100;
    String[] bbb();
}
