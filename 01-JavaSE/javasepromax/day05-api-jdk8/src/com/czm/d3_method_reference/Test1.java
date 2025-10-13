package com.czm.d3_method_reference;


import java.util.Arrays;

public class Test1 {
    /*
    ⚠️：Lamdba 用来简化函数式接口；方法引用 用来简化 Lamdba 表达式。

     1、方法引用分类：
        静态方法引用；
        实例方法引用；
        特定类型方法的引用；
        构造器引用；

    2、静态方法引用
	    语法：类名::静态方法
	    使用场景：如果某个 Lambda表达式 里只是调用一个静态方法，并且前后参数的形式一致，就可以使用静态方法引用。
     */
    public static void main(String[] args) {

        Student[] students = new Student[5];
        students[0] = new Student("周芷若", 18, '女', 169.5);
        students[1] = new Student("李莫愁", 48, '女', 160);
        students[2] = new Student("黄蓉", 56, '女', 155.5);
        students[3] = new Student("小龙女", 28, '女', 175.5);
        students[4] = new Student("小花", 16, '女', 145);

//        Arrays.sort(students, (o1, o2) -> Double.compare(o1.getHeight(), o2.getHeight()));
//        Arrays.sort(students, (o1, o2) -> Student.compareByHeight(o1, o2)) ;

        System.out.println("--- 使用 静态方法简化 简化 Lamdba 表达式");
        Arrays.sort(students, Student::compareByHeight) ;
        System.out.println(Arrays.toString(students));
    }
}
