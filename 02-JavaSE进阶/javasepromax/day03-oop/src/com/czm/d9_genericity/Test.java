package com.czm.d9_genericity;

import java.util.ArrayList;

public class Test {
    /*
     1、泛型
        定义类、接口、方法时，同时声明了一个或者多个类型变量(如：<E>)，称为泛型类。
        泛型接口，泛型方法、它们统称为泛型。

     2、作用
        泛型提供了在编译阶段约束所能操作的数据类型，并自动进行检查的能力!
        这样可以避免强制类型转换，及其可能出现的异常。

     3、泛型的本质
        把具体的数据类型作为参数传给类型变量（如：E、T、K、V）。

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
        ArrayList<String> list = new ArrayList<>(); // JDK 1.7开始后面的类型可以不写
        list.add("a");
        list.add("b");
        list.add("c");
        String l1 = list.get(1);
        System.out.println(l1);

    }
}
