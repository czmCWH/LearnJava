package com.czm.anno;

import com.czm.enumeration.OperationType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义注解，用于 AOP 中实现公共字段自动填充
 */

@Retention(RetentionPolicy.RUNTIME) // 表示 注解存在 的生命周期
@Target(ElementType.METHOD)     // 注解作用的范围，在此设置为 注解作用在方法上
public @interface AutoFill {

    /**
     * 为自定义注解，添加一个属性，用于区分是 新增 or 修改 操作。
     * 该属性的值可以用 String 类型，这样会存在随意性。因此采用枚举类型来限定取值。
     * @return
     */
    OperationType value() default OperationType.INSERT;
}
