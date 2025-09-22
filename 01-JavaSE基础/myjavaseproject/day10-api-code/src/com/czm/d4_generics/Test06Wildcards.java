package com.czm.d4_generics;

import com.czm.d4_generics.demo.BoxRaw;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test06Wildcards {

    /*
      1、通配符(Wildcards)
       在泛型中，问号（?）被称为是通配符。作用如下：
        <? extends Type>，用于在使用泛型类型时，限制其类型参数是某个 Type 类型 或者 Type 子类型。
        <? super Type>，用于在使用泛型类型时，限制其类型参数是某个 Type 类型 或者 Type 父类型。
        <?>，表示使用泛型类型时，其类型参数可以为任何类型。

      2、通配符应用场景：
       通常用作变量类型、返回值类型的类型参数。
       不能用作泛型方法调用、泛型类型实例化、泛型类型定义的类型参数。

     */

    public static void main(String[] args) {

        BoxRaw<Integer> box1 = new BoxRaw<>();
        BoxRaw<String> box2 = new BoxRaw<>();
        BoxRaw<Object> box3 = new BoxRaw<>();

        showBox(box1);
        showBox(box2);
        showBox(box3);

        // 1、使用通配符后，限制泛型的类型参数，示例1：
        showBox2(box1);
//        showBox2(box2);     // 报错！
//        showBox2(box3);     // 报错！

        // 2、使用通配符后，限制泛型的类型参数，示例2：
        BoxRaw<? extends Number> box4 = null;
        box4 = box1;
//        box4 = box2;    // 报错！
        BoxRaw<? extends Integer> box5 = null;
        showBox2(box4);

        // 3、使用通配符限制集合元素的类型，示例3：
        List<Integer> list1 = Arrays.asList(1, 2, 3);
        System.out.println("sum list1 = " + sum(list1));    // 6.0

        List<Double> list2 = Arrays.asList(1.1, 2.2, 3.3);
        System.out.println("sum list2 = " + sum(list2));    // 6.6

        // 4、<? super Type> 限制泛型的 类型参数为 Type 类型 或者 Type 的父类型
        BoxRaw<Integer> bx1 = null;
        BoxRaw<Double> bx2 = null;
        BoxRaw<? super Integer> bx3 = null;
        BoxRaw<? super Number> bx4 = null;
        bx3 = bx1;
//        bx3 = bx2;  // 报错
//        bx4 = bx1;  // 报错
//        bx4 = bx2;  // 报错
        testLower(bx1);
//        testLower(bx2); //报错
        testLower(bx3);
        testLower(bx4);

        // 5、<? super Type> 案例：
        List<Integer> is = new ArrayList<>();
        addNumbers(is);
        System.out.println("is = " + is);   // is = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
        List<Number> ns = new ArrayList<>();
        addNumbers(ns);
        System.out.println("ns = " + ns);   // ns = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        // 6、通配符 - 无限制，<?>
        BoxRaw<Integer> bx5 = null;
        BoxRaw<Double> bx6 = null;
        BoxRaw<String> bx7 = null;
        BoxRaw<? extends  String> bx8 = null;
        testAll(bx5);
        testAll(bx6);
        testAll(bx7);
        testAll(bx8);

        List<Integer> list3 = Arrays.asList(1, 2, 3);
        List<String> list4 = Arrays.asList("a", "b", "c");
        printList(list3);
        printList(list4);

    }

    static <T> void showBox(BoxRaw<T> box) {

    }

    // ?：表示 BoxRaw 泛型类型的 类型参数 必须是 Number 类型或者 Number的子类型
    static void showBox2(BoxRaw<? extends Number> box) {

    }

    // list 的 元素类型 必须是 Number 类型 或者 Number 的子类型
    static double sum(List<? extends Number> list) {
        double sum = 0;
        for (Number n : list) {
            sum += n.doubleValue();
        }
        return sum;
    }

    // box 的 类型参数 必须是 Integer 类型或者其 父类型
    static void testLower(BoxRaw<? super Integer> box) {
    }

    static void addNumbers(List<? super Integer> list) {
        for (int i = 0; i <= 10; i++) {
            list.add(i);
        }
    }

    // 无限制
    static void testAll(BoxRaw<?> box) {

    }

    static void printList(List<?> list) {
        for (Object o : list) {
            System.out.print(o + " ");
        }
        System.out.println();
    }


    // 👉 7、通配符使用注意：
    static void foo(List<?> list) {
        // list 里面存储的必定是 Object 类型 或者 其子类型，所以不会报错！
        Object obj = list.get(0);

        // 结论：编译器在解析 List<E>.set{int index,E element) 时，无法确定E的真实类型，所以报错!
        // 分析：
        // 在使用泛型时，编译器要求编译时确定其类型参数的真实类型，否则会报错。
        // list 参数的 类型参数为 ?，表示 list 的元素可能为任意类型。因此当 list 调用 set 方法时，给一个不确定元素类型的数组传递 Object 类型会报错！
//        list.set(0, obj); // 报错！
//        list.set(0, list.get(0)); // 报错！
    }
    // 可以通过 泛型 解决
    static <T> void foo2(List<T> list) {
        T obj = list.get(0);
        list.set(0, obj);
        list.set(0, list.get(0));
    }
}
