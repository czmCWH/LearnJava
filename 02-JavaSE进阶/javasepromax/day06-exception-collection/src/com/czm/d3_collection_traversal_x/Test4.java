package com.czm.d3_collection_traversal_x;


import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;


public class Test4 {
    /*
     1、List<E> 单列集合
        ArrayList、LinkedList 都是有序、可重复、有索引；但是，它们底层采用的数据结构不同，应用场景不同。
        List 集合因为支持索引，所以多了很多与索引相关的方法，当然，Collection 的功能 List 也都继承了。

     2、List<E> 单列集合的特有功能

     3、List<E> 单列集合 遍历数据
        a、迭代器
        b、增强for循环
        c、Lambda表达式
        以上继承自 Collection 集合
        c、for循环，因为List集合有索引
     */
    public static void main(String[] args) {

        // 1、创建一个List集合对象
        List<String> list = new ArrayList<>();  // 多态
        list.add("小龙女");
        list.add("赵敏");
        list.add("周芷若");
        System.out.println(list);
        
        // 2、在指定索引位置插入数据
        list.add(2, "小燕子");
        System.out.println("---根据索引插入数据 = " + list);

        // 3、根据索引删除数据
        System.out.println("---根据索引删除数据 = " + list.remove(2));
        System.out.println(list);

        // 4、根据索引修改数据
        list.set(1, "灭绝师太");
        System.out.println(list);

        // 5、根据索引获取数据
        System.out.println(list.get(1));

        System.out.println();
        System.out.println("-----a、迭代器遍历");
        Iterator<String> it = list.iterator();
        while (it.hasNext()) {
            String str = it.next();
            System.out.println(str);
        }

        System.out.println();
        System.out.println("-----b、增强for循环");
        for (String str : list) {
            System.out.println(str);
        }

        System.out.println();
        System.out.println("-----c、Lambda表达式");
        list.forEach(s -> System.out.println(s));
        list.forEach(System.out::println);

        System.out.println();
        System.out.println("-----d、for循环");
        for (int i = 0; i < list.size(); i++) {
            String s = list.get(i);
            System.out.println(s);
        }

    }
}
