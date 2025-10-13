package com.czm.d3_annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


/**
 * 使用 @Retention 元注解，注解自定义注解来声明注解的存活周期
 */
@Retention(RetentionPolicy.RUNTIME)     // RUNTIME，表示注解的保留策略为 编译器运行时（一直存活！）
public @interface MyRetentionAnnotation {

}
