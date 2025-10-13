package com.czm.d3_collection_traversal_x;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.function.Consumer;

public class Test1 {
    /*
     Collection 单列集合的常用方法

     1、迭代器遍历集合
        迭代器是用来遍历集合的专用方式。Java 中迭代器的代表是 Iterator。
        注意：数组没有迭代器，因为数组没有索引。
        获取迭代器的方法：
            iterator()：返回集合中的迭代器对象，该迭代器对象默认指向数组的第一个元素。

     2、增强 for 遍历集合
        增强 for 可以用来遍历集合或者数组。
            for (元素的数据类型 变量名: 数组或者集合) {
            }
        ⚠️：增强for遍历集合，本质就是迭代器遍历集合的简化写法。

     3、Lambda表达式遍历集合
        得益于 JDK 8 开始的新技术 Lambda表达式，提供了一种更简单、更直接的方式来遍历集合。
        需要使用 Collection 的如下方法来完成:
            forEach(Consumer<? super T> action)
        ⚠️：Lambda表达式遍历集合 本质也是基于迭代器遍历集合。

     */
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("赵敏");
        list.add("张无忌");
        list.add("谢逊");

        // 1、获取集合对象的迭代器对象
        Iterator<String> iterator = list.iterator();

        // 2、获取迭代器数据
        // next() 获取当前位置的元素，并同时将迭代器对象指向下一个元素处。
//        System.out.println(iterator.next());    // 取出第1个元素
//        System.out.println(iterator.next());    // 取出第2个元素
//        System.out.println(iterator.next());    // 取出第2个元素
//        System.out.println(iterator.next());  // 超出元素，报异常：NoSuchElementException

        System.out.println("--- 方式1：使用循环改进");
        while (iterator.hasNext()) {    // 询问当前位置是否有元素存在，存在返回true,不存在返回false
            System.out.println(iterator.next());
        }

        System.out.println("--- 方式2：使用增强for遍历");
        for(String str : list) {
            System.out.println(str);
        }

        System.out.println("--- 方式2：Lambda表达式遍历集合");

        list.forEach(new Consumer<String>() {   // 匿名内部类对象
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        System.out.println("--- 简化1：");
        list.forEach(s -> System.out.println(s));

        System.out.println("--- 简化2：");
        list.forEach(System.out::println);

    }
}
