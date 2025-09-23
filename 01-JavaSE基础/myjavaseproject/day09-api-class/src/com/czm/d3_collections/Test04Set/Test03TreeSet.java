package com.czm.d3_collections.Test04Set;

import java.util.Set;
import java.util.TreeSet;

public class Test03TreeSet {

    /*
      1、TreeSet - 排序Set
      TreeSet 要求元素必须具备可比较性（即实现了 Comparable ），默认按照从小到大的顺序遍历元素。

      2、
     */

    public static void main(String[] args) {
        Set<String> set = new TreeSet<>();
        set.add("Kate");
        set.add("Tom");
        set.add("Jack");
        set.add("Rose");
        set.add("Kate");

        System.out.println("--- 1、TreeSet 的去重、排序：");

        System.out.println("--- set = " + set);     // [Jack, Kate, Rose, Tom]，TreeSet 的打印顺序就是 遍历顺序，按照从小到大排列元素。

        for (String s : set) {
            System.out.print(s + "、");  // Jack、Kate、Rose、Tom、
        }
        System.out.println();

        System.out.println("--- 2、TreeSet 自定义元素比较方式排序：");
        Set<Integer> set2 = new TreeSet<>((i1, i2) -> i2 - i1);
        set2.add(1);
        set2.add(20);
        set2.add(3);
        set2.add(45);
        set2.add(6);

        System.out.println("--- set2 = " + set2);   // [45, 20, 6, 3, 1]，修改为从大到小排序

        for (Integer i : set2) {
            System.out.print(i + "、");  // 45、20、6、3、1、
        }

    }
}
