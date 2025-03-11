package com.czm.d4_list;

import java.util.ArrayList;

public class Test1 {
    /*
     1、ArrayList、LinkedList 的异同
        它们底层采用的数据结构不同，应用场景不同。
        数据结构：表示存储、组织数据的方式。

     2、ArrayList 的特点
        ArrayList 集合是基于数组实现的。
        根据索引查询数据快！因为，数组中存储每个元素的大小相同，查询数据通过地址值和索引定位，当查询任意数据耗时相同。
        删除效率低：可能需要把后面很多的数据进行前移。
        添加效率极低：可能需要把后面很多的数据后移，再添加元素；或者也可能需要进行数组的扩容。

        总结：查询快，相对而言 增删慢。

     3、ArrayList 集合的底层原理
        利用无参构造器创建的集合，会在底层创建一个默认长度为 0 的数组；
        添加第一个元素时，底层会创建一个新的长度为 10 的数组；
        存满时，会扩容1.5倍；
        ArrayList 删除数据时是基于迁移算法实现的；

     4、ArrayList 集合适合的应用场景
        1、ArrayList适合：根据索引查询数据，比如：根据随机索引取数据(高效)；或者数据量不是很大时!
        2、ArrayList不适合：数据量大的同时，又要频繁的进行增删操作！

     */
    public static void main(String[] args) {

        ArrayList<String> list = new ArrayList<>();
        list.add("哈哈1");
        list.add("哈哈2");
        list.add("哈哈3");
        list.add("哈哈4");
        list.add("哈哈5");
        list.add("哈哈1");
        list.add("哈哈2");
        list.add("哈哈3");
        list.add("哈哈4");
        list.add("哈哈5");
        list.add("嘻嘻");

        // 详细配合视频，查看源码学习


    }
}
