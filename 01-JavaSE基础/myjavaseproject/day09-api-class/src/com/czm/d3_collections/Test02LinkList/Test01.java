package com.czm.d3_collections.Test02LinkList;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Test01 {
    /*
      1、LinkedList 是一个双向链表，与 ArrayList 的 API 类似。

      2、ArrayList 与 LinkedList 对比：
        a、查找元素
            ArrayList，效率非常高，可以直接通过索引定位到元素。
            LinkedList，效率低。
        b、添加元素
            末尾追加元素，ArrayList 与 LinkedList 效率差不多。
            开头插入元素，LinkedList 效率非常高，ArrayList 效率低。
            任意位置插入元素，ArrayList 与 LinkedList 效率差不多。
        c、删除元素
            删除尾部元素，ArrayList 与 LinkedList 效率差不多。
            删除头部元素，LinkedList 效率非常高，ArrayList 效率低。
            删除任意位置，ArrayList 与 LinkedList 效率差不多。
      3、总结
        a、使用效率上的差别：
            ArrayList 优势：
                1、查找元素；
                2、在尾部 追加/删除 元素
            LinkedList 优势：
                1、头/尾 添加/删除 元素
            如果频繁的在任意位置 添加、删除 元素使用 LinkedList；

        b、内存上的差别：
            LinkedList 会频繁的申请/销毁 堆空间（因为增加删除元素时会频繁 创建/销毁 Node 对象）；不会造成内存的浪费（需要多少使用多少）；
            ArrayList 不会频繁申请和销毁内存空间（因为会自动扩容）；可能会导致内存的浪费（可通过缩容解决）；

     */
    public static void main(String[] args) {

        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        System.out.println(list);   // [1, 2, 3]
        System.out.println(list.get(2));    // 3

    }
}
