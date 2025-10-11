package com.czm.d4_map_x;

import java.util.HashMap;
import java.util.Map;

public class Test1 {
    /*
     1、Map 集合
        双列集合、键值对集合，一次存一对数据作为元素。
        语法：{key1=value1, key2=value2, key3=value3,..}
        Map 集合的每个元素 key=value 称为键值对、键值对对象、一个Entry对象，Map集合也被叫做“键值对集合”。
        Map 集合的所有键是不允许重复的，但值可以重复，键和值是一一对应的，每一个键只能找到自己对应的值。

     2、Map 集合在什么业务场景下使用
        需要存储一一对应关系的数据时，可以考虑使用Map集合，比如：购物车中的 商品和购买数量

     3、Map集合体系
        Map<K,V>
            HashMap<K,V>
                LinkedHashMap<K,V>
            TreeMap<K,V>
        注意：⚠️Map系列集合的特点都是由键决定的，值只是一个附属品，值是不做要求的。
        HashMap（由键决定特点）：无序、不重复、无索引；⚠️：使用最多
        LinkedHashMap（由键决定特点）：有序、不重复、无索引；
        TreeMap（由键决定特点）：按大小默认生序排序、不重复、无索引；

        ⚠️：Set<E> 集合的底层是基于 Map<K,V>，不过它只要 Map 集合的键元素。
     */
    public static void main(String[] args) {
        // 集合、泛型不支持基本数据类型，只能使用包装类

        // 1、定义一个Map对象
        Map<String, Integer> mp = new HashMap<>();      // 多态
        mp.put("iphone", 1);
        mp.put("小米", 1);
        mp.put("Mac电脑", 1);
        mp.put("背包", 1);
        mp.put("水杯", 1);
        mp.put("iphone", 11);       // 重复的键，会覆盖同名的键
        System.out.println(mp);

    }
}
