package com.czm;

public class d3_param {

    public static void main(String[] args) {
        /*
         1、Java的参数传递机制都是：值传递
            所谓值传递：指的是在传输实参给方法的形参的时候，传输的是实参变量中存储的值的副本
                a、基本数据类型（byte、short、char、int、long、float、double、boolean）作为方法参数 或 方法返回值 传递的是数据值；--- 值传递
                b、引用类型（String、类、接口、数组）作为方法参数 或 方法返回值 传递的是引用对象的地址值；--- 引用传递
         */
        int a = 10;
        change(a);
        System.out.println("a = " + a);

        int[] arr = new int[]{10, 20, 30};
        changeRef(arr);
        System.out.println("arr[1] = " + arr[1]);   // 打印：222

        int[] n = test();   // 执行 test 方法后，其内部的 arr 局部变量的内存会回收，返回数组的地址给 n 存储，数组不销毁。
        for (int i = 0; i < n.length; i++) {
            System.out.println(n[i]);
        }
    }

    public static void change(int a) {
        System.out.println("---change1 = " + a);
        a = 20;
        System.out.println("---change2 = " + a);
    }

    public static void changeRef(int[] arr) {
        System.out.println("---changeRef1 = " + arr[1]);
        arr[1] = 222;
        System.out.println("---changeRef2 = " + arr[1]);
    }

    public static int[] test() {
        int[] arr = {1, 2, 3, 4, 5};
        return arr;
    }

}
