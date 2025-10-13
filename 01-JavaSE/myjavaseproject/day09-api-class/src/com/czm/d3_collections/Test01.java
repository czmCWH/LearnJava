package com.czm.d3_collections;

public class Test01 {
    /*
      1、集合（Collections）
       java.util 包中有个集合框架（Collections Framework），提供了一大堆常用的数据结构，如：
         ArrayList、LinkedList、Queue、Stack、HashSet、HashMap等。

       数据结构是计算机存储、组织数据的方式。常见的数据结构类型：
            线性结构、树形结构、图形结构
       在实际应用中，根据使用场景来选择最合适的数据结构。

      2、List vs Set vs Map
        a、List 的特点：
            可以存储重复的元素，即元素之间 equals 可能返回 true；
            可以通过索引访问元素；（有记录元素的添加顺序）

        b、Set的特点：
            不可以存储重复的元素，即元素之间 equals 不可能返回 true；
            不可以通过索引访问元素；
            不记录元素的添加顺序（LinkedHashSet 除外）；

        c、Map 的特点
            不可以存储重复的key，可以存储重复的 value；
            不可以通过索引访问 key-value;
            不记录 key-value 的添加顺序(LinkedHashMap 除外)；
            Map 底层使用了 红黑树、链表、哈希表

        d、Set 的底层是基于 Map 实现的
            HashSet 底层用了 HashMap；
            LinkedHashSet 底层用了 LinkedHashMap；
            TreeSet 底层用了 TreeMap；

     */

    public static void main(String[] args) {

    }
}
