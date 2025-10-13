package com.czm.d3_method_reference;

import java.util.Arrays;
import java.util.Comparator;

public class Test3 {
    /*
     1、特定类型方法的引用
        语法：类型::方法

     2、使用场景
        如果某个 Lambda表达式 里只是调用一个实例方法，并且前面参数列表中的第一个参数是作为方法的主调，
        后面的所有参数都是作为该实例方法的入参的，则此时就可以使用特定类型的方法引用。

     */
    public static void main(String[] args) {
        // 对如下字符串数组排序
        String[] names = {"liming", "zhangsan", "李明", "Tom", "张三", "lei", "Jan", "LiMing", "ZhangSan"};

        // Arrays.sort 默认是按照首字母排序，区分大小。
//        Arrays.sort(names);
//        System.out.println(Arrays.toString(names));

        // 不区分大小写来排序

//        Arrays.sort(names, new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                // 区分大小写比较
//                return o1.compareToIgnoreCase(o2);
//            }
//        });

//        Arrays.sort(names, (o1, o2) -> o1.compareToIgnoreCase(o2));
//        System.out.println(Arrays.toString(names));
        System.out.println("--- 使用 特定类型方法的引用 简化 Lamdba 表达式");
        Arrays.sort(names, String::compareToIgnoreCase);
        System.out.println(Arrays.toString(names));
    }


}
