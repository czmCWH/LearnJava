package com.czm.d3_collections.Test01ArrayList;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test03SafeRemove {

    /*
       ArrayList 遍历元素，安全的删除元素，多种案例实现：

       1、集合的并发修改异常
        使用迭代器遍历集合时，又同时在删除集合中的数据，程序就会出现并发修改异常的错误。
     */

    public static void main(String[] args) {

        List<Integer> list = new ArrayList<>();
        list.add(11);
        list.add(22);
        list.add(33);
        list.add(44);
        list.add(55);
        // 如下代码实现一边遍历，一边删除元素，会有问题，因为 ArrayList 是一个动态数组。
        // 方式1，报错！因为 size 是固定的，而每删除一个元素数组的容量会变小，当删第3个元素时就报错 数组越界了。
//        int size = list.size();
//        for (int i = 0; i < size; i++) {
//            list.remove(i);
//        }

        // 方式2，无法删彻底！！！此时 每次都会获取 list.size()，这个值是动态的，会导致漏删元素
//        for (int i = 0; i < list.size(); i++) {
//            list.remove(i);
//        }
//        System.out.println(list);   // [22, 44]，未全部删除干净

        // 正确实现1：每删除一个元素，把 for 循环遍历索引后退一步
        for (int i = 0; i < list.size(); i++) {
            list.remove(i);
            i--;
        }
        System.out.println("--- list = " + list);


        // 方式3，报错：java.util.ConcurrentModificationException，并发修改异常
//        for (Integer e : list) {
//            list.remove(e);
//        }
//        list.forEach((e) -> {
//            list.remove(e);
//        });

        // 方式4，迭代器方式，
        // 报错：java.util.ConcurrentModificationException，并发修改异常
//        Iterator<Integer> it = list.iterator();
//        while (it.hasNext()) {
//            list.remove(it.next());   // 👉 使用集合自带的方法删除元素
//        }
        // 正确实现2，使用 Iterator 的方法来删除元素：
        Iterator<Integer> it = list.iterator();
        while (it.hasNext()) {
            it.next();  // 注意，必须先每次拿一下，拿到元素对象后，才能进行删除！
            it.remove();    // 👉 使用 Iterator 的方法来删除元素。
        }
        System.out.println(list);

        /*
          结论：使用 迭代器、forEach 方法遍历集合元素时，使用了集合自带的方法修改集合的长度（比如：add、remove 等方法）
               那么可能抛出 java.util.ConcurrentModificationException 异常。
          原因：因为使用 Iterator 迭代器遍历时，cursor(游标) 记录元素的位置。此时使用 List 的 add/remove 修改了集合的长度，影响了 cursor 的遍历操作，所以报错！
          实现：异常监控实现是通过检测 modCount 修改次数来实现的！
         */

    }
}
