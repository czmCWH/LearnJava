package com.czm.d3_collections.Test05Map;

import java.util.Map;
import java.util.TreeMap;

public class Test03TreeMap {

    /*
      1、TreeMap
      TreeMap 要求 key 必须具备可比较性，默认按照从小到大的顺序遍历 key

      2、
     */

    public static void main(String[] args) {
        Map<String, Integer> map = new TreeMap<>();
        map.put("Jack", 90);
        map.put("Kate", 70);
        map.put("Tom", 80);
        map.put("Rose", 88);

        System.out.println("--- map = " + map);     //  {Jack=90, Kate=70, Rose=88, Tom=80}

        map.forEach((key, value) -> {
            System.out.println("key = " + key + ", value = " + value);
        });


    }
}
