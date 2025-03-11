package com.czm.d4_map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Test2 {
    /*
     1、 Map<K,V> 集合的常用方法
        Map 是双列集合的祖宗接口，它的功能是全部双列集合都可以继承过来使用的。

     2、
     */
    public static void main(String[] args) {
        System.out.println("------ 1、添加元素");
        Map<String, Integer> mp = new HashMap<>();      // 多态
        mp.put("iphone", 1);
        mp.put("小米", 3);
        mp.put("Mac电脑", 1);
        mp.put("背包", 1);
        mp.put("水杯", 1);
        mp.put("iphone", 11);       // 重复的键，会覆盖同名的键
        System.out.println(mp);

        System.out.println("------ 2、获取集合的大小");
        System.out.println(mp.size());

        System.out.println("------ 3、清空集合");
//        mp.clear();
//        System.out.println(mp);

        System.out.println("------ 4、判断集合是否为空，为空返回true");
        System.out.println(mp.isEmpty());

        System.out.println("------ 5、根据键获取对应值");
        System.out.println(mp.get("iphone"));
        System.out.println(mp.get("小米"));

        System.out.println("------ 6、根据键删除整个元素，返回数据对应的值");
        System.out.println(mp.remove("iphone"));
        System.out.println(mp);

        System.out.println("------ 8、判断是否包含某个键");
        System.out.println(mp.containsKey("iphone"));

        System.out.println("------ 9、判断是否包含某个值");
        System.out.println(mp.containsValue(11));

        System.out.println("------ 9、获取全部键的集合");
        Set<String> keys = mp.keySet();     // 键不能重复，所以返回值为 Set 类型
        System.out.println(keys);

        System.out.println("------ 10、获取Map集合的全部值");
        Collection<Integer> values = mp.values();   // 值可以重新赋，所以返回值用 Collection 类型
        System.out.println(values);


    }
}
