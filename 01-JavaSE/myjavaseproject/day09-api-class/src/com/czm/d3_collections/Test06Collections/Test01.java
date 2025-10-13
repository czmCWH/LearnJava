package com.czm.d3_collections.Test06Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Test01 {

    /*
      1、java.util.Collections 针对集合的工具类
      java.util.Collections 是一个常用的 集合工具类，提供了很多实用的静态方法。
            static void sort(List<T> list)，把集合中元素排序
            static <T> void sort(List<T> list, Comparator<? super T> c)，根据 自定义比较器 排序集合元素

            static T max(Collection<? extends T> coll)
            static <T> T max(Collection<? extends T> coll, Comparator<? super T> comp)，根据 自定义比较器 获取最大值

            static T min(Collection<? extends T> coll)
            static <T> T min(Collection<? extends T> coll, Comparator<? super T> comp)，根据 自定义比较器 获取最小值

            static <T> boolean addAll(Collection<? super T> c, T... elements)，向集合中添加多个元素。

            static void reverse(List<?> list)
            static <T> Comparator<T> reverseOrder()，返回一个倒序比较器

            static void shuffle(List<?> list)，随机打乱 list 中的元素顺序
            static void swap(List<?> list, int i, int j)，交换 i、j 索引位置的元素
            static <T> void fill(List<? super T> list, T obj)，使用 obj 填满 list
            static <T> void copy(List<? super T> dest, List<? extends T> src)，将 src 的元素拷贝到 dest 中
            static <T> boolean replaceAll(List<T> list, T oldVal, T newVal)，将 list 中 所有的 oldVal 替换为 newVal

      2、
     */

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();

        Collections.addAll(list, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("--- 1、list = " + list);

        Collections.sort(list, Collections.reverseOrder());
        System.out.println("\n--- 2、list = " + list);

        Collections.reverse(list);
        System.out.println("\n--- 3、list = " + list);



        Collections.fill(list, 99);
        System.out.println("\n--- 4、list = " + list);

        Collections.replaceAll(list, 99, 0);
        System.out.println("\n--- 5、list = " + list);



    }
}
