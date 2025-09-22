package com.czm.d3_collections.Test01ArrayList;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Test04ListIterator {
    /*
      1、ListIterator 接口
      ListIterator 继承自 Iterator，在 Iterator 的基础上增加了一些功能。
      常用方法如下：
        boolean hasNext(); 是否有下一个元素
        E next(); cursor 游标向后移动一位，取出当前指向的元素
        boolean hasPrevious(); 是否存在前一个元素
        E previous(); 与 next 作用相反；
        int nextIndex();
        int previousIndex();
        void remove(); 删除 cursor 游标指向的元素；
        void set(E e); 修改 cursor 游标指向的元素；
        void add(E e); 在 cursor 游标位置新增元素；

     */
    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(12);
        list.add(13);

        ListIterator<Integer> it1 = list.listIterator();
        System.out.println("--- ListIterator 正向遍历：");
        while (it1.hasNext()) {
            System.out.println(it1.next());
        }
        System.out.println("\n--- ListIterator 反向遍历：");
        while (it1.hasPrevious()) {
            System.out.println(it1.previous());
        }
        System.out.println("\n--- ListIterator 遍历修改元素的值：");
        while (it1.hasNext()) {
            it1.set(it1.next() + 10);
        }
        System.out.println(list);   // [21, 22, 23]

        System.out.println("\n--- ListIterator 遍历过程中，新增元素不会影响遍历的结果：");
        ListIterator<Integer> it = list.listIterator();
        while (it.hasNext()) {
            it.add(10);
            System.out.println(it.next());
            it.add(20);
        }
        System.out.println(list);   // [10, 21, 20, 10, 22, 20, 10, 23, 20]
    }
}
