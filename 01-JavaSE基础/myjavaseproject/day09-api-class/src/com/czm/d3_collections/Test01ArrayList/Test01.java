package com.czm.d3_collections.Test01ArrayList;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Test01 {
    /*
      1、数组的局限性
        无法动态扩容；如：int[] array = new int[4]; 最多只能存储4个数据。
        操作元素的过程不够面向对象；如：无法通过数组对象进行增、删等操作。

      2、ArrayList 动态数组
        java.util.ArrayList 是 Java 中的基于动态数组实现的 List 类，有如下特点：
            它是一个可以动态扩容的数组；
            允许元素重复 与 null值‌；
            封装了各种实用的数组操作；

      3、ArrayList 扩容的原理
        ArrayList 默认容量 DEFAULT_CAPACITY 是 10 个；
        每次扩容时，新容量是旧容量的1.5倍，计算倍数如下所示：
            int newCapacity = oldCapacity + (oldCapacity >> 1);
            int newCapacity = (int) (oldCapacity * 1.5);
            乘/除/取模运算效率都比较低，不如加/减/位运算效率高。

      4、
     */

    public static void main(String[] args) {
        /*
          👉 1、ArrayList 的基本使用
            private int size，数组中元素个数。
            public boolean isEmpty()，数组是否为空，即没有任何元素。
            public boolean contains(Object o)，数组是否包含某个对象。
            public int indexOf(Object o) ，返回对象在数组中第一次出现的索引，返回-1表示数组不包含该元素。
            public int lastIndexOf(Object o)，从末尾开始查找元素在数组中第一次出现的索引，返回-1即不存在此对象。
            public E get(int index)，返回index索引的元素。
            public E set(int index, E element)，把 index 索引位置的元素设置新的对象，并返回被修改的元素。
            public boolean add(E e)，在数组末尾添加元素。
            public void add(int index, E element)，在 index 索引位置插入元素。
            public E remove(int index)，删除 index 索引位置的元素。
            public boolean remove(Object o)，删除数组中第一个匹配的对象，删除成功返回 true。
            public void clear()，清空数组。
         */
        // ⚠️，通常我们把变量的类型声明为 List 接口类型，这样方便需求切换选择不同的实际类型。
        List<Object> list = new ArrayList<>();
        list.add(11);
        list.add(false);
        list.add(null);
        list.add(3.14);
        list.add(0, "jack");
        list.add("888");

        System.out.println(list.get(3));    // null
        System.out.println(list.indexOf(null)); // 3
        System.out.println(list.size());    // 6
        System.out.println(list);   // [jack, 11, false, null, 3.14, 888]

        List<Object> list2 = Arrays.asList(1, 2, 3, null, "你好");
        System.out.println("list2 = " + list2);     // [1, 2, 3, null, 你好]

    }

}
