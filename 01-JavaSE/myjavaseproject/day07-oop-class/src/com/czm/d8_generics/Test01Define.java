package com.czm.d8_generics;

import com.czm.d8_generics.demo.Student;

import java.util.ArrayList;

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

      定义泛型类语法：
        修饰符 class 类名<类型参数1,类型参数2... > {
            // 类的实现
        }
      定义泛型接口语法：
        修饰符 interface 接口名<类型参数1, 类型参数2...> {

        }

      3、
     */

    public static void main(String[] args) {
        // 1、如果不使用泛型
        ArrayList list1 = new ArrayList();
        list1.add(1);
        list1.add(false);
        list1.add("你好");
        // 不使用泛型，需要类型检查
        if (list1.get(1) instanceof Boolean) {
            System.out.println((Boolean)list1.get(1));
        }

        // 2、使用泛型
        ArrayList<String> list = new ArrayList<> (); // JDK 1.7开始后面的类型可以不写
        list.add("a");
        list.add("b");
        list.add("c");
        String l1 = list.get(1);
        System.out.println(l1);

        // 3、定一个泛型类，并初始化
        Student<Integer, Double> s1 = new Student<>(12,90.0);
        Student<String, Integer> s2 = new Student<>("优秀学生", 100);
    }
}
