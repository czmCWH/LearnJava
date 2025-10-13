package com.czm.d7_map_x;

import java.util.Map;
import java.util.TreeMap;

public class Test3 {
    /*
     1、TreeMap 集合的原理
        特点: 不重复、无索引、可排序(按照键的大小默认升序排序，只能对键排序）

        原理: TreeMap 跟 TreeSet 集合的底层原理是一样的，都是基于红黑树实现的排序。

     2、TreeMap 集合同样也支持两种方式来指定排序规则
        a、让类实现 Comparable 接口，重写比较规则。
        b、TreeMap 集合有一个有参数构造器，支持 Comparator 比较器对象，以便用来指定比较规则。

     */
    public static void main(String[] args) {
        Map<Movie1, String> mp = new TreeMap<>((m1, m2) -> Double.compare(m2.getScore(), m1.getScore()));
        mp.put(new Movie1("【西游记】", 8.5, "吴承恩"), "17: 00");
        mp.put(new Movie1("【水浒传】", 8.0, "施耐庵"), "09:00");
        mp.put(new Movie1("【三国演义】", 9.6, "罗贯中"), "11: 00");
        mp.put(new Movie1("【红楼梦】", 9.9, "曹雪芹"), "14: 00");
        mp.put(new Movie1("【西游记】", 8.5, "吴承恩"), "15: 00");
        System.out.println(mp);
    }
}
