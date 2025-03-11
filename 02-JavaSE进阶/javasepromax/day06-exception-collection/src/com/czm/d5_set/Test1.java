package com.czm.d5_set;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class Test1 {
    /*
     1、Set 集合
        Collection<E>
            Set<E>
                HashSet<E>
                    LinkedHashSet<E>
                TreeSet<E>
         注意：
            Set要用到的常用方法，基本上就是Collection提供的!!
            自己几乎没有额外新增一些常用功能!


     2、
     */
    public static void main(String[] args) {
        // 1、开发中经典代码
        // HashSet 无序、无索引、无重复代码
        Set<String> set = new HashSet<>();
        set.add("张无忌");
        set.add("小昭");
        set.add("张无忌");
        set.add("金花婆婆");
        set.add("灭绝师太");
        set.add("金花婆婆");
        System.out.println("--- set = " + set);

        // 2、LinkedHashSet
        // LinkedHashSet 有序、无索引、无重复代码
        Set<String> set1 = new LinkedHashSet<>();
        set1.add("张无忌");
        set1.add("小昭");
        set1.add("张无忌");
        set1.add("金花婆婆");
        set1.add("灭绝师太");
        set1.add("金花婆婆");
        System.out.println("--- set1 = " + set1);

    }
}
