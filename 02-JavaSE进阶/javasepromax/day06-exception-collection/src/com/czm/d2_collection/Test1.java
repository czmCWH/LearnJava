package com.czm.d2_collection;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

public class Test1 {
    /*
     1、集合
     集合是一种容器，用来装数据的，类似于数组，但集合的大小可变，开发中也非常常用

     2、集合分为2类：
	    单列集合(Collection)：代表单列集合，每个元素(数据)只包含一个值。
	    双列集合(Map)：代表双列集合，每个元素包含两个值(键值对)。

     3、常见的 Collection 集合体系
        Collection<E>
            List<E>
                ArrayList<E>
                LinkedList<E>
            Set<E>
                HashSet<E>
                    LinkedHashSet<E>
                TreeSet<E>

         ⚠️⚠️开发中经常使用：
            List<String> list = new ArrayList<>();      // 有序
            Set<String> set = new HashSet<>();      // 无序

     4、Collection结合特点：
        List系列集合：添加的元素是有序、可重复、有索引。
            ArrayList、LinkedList：有序、可重复、有索引。

        Set系列集合：添加的元素是无序、不重复、无索引。
            HashSet: 无序、不重复、无索引;
                LinkedHashset: 有序、不重复、无索引。
            TreeSet：按照大小默认升序排序、不重复、无索引。

     */
    public static void main(String[] args) {

        // 1、遵守泛型规范创建 ArrayList 类型集合对象
        List<String> list = new ArrayList<>();
        list.add("AA");
        list.add("BB");
        list.add("哈哈");
        list.add("嘻嘻");
        System.out.println(list.get(0));
        System.out.println(list.get(1));

        // 2、Set集合：无序、不重复、无索引;
        HashSet<String> set = new HashSet<>();
        set.add("哈哈");
        set.add("嘻嘻");
        set.add("AA");
        set.add("BB");
        System.out.println(set);
    }
}
