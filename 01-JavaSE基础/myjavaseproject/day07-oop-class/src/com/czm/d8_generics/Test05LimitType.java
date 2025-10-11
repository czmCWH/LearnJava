package com.czm.d8_generics;

import com.czm.d8_generics.demo.Man;
import com.czm.d8_generics.demo.Pig;
import com.czm.d8_generics.demo.Sport;

import java.util.Arrays;

public class Test05LimitType {

    /*
      1、限制泛型的 类型参数
      可以通过 extends 对类型参数增加一些限制条件，比如：<T extends A>。
        extends 后面可以跟上 类名、接口名，代表 T 必须是 A 类型、或者继承A类型、或者实现A接口.
        可以同时添加多个限制，比如：<T extends A & B & C>，代表 T 必须同时满足 A、B、C。

      2、Comparable 接口  vs  Comparator 函数式接口
        数组的所有元素本身具备可比较性，即都需实现 Comparable 接口。那么，可以直接使用 Arrays.sort 排序。
        也可以给  Arrays.sort 传递 Comparator 类型参数，在不修改数组元素的前提下，自定义比较方式。此方式适用于使用第三方类、官方类时。

        Comparator 类型是 函数式接口！

     */

    public static void main(String[] args) {

        System.out.println("--- 👉 1、使用 extends 限制泛型的类型参数：");
        Man<Double> p1 = new Man<>(18.7);
        Man<Integer> p2;
//        Man<String> p2; // 报错，Error

        System.out.println("\n--- 👉 2、Comparable 接口比较大小：");
        // 使用 Comparable 协议查找数组中最大的元素
        Double[] arr1 = {1.2, 8.9, 1.3, 5.5};
        System.out.println("arr1 max = " + getMax(arr1));

        Integer[] arr2 = {19, 22, 5, 2, 9};
        System.out.println("arr2 max = " + getMax(arr2));

        System.out.println("\n--- 👉 3、Arrays.sort 数组排序");
        Integer[] arr3 = {99, 10, 2, 55, 9};
        Arrays.sort(arr3);
        System.out.println("arr3 = " + Arrays.toString(arr3));  // [2, 9, 10, 55, 99]

        Pig[] pgs = {
                new Pig(190.0),
                new Pig(130.0),
                new Pig(100.0),
        };
        // 根据数组元素的  Comparable 接口进行比较
        Arrays.sort(pgs);
        System.out.println("pgs = " + Arrays.toString(pgs));

        // Comparator 函数式接口类型，根据 指定比较器 进行自定义比较方式，如下所示：
//        Arrays.sort(pgs, new Comparator<Pig>() {
//            @Override
//            public int compare(Pig o1, Pig o2) {
//                return (int) (o2.getWeight() - o1.getWeight());
//            }
//        });

        // 简化1：
//        Arrays.sort(pgs, (o1, o2) -> {
//            return (int) (o2.getWeight() - o1.getWeight());
//        });
        // 简化2：
        Arrays.sort(pgs, (o1, o2) -> (int) (o2.getWeight() - o1.getWeight()));
        System.out.println("pgs = " + Arrays.toString(pgs));

        System.out.println("\n--- 👉 4、限制泛型的 类型参数 实现 Comparable 接口：");
        Sport[] spt = {
                new Sport<>(190.0),
                new Sport<>(130.0),
                new Sport<>(130.1),
        };
        Arrays.sort(spt);
        System.out.println("spt = " + Arrays.toString(spt)); // [Sport{score=130.0}, Sport{score=130.1}, Sport{score=190.0}]

    }

    static <T extends Comparable<T>> T getMax(T[] array) {
        if (array == null || array.length == 0) return null;
        T max = array[0];
        for (int i = 1; i < array.length; i++) {
//            if (array[i] > max) {   // 报错！因为 数组的元素类型是泛型，不是所有类型都可以使用 > 运算符。
            if (array[i].compareTo(max) > 0) {  // 返回值大于 0，表示左边大
                max = array[i];
            }
        }
        return max;
    }
}
