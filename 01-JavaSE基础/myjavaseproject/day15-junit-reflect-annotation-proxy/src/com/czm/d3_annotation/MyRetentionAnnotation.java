package com.czm.d3_annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


// 使用 元注解@Retention 注解自定义注解来声明注解的存活周期

@Retention(RetentionPolicy.RUNTIME)
public @interface MyRetentionAnnotation {

}
