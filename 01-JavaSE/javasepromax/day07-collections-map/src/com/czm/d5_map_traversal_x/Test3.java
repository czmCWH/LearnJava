package com.czm.d5_map_traversal_x;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

public class Test3 {

    // 3、Lambda 表达式方式遍历 Map<K,V> 集合

    public static void main(String[] args) {
        Map<String, Integer> mp = new HashMap<>();      // 多态
        mp.put("iphone", 1);
        mp.put("小米", 3);
        mp.put("Mac电脑", 1);
        mp.put("背包", 1);
        mp.put("水杯", 1);
        mp.put("iphone", 11);

        // 创建一个匿名内部类，使用对象回调 思想 进行遍历
        mp.forEach(new BiConsumer<String, Integer>() {
            // accept 方法中消费键、值
            @Override
            public void accept(String s, Integer integer) {
                System.out.println(s + "=" + integer);
            }
        });

        System.out.println("--- 简化");
        mp.forEach((k, v) -> {
            System.out.println(k + "=>" + v);
        });


    }
}
