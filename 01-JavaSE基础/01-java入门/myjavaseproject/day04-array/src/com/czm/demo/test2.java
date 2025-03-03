package com.czm.demo;

public class test2 {
    public static void main(String[] args) {
        /*
         1、数组反转案例
         即让数组前后数据依次交换：[10，20，30，40，50] 反转后 [50，40，30，20，10]
         */
        int[] arr = { 10, 20, 30, 40, 50 };
        // i 往前走；j 往后走；且 i < j
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int tmp = arr[j];
            arr[j] = arr[i];
            arr[i] = tmp;
        }
        System.out.println("交换后的结果：");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
