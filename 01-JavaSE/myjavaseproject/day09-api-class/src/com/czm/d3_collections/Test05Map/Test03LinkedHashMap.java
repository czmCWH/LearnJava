package com.czm.d3_collections.Test05Map;

import java.util.LinkedHashMap;
import java.util.Map;

public class Test03LinkedHashMap {

    /*
      1、LinkedHashMap
       LinkedHashMap 在 HashMap 的基础上 记录了添加元素的顺序；
       底层数据结构依然是基于哈希表实现的，只是每个键值对元素又额外的多了一个双链表的机制记录元素顺序(保证有序)；
       实际上：原来学习的 LinkedHashSet 集合的底层原理就是 LinkedHashMap；
     */

    public static void main(String[] args) {

        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("Jack", 90);
        map.put("Kate", 70);
        map.put("Tom", 80);
        map.put("Rose", 88);

        System.out.println("--- map = " + map);     // {Jack=90, Kate=70, Tom=80, Rose=88}

        map.remove("Kate");
        System.out.println("--- map = " + map);     // {Jack=90, Tom=80, Rose=88}

        System.out.println("\n--- 遍历：");
        map.forEach((k, v) -> {
            System.out.printf("key = %s, value = %s\n", k, v);
        });


    }
}
