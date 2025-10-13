package com.czm.d3_method_reference;

import java.util.Arrays;

public class Test2 {

    /*
     1、实例方法引用
        语法：对象名::实例方法

     2、使用场景
        如果某个 Lambda表达式 里只是调用一个实例方法，并且前后参数的形式一致，就可以使用实例方法引用。
     */
    public static void main(String[] args) {
        Student[] students = new Student[5];
        students[0] = new Student("周芷若", 18, '女', 169.5);
        students[1] = new Student("李莫愁", 48, '女', 160);
        students[2] = new Student("黄蓉", 56, '女', 155.5);
        students[3] = new Student("小龙女", 28, '女', 175.5);
        students[4] = new Student("小花", 16, '女', 145);

//        Test2 t = new Test2();
//        Arrays.sort(students, t.compareByHeight(o1, o2));
        System.out.println("--- 使用 实例方法引用 简化 Lamdba 表达式");
        Arrays.sort(students, new Test2()::compareByHeight);
        System.out.println(Arrays.toString(students));
    }

    public int compareByHeight(Student o1, Student o2) {
        return Double.compare(o1.getAge(), o2.getAge());
    }
}
