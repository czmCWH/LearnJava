package com.czm.d3_collection_traversal;

import java.util.ArrayList;
import java.util.Iterator;

public class Test3 {
    /*
    Collection 单列集合的常用方法

     1、集合的并发修改异常
        使用迭代器遍历集合时，又同时在删除集合中的数据，程序就会出现并发修改异常的错误。

     2、如何保证 遍历+删除 集合元素时不出bug
        方式一：使用迭代器遍历集合，但必须用迭代器自己的删除方法删除数据即可；
        方式二：如果能用for循环遍历时，可以倒着遍历并删除；或者从前往后遍历，"但删除元素后做i --操作。

     */
    public static void main(String[] args) {
        // 目标:三种遍历可能出现的并发修改异常问题
        ArrayList<String> list = new ArrayList<String>();
        list.add("宁夏枸杞");
        list.add("黑枸杞");
        list.add("人字拖");
        list.add("特级枸杞");
        list.add("枸杞子");

        System.out.println("--- 1、迭代器遍历，删除指定的元素");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String name = it.next();
            if (name.contains("枸杞")) {
                // 如果使用迭代器遍历，并用集合删除数据，会出现并发修改异常（ConcurrentModificationException），程序出现bug。
//                list.remove(name);    //  // 执行异常：ConcurrentModificationException
                it.remove();        // 使用迭代器自己的方法来删除数据，才不会出现异常。
            }
        }
        System.out.println(list);

        System.out.println("--- 2、使用增强for，删除指定的元素");
        ArrayList<String> list2 = new ArrayList<String>();
        list2.add("黑枸杞");
        list2.add("宁夏枸杞");
        list2.add("人字拖");
        list2.add("特级枸杞");
        list2.add("枸杞子");
        // 报异常：ConcurrentModificationException
        // 因为增强for循环本质也是迭代器遍历，并且无法解决次异常。
//        for (String name : list2) {
//            if (name.contains("枸杞")) {
//                list2.remove(name);
//            }
//        }

        System.out.println("--- 3、Lambda表达式遍历集合，删除指定的元素");
        ArrayList<String> list3 = new ArrayList<String>();
        list3.add("黑枸杞");
        list3.add("宁夏枸杞");
        list3.add("人字拖");
        list3.add("特级枸杞");
        list3.add("枸杞子");
        // 报异常：ConcurrentModificationException
//        list3.forEach(name -> {
//            if (name.contains("枸杞")) {
//                list3.remove(name);
//            }
//        });

        // 注意：也可以采用基础版 day08 的for循环带索引的方式删除，如下代码所示：
        // 解决方案1：删一个元素，for循环遍历索引回退一步
        for (int i = 0; i < list3.size(); i++) {
            String name = list3.get(i);
            if (name.contains("枸杞")) {
                list3.remove(name);
                i--;
            }
        }
        System.out.println(list3);
    }
}
