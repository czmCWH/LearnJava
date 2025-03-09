package com.czm.d2_Lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.IntToDoubleFunction;

public class Test2 {
    /*
     1、Lambda 简化代码案例

     2、Lambda表达式的省略写法
        参数类型可以省略不写
        如果只有一个参数，参数类型可以省略，同时()也可以省略
        如果 Lambda 表达式中的 方法体代码只有一行代码，可以省略大括号不写，同时要省略分号!，此时，如果这行代码是 return 语句，也必须去掉 return 不写。
     */
    public static void main(String[] args) {

        double[] scores = {70, 85.5, 76.5, 80, 87.5};
        // 参数2为 interface 类型，我们通过匿名内部类创建一个接口的对象来传入。
        Arrays.setAll(scores, new IntToDoubleFunction() {
            @Override
            public double applyAsDouble(int value) {
                return scores[value] + 10;
            }
        });
        System.out.println("---- 使用 Lambda 简化 ");
        Arrays.setAll(scores, (int value) -> {
            return scores[value] + 10;
        });
        System.out.println("----  Lambda 省略简写 ");
        Arrays.setAll(scores, index -> scores[index] + 10);


        System.out.println();
        System.out.println("--------------------------------------");

        Student[] students = new Student[5];
        students[0] = new Student("周芷若", 18, '女', 169.5);
        students[1] = new Student("李莫愁", 48, '女', 160);
        students[2] = new Student("黄蓉", 56, '女', 155.5);
        students[3] = new Student("小龙女", 28, '女', 175.5);
        students[4] = new Student("小花", 16, '女', 145);

        Arrays.sort(students, new Comparator<Student>() {
            // 参数o1：比较者；参数o2：被比较者
            @Override
            public int compare(Student o1, Student o2) {
                return Double.compare(o1.getHeight(), o2.getHeight());
            }
        });

        System.out.println(Arrays.toString(students));

        System.out.println("--- 使用 Lambda 简化");
        Arrays.sort(students, (Student o1, Student o2) -> {
            return Double.compare(o1.getHeight(), o2.getHeight());
        });

        System.out.println("--- Lambda 省略简写");
        Arrays.sort(students, (o1, o2) -> Double.compare(o1.getHeight(), o2.getHeight()));
    }
}
