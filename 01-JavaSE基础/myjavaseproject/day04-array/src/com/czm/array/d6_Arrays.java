package com.czm.array;

import java.util.Arrays;
import java.util.function.IntToDoubleFunction;

public class d6_Arrays {

    /*
      1、java.util.Arrays 数组工具类
        在 Java 中凡是带 s 结尾的类名，基本上都是工具类。

        Arrays 常用方法：
          toString，
          copyOfRange，
          copyOf，
          setAll，
          sort，

     */

    public static void main(String[] args) {

        // 1、将数组的内容以字符串的方式返回
        int[] arr = {12, 22, 10, 38, 21, 33};
        String arrStr = Arrays.toString(arr);
        System.out.println("--- 1、Arrays.toString() = " + arrStr);

        // 2、拷贝数组的指定区间内容到一个新数组
        int[] arr2 = Arrays.copyOfRange(arr, 2, 5);     // ⚠️：java 的中的区间范围 包前不包后
        String arr2Str = Arrays.toString(arr2);
        System.out.println("--- 2、Arrays.copyOfRange() = " + arr2Str);

        // 3、给数组扩容，并返回一个新数组
        int[] arr3 = Arrays.copyOf(arr, arr.length + 5);
        String arr3Str = Arrays.toString(arr3);
        System.out.println("---- 3、Arrays.copyOf() = " + arr3Str);

        // 4、修改数组中的每个数据
        double[] scores = {70, 85.5, 76.5, 80, 87.5};
        // 参数2为 interface 类型，我们通过匿名内部类创建一个接口的对象来传入。
        Arrays.setAll(scores, new IntToDoubleFunction() {
            @Override
            public double applyAsDouble(int value) {
                return scores[value] + 10;
            }
        });
        System.out.println("---- 4、 Arrays.setAll() = " + Arrays.toString(scores));

        // 5、对数组进行排序，升序排序，由小到大。
        // 注意：Arrays.sort 无法对对象类型排序，因为它不知道排序规则
        Arrays.sort(scores);
        System.out.println("--- 5、 Arrays.sort = " + Arrays.toString(scores));
    }
}
