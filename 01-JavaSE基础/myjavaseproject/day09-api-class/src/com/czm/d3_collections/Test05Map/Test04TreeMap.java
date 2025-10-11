package com.czm.d3_collections.Test05Map;

import java.util.Map;
import java.util.TreeMap;

public class Test04TreeMap {

    /*
      1、TreeMap
       TreeMap 要求 key 必须具备可比较性，默认按照从小到大的顺序遍历 key；
       原理: TreeMap 跟 TreeSet 集合的底层原理是一样的，都是基于红黑树实现的排序。

      2、TreeMap 集合同样也支持两种方式来指定排序规则
        a、让类实现 Comparable 接口，重写比较规则。
        b、TreeMap 集合有一个有参数构造器，支持 Comparator 比较器对象，以便用来指定比较规则。
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


        Map<Book, String> mp = new TreeMap<>((m1, m2) -> Double.compare(m2.getScore(), m1.getScore()));
        mp.put(new Book("【西游记】", 8.5, "吴承恩"), "17: 00");
        mp.put(new Book("【水浒传】", 8.0, "施耐庵"), "09:00");
        mp.put(new Book("【三国演义】", 9.6, "罗贯中"), "11: 00");
        mp.put(new Book("【红楼梦】", 9.9, "曹雪芹"), "14: 00");
        mp.put(new Book("【西游记】", 8.5, "吴承恩"), "15: 00");
        System.out.println(mp);


    }
}
