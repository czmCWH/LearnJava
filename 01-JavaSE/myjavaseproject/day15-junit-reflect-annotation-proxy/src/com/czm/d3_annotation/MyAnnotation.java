package com.czm.d3_annotation;

/**
 * 自定义注解
 */
public @interface MyAnnotation {
    String name();
    double age() default 18;
    String[] hobby();
}
