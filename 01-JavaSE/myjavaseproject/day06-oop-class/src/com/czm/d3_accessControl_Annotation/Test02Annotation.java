package com.czm.d3_accessControl_Annotation;

import com.czm.d2_oop_inherit.Student;

public class Test02Annotation {
    /*
      1、Annotation 注解
       java 中有很多注解，开发者可以自定义注解，常见的注解有：
        @Override，告诉编译器这是一个重写后的方法
        @SuppressWarnings("警告类别")，让编译器不生成警告信息，使用如下：
            @SuppressWarnings("unused")
            @SuppressWarnings({"rawtypes", "unused"})

      2、
     */

    public static void main(String[] args) {

        @SuppressWarnings("unused")
        Student stu = new Student();
    }
}
