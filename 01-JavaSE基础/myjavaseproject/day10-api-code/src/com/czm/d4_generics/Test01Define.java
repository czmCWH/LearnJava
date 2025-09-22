package com.czm.d4_generics;

import com.czm.d4_generics.demo.Student;

public class Test01Define {

    /*
      1、泛型（Generics）
       从 Java 5 开始增加了泛型技术。泛型是指 将类型变为参数，提高代码复用率。

       建议的 类型参数 名称（或类型占位符名称）：
        T：Type
        E：Element
        K：Key
        N：Number
        V：Value
        S、U、V：2nd、3rd、4th types，第2、3、4个类型


      2、泛型类型（Generic Type）
      使用了泛型的类或接口。
      比如：
            java.util.Comparator
            java.lang.comparable

      3、
     */

    public static void main(String[] args) {
        // 1、定一个泛型类，并初始化
        Student<Integer, Double> s1 = new Student<>(12,90.0);
        Student<String, Integer> s2 = new Student<>("优秀学生", 100);
    }
}
