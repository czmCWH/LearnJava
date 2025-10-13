package com.czm.d3_annotation_x;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

// 使用 @Target 元注解，来指定 MyTargetAnnotation 注解修饰的范围
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface MyTargetAnnotation {

}
