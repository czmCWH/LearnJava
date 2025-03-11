package com.czm.d5_map_traversal;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test2 {

    // 2、键值对方式遍历 Map<K,V> 集合

    public static void main(String[] args) {
        Map<String, Integer> mp = new HashMap<>();      // 多态
        mp.put("iphone", 1);
        mp.put("小米", 3);
        mp.put("Mac电脑", 1);
        mp.put("背包", 1);
        mp.put("水杯", 1);
        mp.put("iphone", 11);

        // 增强for循环无法直接遍历 Map集合，因为 Map集合的元素是键值对，而且不存在键值对类型
//        for (元素类型 变量 : Map<K,V>) {}

        // 解决方法：调用 Map 集合的一个方法把 Map集合转换成 Set集合来遍历；

        Set<Map.Entry<String, Integer>> entries = mp.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            System.out.println(key + "=" + value);
        }
    }
}
