package com.czm.d3_collections.Test05Map;

import java.util.*;
import java.util.Map.Entry;

public class Test01HashMap {

    /*
      1、HashMap
      HashMap 存储的是键值对（key-value），有些便程语言中叫做“字典”，Java 中叫做“映射“。

      2、
     */

    public static void main(String[] args) {

        Map<String, Integer> map = new HashMap<>();
        map.put("Jack", 90);
        map.put("Tom", 80);
        map.put("Kate", 70);
        map.put("Rose", 80);
        map.put("Jack", 88);

        System.out.println("--- map = " + map);     // {Tom=80, Kate=70, Rose=80, Jack=88}

        System.out.println("--- size = " + map.size());     // size = 4
        System.out.println("--- key：Jack，value： " + map.get("Jack"));   // key：Jack，value： 88

        map.remove("Jack");
        System.out.println("--- key：Jack，value： " + map.get("Jack"));   // key：Jack，value： null

        System.out.println("--- map = " + map);     // {Tom=80, Kate=70, Rose=80}

        System.out.println("\n--- Map 的遍历：");

        System.out.println("--- 方式1：👍推荐！");
        // 一个 Entry 代表一个 key-value 键值对
        Set<Entry<String, Integer>> entries = map.entrySet();
        for (Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        System.out.println("\n--- 方式2：forEach 函数式接口 👍推荐！");
        map.forEach((k, v) -> {
            System.out.println(k + "=" + v);
        });

        System.out.println("\n--- 方式3：先获取所有key，再遍历key取出value，整体效率低");
        Set<String> keys = map.keySet();
        for (String key : keys) {
            System.out.println(key + " = " + map.get(key));
        }

        System.out.println("\n--- 方式4：只遍历 value");
        Collection<Integer> values = map.values();
        for (Integer value : values) {
            System.out.println("value = " + value);
        }

    }
}
