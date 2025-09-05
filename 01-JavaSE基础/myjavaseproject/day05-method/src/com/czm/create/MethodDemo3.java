package com.czm.create;

public class MethodDemo3 {

    public static void main(String[] args) {
        /*
         1、Java的参数传递机制都是：值传递
            所谓值传递：指的是在传输实参给方法的形参的时候，传输的是实参变量中存储的值的副本
                a、基本数据类型传递的是数据值；
                b、引用类型传递的是引用对象的地址值；
         */
        int a = 10;
        change(a);
        System.out.println("a = " + a);

        int[] arr = new int[]{10, 20, 30};
        changeRef(arr);
        System.out.println("arr[1] = " + arr[1]);   // 打印：222
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


}
