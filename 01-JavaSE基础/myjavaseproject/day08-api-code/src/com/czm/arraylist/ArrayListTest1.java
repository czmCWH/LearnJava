package com.czm.arraylist;

import java.util.ArrayList;

public class ArrayListTest1 {
    /*
     1、ArrayList 集合案例：批量删除
     */
    public static void main(String[] args) {
        // 1、用 ArrayList 模拟购物车
        ArrayList<String> list = new ArrayList<String>();
        list.add("宁夏枸杞");
        list.add("黑枸杞");
        list.add("人字拖");
        list.add("特级枸杞");
        list.add("枸杞子");

        // 2、遍历 集合中的元素，并删除满足条件的元素
//        for (int i = 0; i < list.size(); i++) {
//            String name = list.get(i);
//            if (name.contains("枸杞")) {
//                list.remove(name);
//            }
//        }
//        System.out.println(list);   // 打印：[黑枸杞, 人字拖, 枸杞子]
        // 问题，为什么没有删除干净，因为每执行删除一个元素，数组前面的元素后移，for循环的i前移动，所以会这样。

        // 解决方案1：删一个元素，for循环遍历索引回退一步
        for (int i = 0; i < list.size(); i++) {
            String name = list.get(i);
            if (name.contains("枸杞")) {
                list.remove(name);
                i--;
            }
        }
        System.out.println(list);
        System.out.println("-----------解决方案2");
        ArrayList<String> list2 = new ArrayList<String>();
        list2.add("宁夏枸杞");
        list2.add("黑枸杞");
        list2.add("人字拖");
        list2.add("特级枸杞");
        list2.add("枸杞子");
        // 倒序遍历删除
        for (int i = list2.size() - 1; i >= 0; i--) {
            String name = list2.get(i);
            if (name.contains("枸杞")) {
                list2.remove(name);
            }
        }
        System.out.println("---方法2删除结束 = " + list2);
    }
}
