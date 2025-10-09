package com.czm.d5_AnonymousClass;

import java.util.Arrays;
import java.util.Comparator;

public class Test03 {

    /*
     1、排序
      可以使用 JDK 自带的 java.util.Arrays 类对数组进行排序。

      10大排序算法！

     2、

     */

    public static void main(String[] args) {
        Integer[] array = {10, 1, 33, 2, 3, 22, 11, 22};
        Arrays.sort(array); // 默认是升序，小的放左边，大的放右边
        System.out.println(Arrays.toString(array));     // [1, 2, 3, 10, 11, 22, 22, 33]

        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println(Arrays.toString(array));     // [33, 22, 22, 11, 10, 3, 2, 1]
    }
}
