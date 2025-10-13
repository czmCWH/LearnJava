package com.czm.d5_set_x;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class Test5 {
    /*
     1、TreeSet 集合
        特点：不重复、无索引、可排序(默认升序排序，按照元素的大小，由小到大排序）
        底层是基于红黑树实现的排序。
        注意：
            对于数值类型：Integer，Double，默认按照数值本身的大小进行升序排序。
            对于字符串类型：默认按照首字符的编号升序排序。
            对于自定义类型的对象，TreeSet默认是无法直接排序的。

     2、自定义 TreeSet 集合的排序规则
        TreeSet 集合存储自定义类型的对象时，必须指定排序规则，支持如下两种方式来指定比较规则。
        方式一：
            让自定义的类实现 Comparable 接口，重写里面的 compareTo 方法来指定比较规则。

        方式二：
            通过调用 TreeSet 集合有参数构造器，可以设置 Comparator 对象(比较器对象，用于指定比较规则。
     */
    public static void main(String[] args) {
        // 方式1
        Set<Dog> sets = new TreeSet<>();
        sets.add(new Dog("白色", 2.5));
        sets.add(new Dog("黑色", 1));
        sets.add(new Dog("黄色", 1.5));
        sets.add(new Dog("黑色", 3));
        sets.add(new Dog("黑色", 1));
        System.out.println(sets.size());

        // 方式2
        Set<Dog> sets2 = new TreeSet<>(new Comparator<Dog>() {
            @Override
            public int compare(Dog o1, Dog o2) {
                return Double.compare(o1.getAge(), o2.getAge());
            }
        });

    }
}
