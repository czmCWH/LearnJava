package com.czm;

public class d2_memory {
    public static void main(String[] args) {
         /*
         ⚠️⚠️⚠️ Java 内存分配：
            方法区：字节码文件先加载到这里，如：xx.class。
            栈内存：方法运行时所进入的内存，以及一些变量。
            堆内存：new出来的都会在此处开辟空间，并产生地址
         */
        int a = 20;		// a是变量，存储在栈内存，a变量中存储的是20这个数值。

        // arr是一个变量，存储在栈内存，arr中存储的是数组对象在对内存中的地址值。
        // new int[3] 是一个数组对象，会在堆内存中开辟区域存储3个整数
        int[] arr = new int[3];

        System.out.println("-----------------");

        /*
         2、多个变量指向同一个数组对象
            多个数组变量中存储的是同一个数组对象的地址。
            多个变量修改的都是同一个数组对象中的数据。
         */
        int[] arr1 = {11, 222, 3333};
        int[] arr2 = arr1;

        // arr1 和 arr2 打印的地址值一样
        System.out.println(arr1);
        System.out.println(arr2);

        arr2[2] = 3;
        System.out.println(arr1[2]);    // 打印：3

        // 3、如果某个数组变量存储的地址是null，那么该变量将不再指向任何数组对象
        // 不能用这个数组变量去访问数据或者访问数组长度，会报空指针异常。
        arr2 = null;
        System.out.println(arr2);
//        System.out.println(arr2[2]);    // 报错：NullPointerException
    }
}
