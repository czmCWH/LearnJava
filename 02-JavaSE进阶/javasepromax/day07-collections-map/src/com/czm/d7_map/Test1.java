package com.czm.d7_map;

import java.util.HashMap;
import java.util.Map;

public class Test1 {
    /*
     1、HashMap 集合的底层原理
        HashMap 跟 HashSet 的底层原理是一模一样的，都是基于哈希表实现的。

        哈希表：
            a、JDK8之前，哈希表=数组+链表
            b、JDK8开始，哈希表=数组+链表+红黑树
            c、哈希表是一种增删改查数据，性能都较好的数据结构。

     2、HashMap 的特点
        HashMap 集合是一种增删改查数据，性能都较好的集合；

        但是它是无序，不能重复，没有索引支持的(由键决定特点）；

        HashMap 的键依赖 hashCode 方法 和 equals 方法保证键的唯一；

        如果键存储的是自定义类型的对象，可以通过重写 hashCode 和 equals 方法，这样可以保证多个对象内容一样时，HashMap 集合就能认为是重复的；

     2、TreeMap
     */
    public static void main(String[] args) {

        Map<Movie, String> mp = new HashMap<>();
        mp.put(new Movie("【西游记】", 8.5, "吴承恩"), "17: 00");
        mp.put(new Movie("【水浒传】", 8.0, "施耐庵"), "09:00");
        mp.put(new Movie("【三国演义】", 9.6, "罗贯中"), "11: 00");
        mp.put(new Movie("【红楼梦】", 9.9, "曹雪芹"), "14: 00");
        mp.put(new Movie("【西游记】", 8.5, "吴承恩"), "15: 00");

        System.out.println("---" + mp);

    }
}
