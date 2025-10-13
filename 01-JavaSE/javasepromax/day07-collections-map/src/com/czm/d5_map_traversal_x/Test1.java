package com.czm.d5_map_traversal_x;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test1 {
    /*
     1、Map<K,V> 集合的遍历方式
        a、键找值
            先获取Map集合全部的键，再通过遍历键来找值；
        b、键值对
            把键值对看作一个整体遍历，难度大；
        c、Lambda 表达式
            JDK1.8开始之后的新技术，非常简单；

     2、
     */
    public static void main(String[] args) {
        Map<String, Integer> mp = new HashMap<>();      // 多态
        mp.put("iphone", 1);
        mp.put("小米", 3);
        mp.put("Mac电脑", 1);
        mp.put("背包", 1);
        mp.put("水杯", 1);
        mp.put("iphone", 11);

        // 1、获取Map集合全部的键
        Set<String> keys = mp.keySet();
        // 2、根据键获取值
        for (String key : keys) {
            Integer value = mp.get(key);
            System.out.println(value);
        }
    }
}
