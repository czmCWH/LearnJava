package com.czm.d2_collections_x;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Test1 {
    /*
     1、Collections 工具类
        Collections 是一个用来操作集合的工具类，它提供了许多静态方法。

        ⚠️：Collections 提供的方法只能操作 List 单列集合。
     3、
     */
    public static void main(String[] args) {

        System.out.println("--- 1、为集合批量添加数据");
        List<String> names = new ArrayList<>();
        Collections.addAll(names, "周瑜", "曹操", "刘备", "吕布");
        System.out.println(names);

        System.out.println("--- 2、打乱集合中元素的顺序");
        Collections.shuffle(names);     // 案例：抽奖、洗牌
        System.out.println(names);

        System.out.println("--- 3、对集合中的元素生序排序");
        List<Double> nums = new ArrayList<>();
        Collections.addAll(nums, 5.8, 2.0, 9.0, 1.32, 7.9, 6.0);
        System.out.println(nums);
        Collections.sort(nums);
        System.out.println(nums);

        System.out.println("--- 4、对自定义类型排序");
        List<Person> list = new ArrayList<>();
        Person p1 = new Person("郭嘉", 38, 170.0);
        Person p2 = new Person("诸葛亮", 28, 183.5);
        Person p3 = new Person("司马懿", 42, 175.5);
        Person p4 = new Person("荀彧", 50, 171.3);
        list.add(p1);
        list.add(p2);
        list.add(p3);
        list.add(p4);
        System.out.println(list);

        // 方式一: 让对象的类实现 Comparable 接口，重写 compare 方法，指定大小比较规则
        Collections.sort(list);
        System.out.println("方式一 = " + list);

        // 方式二：指定 Comparator 比较器对象，再指定比较规则
        Collections.sort(list, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return Double.compare(o1.getHeight(), o2.getHeight());
            }
        });
        // 从小到大，生序
        // 从大到小，降序
        System.out.println("方式二 = " + list);
    }
}
