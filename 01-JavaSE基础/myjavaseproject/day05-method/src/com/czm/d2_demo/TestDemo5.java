package com.czm.d2_demo;

public class TestDemo5 {
    /*
     1、案例：数组拷贝
        把一个数组复制出新的一个新的数组。
     2、本案例知识点：
        复制引用类型；
     */
    public static void main(String[] args) {
        int[] arr1 = {1,2,3};
        System.out.println("---原始数组对象 = "+ arr1);
        int[] arr2 = copyArray(arr1);
        System.out.println("---新数组对象 = " + arr2);

    }

    public static int[] copyArray(int[] array) {

        // 1、创建新数组对象
        int[] arr = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            arr[i] = array[i];
        }
        return arr;
    }
}
