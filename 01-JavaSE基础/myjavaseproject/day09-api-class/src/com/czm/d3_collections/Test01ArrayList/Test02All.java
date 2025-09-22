package com.czm.d3_collections.Test01ArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;

public class Test02All {
    /*
      👉 2、ArrayList 的基本使用2
        public boolean addAll(Collection<? extends E> c)，把集合对象的所有元素添加到当前数组。
        public boolean addAll(int index, Collection<? extends E> c)，把集合中所有元素添加到 index 索引的位置。
        public boolean removeAll(Collection<?> c)，把集合中所有元素从数组中删除。
        public boolean retainAll(Collection<?> c)，删除当前数组中不包含在集合c中的所有元素。
        public void forEach(Consumer<? super E> action)，接收 函数式接口类 型参数，可以使用。
        public void sort(Comparator<? super E> c)，数组元素排序。
     */
    public static void main(String[] args) {

        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        List<Integer> list2 = new ArrayList<>();
        list2.add(3);
        list2.add(4);
        list2.add(5);

        // retainAll 从 List1 中删除 List2 中元素以外的所有元素
        list1.retainAll(list2);
        System.out.println("list1 = " + list1);     // [3, 4]

        System.out.println("\n---  集合遍历：");
        iterateList();
    }

    // 集合的遍历
    private static void iterateList() {
        List<Integer> list1 = new ArrayList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);

        System.out.println("--- 集合遍历1：for循环遍历");
        int size = list1.size();
        for (int i = 0; i < size; i++) {
            System.out.println(list1.get(i));
        }

        System.out.println("\n--- 集合遍历2：迭代器方式遍历");
        Iterator<Integer> it = list1.iterator();    // 获取迭代器
        while (it.hasNext()) {  // 查看 cursor 游标是否指向 集合 size 位置，如果不是则返回 true；
            System.out.println(it.next());  // next()方法作用：将 cursor 游标指向的集合元素取出返回；cursor 游标向后移动一位；
        }

        System.out.println("\n--- 集合遍历3：for-each 格式快速遍历，官方特指");
        /*
          for-each 语法：
            for (元素类型 变量名 : 数组/Iterable) { ... }

          特点：
            a、实现了 Iterable 接口的对象，都可以使用 for-each 遍历元素，比如：List、Set 等。
            b、Iterable 在使用 foreach 格式遍历元素时，本质是使用了 Iterator 对象。
            c、⚠️ for-each 本质是 【集合遍历2：迭代器方式遍历】。
         */

        System.out.println("--- 遍历 List 集合类型：");
        for (Integer el : list1) {
            System.out.println("el = " + el);
        }

        System.out.println("--- 遍历数组：");
        Integer[] arrray = {1, 2, 3};
        for (Integer el : arrray) {
            System.out.println(el);
        }

        System.out.println("--- 遍历 Iterable 类型：");
        Classroom clsroom = new Classroom("a", "b", "c");
        for (String el : clsroom.getStudents()) {
            System.out.println(el);
        }
        // 实现 Iterable 直接使用 for-each 方式遍历：
        for (String el : clsroom) {
            System.out.println(el);
        }


        System.out.println("\n--- 集合遍历4：函数式遍历");
        // forEach 接收函数式接口类型参数，可以使用非常简洁的写法，如下是不断简化的过程：
        list1.forEach(new Consumer<Integer>() {
            @Override
            public void accept(Integer el) {
                System.out.println("--- item = " + el);
            }
        });

        list1.forEach((i) -> {
            System.out.println("--- i = " + i);
        });

        list1.forEach((i) -> System.out.println("--- i = " + i));

        list1.forEach(System.out::println);
    }
}
