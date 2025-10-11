package com.czm.d3_collections.Test04Set;

import java.util.LinkedHashSet;
import java.util.Set;

public class Test04LinkedHashSet {

    /*
     1、LinkedHashSet - 顺序Set
       LinkedHashSet 继承自 HashSet，记录了元素的添加顺序；
       基于哈希表(数组、链表、红黑树)实现的；
        但是，它的每个元素都额外的多了一个双链表的机制记录它前后元素的位置；因此它是有序的。

       牺牲内存，获取效果。
     */

    public static void main(String[] args) {

        Set<String> set = new LinkedHashSet<>();
        set.add("Jack");
        set.add("Tom");
        set.add("Kate");
        set.add("Tom");
        System.out.println("--- set = " + set);

        for (String s : set) {
            System.out.print(s + "、");      // Jack、Tom、Kate、
        }
        System.out.println();

        set.remove("Tom");
        System.out.println("--- set = " + set); // [Jack, Kate]

    }
}
