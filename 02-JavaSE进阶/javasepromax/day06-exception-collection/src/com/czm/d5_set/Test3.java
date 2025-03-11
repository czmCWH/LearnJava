package com.czm.d5_set;

import java.util.HashSet;
import java.util.Set;

public class Test3 {
    /*
     1、理解 HashSet 集合去重

     2、
     */
    public static void main(String[] args) {
        Set<Student> sets = new HashSet<>();
        Student s1 = new Student("小明", "篮球");
        Student s2 = new Student("小红", "羽毛球");
        Student s3 = new Student("小明", "篮球");
        Student s4 = new Student("小李", "跑步");

        sets.add(s1);
        sets.add(s2);
        sets.add(s3);
        sets.add(s4);
        System.out.println(sets.size());        // 打印：4

        // 为什么 HashSet 集合中 s1 和 s3 同时存在？
        // 引用 s1 和 s3 是不同的对象，对应的 hashCode 值不同，而 HashSet 集合是根据 hashCode 值相同来去重的。

        // HashSet 集合中只有对象的 hashCode 相同，且对象相等才表示重复了，
        // 因此可以重写对象的 hashCode、equals 方法，来根据需求去重复。

    }
}
