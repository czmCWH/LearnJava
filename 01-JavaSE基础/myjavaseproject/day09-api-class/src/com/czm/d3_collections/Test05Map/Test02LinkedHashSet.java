package com.czm.d3_collections.Test05Map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class Test02LinkedHashSet {

    /*
      1、LinkedHashMap
      LinkedHashMap 在 HashMap 的基础上 记录了添加元素师的顺序

      2、
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
