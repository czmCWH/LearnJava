package com.czm.anno;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义 Log 注解，作用标志作用
 */
@Retention(RetentionPolicy.RUNTIME) // 表示 注解存在 的生命周期
@Target(ElementType.METHOD)     // 注解作用的范围，在此设置为 注解作用在方法上
public @interface Log {
}
