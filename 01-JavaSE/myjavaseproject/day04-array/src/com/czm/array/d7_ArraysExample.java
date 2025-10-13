package com.czm.array;

import java.util.Arrays;
import java.util.Comparator;

public class d7_ArraysExample {

    /*
     1、案例 - 对象类型数组排序
        方式一：让该对象的类实现 Comparable (比较规则)接口，然后重写 compareTo 方法，自己来制定比较规则。
        方式二：使用 sort的自定义排序规则 方法，创建 Comparator 比较器接口 的匿名内部类对象，然后自己制定比较规则。

     */
    public static void main(String[] args) {
        Student[] students = new Student[5];
        students[0] = new Student("周芷若", 18, '女', 169.5);
        students[1] = new Student("李莫愁", 48, '女', 160);
        students[2] = new Student("黄蓉", 56, '女', 155.5);
        students[3] = new Student("小龙女", 28, '女', 175.5);
        students[4] = new Student("小花", 16, '女', 145);

        // 通过方式一排序
        System.out.println("----- 方式一：实现 Comparable 接口，再通过  Arrays.sort 排序");
        Arrays.sort(students);
        System.out.println(Arrays.toString(students));

        System.out.println("-------------------------");
        Arrays.sort(students, new Comparator<Student>() {
            // 参数o1：比较者；参数o2：被比较者
            @Override
            public int compare(Student o1, Student o2) {
                // 方式1:
                // ⚠️：不安全，因为浮点数计算不安全
//                return (int) (o1.getHeight() - o2.getHeight());

                // 方式2:
//                if (o1.getHeight() > o2.getHeight()) {
//                    return 1;
//                } else if (o1.getHeight() < o2.getHeight()) {
//                    return -1;
//                }
//                return 0;

                // // 方式3:
                return Double.compare(o1.getHeight(), o2.getHeight());
            }
        });
        System.out.println(Arrays.toString(students));
    }
}
