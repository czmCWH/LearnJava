package com.czm.d3_annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 实现注解的应用场景！
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTest {
    /**
     * 控制 被注解的 方法执行几次
     * @return
     */
    int count() default 1;
}
