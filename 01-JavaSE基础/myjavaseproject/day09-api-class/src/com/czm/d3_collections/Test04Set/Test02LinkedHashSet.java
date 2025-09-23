package com.czm.d3_collections.Test04Set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Test02LinkedHashSet {

    /*
      1、LinkedHashSet - 顺序Set
      LinkedHashSet 在 HashSet 的基础上，记录了元素的添加顺序。

      2、

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
