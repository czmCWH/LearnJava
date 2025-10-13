package com.czm.d8_generics;

import com.czm.d8_generics.demo.Box;
import com.czm.d8_generics.demo.Person;
import com.czm.d8_generics.demo.Student;

import java.util.ArrayList;
import java.util.List;

public class Test04Func {
    /*
     1、泛型方法
      泛型方法是指 方法声明中的使用了泛型的方法(实例方法、静态方法、构造方法)。
      语法：
        修饰符 <类型参数1, 类型参数2...> 返回值类型 方法名(形参列表) {
	        // 方法实现
	    }

     */
    public static void main(String[] args) {

        Student<String, Integer> stu = new Student<>("一年级03", 66);

        set(stu, "二年级11", 90);

        System.out.println("--- 1、定义泛型方法：");
        Student<Integer, String> stu2 = new Student<>(10, "优秀");
        // 泛型方法的完整写法：
        Test04Func.<String, Integer>set(stu, "三年级22", 100);
        // 编译器类型推断，简写：
        set(stu2, 10, "良好");

        System.out.println("\n--- 2、泛型方法：");
        List<Box<Integer>> boxes = new ArrayList<>();
        addBox(1, boxes);
        addBox(2, boxes);
        addBox(3, boxes);

        System.out.println("\n--- 3、泛型方法 - 构造方法：");
        Person<Integer> p1 = new Person<>("张三", 20);
        Person<Double> p2 = new Person<>(666, 1.34);
        Person<String> p3 = new Person<>(12.34, "10后");


    }

    static void set(Student<String, Integer> stu, String n, Integer score) {
        stu.setN(n);
        stu.setScore(score);
    }

    // 定义泛型方法，在方法返回值前书写 类型占位符。
    static <N, S> void set(Student<N, S> stu, N n, S score) {
        stu.setN(n);
        stu.setScore(score);
    }

    static <T> void addBox(T element, List<Box<T>> boxes) {
        Box<T> box = new Box<>(element);
        boxes.add(box);
    }
}

