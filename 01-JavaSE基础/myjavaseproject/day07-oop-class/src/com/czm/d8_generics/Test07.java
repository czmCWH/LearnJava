package com.czm.d8_generics;

import com.czm.d8_generics.demo.Box;
import com.czm.d8_generics.demo.BoxRaw;

import java.util.ArrayList;

public class Test07 {
    /*
      1、泛型的使用限制 - 难！
        a、基本数据类型不能作为类型参数，如：int、char 等；
        b、不能通过类型参数来创建实例；
        c、不能定义类型为类型参数的 static 变量；
        g、不能定义泛型的异常类型；
        h、catch 的异常类型不能用异常类型；

     */
    public static void main(String[] args) {


        // d、类型参数不能和 instanceof 一起使用；
        ArrayList<Integer> list = new ArrayList<>();
//        if (list instanceof ArrayList<Integer>) {}  // 报错！
        if (list instanceof ArrayList) {
            System.out.println("ArrayList");
        }

        // e、不能创建带有类型参数的数组
//        Box<Integer>[] boxs1 = new Box<Integer>[4];     // 报错！
        Box<Integer>[] boxs2 = new Box[4];


    }

    static <E> void test(BoxRaw<E> box) {
//        E el = new E();   // 报错！

    }


    // f、如下方法不构成重载
//    void test(BoxRaw<Integer> box) {
//
//    }
//
//    void test(BoxRaw<String> box) {
//
//    }

}
