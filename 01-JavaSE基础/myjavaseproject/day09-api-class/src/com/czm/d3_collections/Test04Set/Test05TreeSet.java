package com.czm.d3_collections.Test04Set;

import java.util.Set;
import java.util.TreeSet;

public class Test05TreeSet {

    /*
     1、TreeSet - 排序Set
      TreeSet 要求元素必须具备可比较性（即实现了 Comparable ）；

      特点：不重复、无索引、可排序(默认升序排序，按照元素的大小，由小到大排序）
        底层是基于红黑树实现的排序。

      注意：
            对于数值类型：Integer，Double，默认按照数值本身的大小进行升序排序。
            对于字符串类型：默认按照首字符的编号升序排序。
            对于自定义类型的对象，TreeSet默认是无法直接排序的。

     2、自定义 TreeSet 集合的排序规则
       TreeSet 集合存储自定义类型的对象时，必须指定排序规则，支持如下两种方式来指定比较规则。
       方式一：
          让自定义的类实现 Comparable 接口，重写里面的 compareTo 方法来指定比较规则。

       方式二：
          通过调用 TreeSet 集合有参数构造器，可以设置 Comparator 对象(比较器对象，用于指定比较规则。
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
