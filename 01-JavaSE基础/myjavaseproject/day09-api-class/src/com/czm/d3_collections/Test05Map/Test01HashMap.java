package com.czm.d3_collections.Test05Map;

import java.util.*;
import java.util.Map.Entry;

public class Test01HashMap {

    /*
      1、HashMap
      HashMap 存储的是键值对（key-value），有些便程语言中叫做“字典”，Java 中叫做“映射、键值对集合“。

      HashMap 的 key键 依赖 hashCode 方法 和 equals 方法保证键的唯一；
        如果键存储的是自定义类型的对象，可以通过重写 hashCode 和 equals 方法，这样可以保证多个对象内容一样时，HashMap 集合就能认为是重复的；

      2、Map集合体系
        Map<K,V>
            HashMap<K,V>
                LinkedHashMap<K,V>
            TreeMap<K,V>

        注意：Map 系列集合的特点都是由键决定的，值只是一个附属品，值是不做要求的。
        HashMap（由键决定特点）：无序、不重复、无索引；--- 日常使用最多
        LinkedHashMap（由键决定特点）：有序、不重复、无索引；
        TreeMap（由键决定特点）：按键大小默认生序排序、不重复、无索引；

        ⚠️：Set<E> 集合的底层是基于 Map<K,V>，不过它只要 Map 集合的键元素。

      3、HashMap 集合的底层原理
        HashMap 跟 HashSet 的底层原理是一模一样的，都是基于哈希表实现的。
        哈希表：
            a、JDK8之前，哈希表=数组+链表
            b、JDK8开始，哈希表=数组+链表+红黑树
            c、哈希表是一种增删改查数据，性能都较好的数据结构。

     */

    public static void main(String[] args) {

        // 1、HashMap 的初始化
        Map<String, Integer> map = new HashMap<>();
        map.put("Jack", 90);
        map.put("Tom", 80);
        map.put("Kate", 70);
        map.put("Rose", 80);
        map.put("Jack", 88);    // 重复的键，会覆盖同名的键

        System.out.println("--- map = " + map);     // {Tom=80, Kate=70, Rose=80, Jack=88}

        // 双括号初始化 Map --- 不推荐！
        Map<String, Integer> map2 = new HashMap<String, Integer>() {{
            put("Jack", 90);
            put("Tom", 80);
        }};
        System.out.println("map2 = " + map2);

        // 2、size，获取元素个数
        System.out.println("--- size = " + map.size());     // size = 4

        // 3、清空集合
        map2.clear();
        System.out.println("map2 = " + map2);   // map2 = {}
        System.out.println("map2.isEmpty = " + map2.isEmpty());     // map2.isEmpty = true

        // 4、根据键获取对应值
        System.out.println("--- key：Jack，value： " + map.get("Jack"));   // key：Jack，value： 88

        // 5、根据键删除整个元素，返回数据对应的值
        map.remove("Jack");
        System.out.println("--- key：Jack，value： " + map.get("Jack"));   // key：Jack，value： null
        System.out.println("--- map = " + map);     // {Tom=80, Kate=70, Rose=80}

        // 6、判断是否包含 某个键 / 某个值
        System.out.println("containsKey = " + map.containsKey("Jack"));
        System.out.println("containsValue = " + map.containsValue(80));

        // 7、获取全部 键 / 值
        Set<String> allKeys = map.keySet();     // 键不能重复，所以返回值为 Set 类型
        System.out.println("--- allKeys = " + allKeys);

        Collection<Integer> allValues = map.values();   // 值可以重新赋，所以返回值用 Collection 类型
        System.out.println("--- allValues = " + allValues);

        // 8、HashMap 使用自定义 key
        Map<Movie, String> mp = new HashMap<>();
        mp.put(new Movie("【西游记】", 8.5, "吴承恩"), "17: 00");
        mp.put(new Movie("【水浒传】", 8.0, "施耐庵"), "09:00");
        mp.put(new Movie("【三国演义】", 9.6, "罗贯中"), "11: 00");
        mp.put(new Movie("【红楼梦】", 9.9, "曹雪芹"), "14: 00");
        mp.put(new Movie("【西游记】", 8.5, "吴承恩"), "15: 00");

        System.out.println("---" + mp);


        System.out.println("\n--- Map 的遍历：");

        System.out.println("--- 方式1：👍推荐！");
        // entrySet 把 Map集合转换成 Set集合来遍历
        // 一个 Entry 代表一个 key-value 键值对
        Set<Entry<String, Integer>> entries = map.entrySet();
        for (Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }

        System.out.println("\n--- 方式2：forEach 函数式接口 （Lambda 表达式） 👍推荐！");
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
