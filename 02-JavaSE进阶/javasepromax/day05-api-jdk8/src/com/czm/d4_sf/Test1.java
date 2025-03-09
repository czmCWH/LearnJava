package com.czm.d4_sf;

import java.util.Arrays;

public class Test1 {
    /*
     1、什么是算法?
        解决某个实际问题的过程和方法

     2、学习算法的技巧
        1、先搞清楚算法的流程。
        2、直接去推敲如何写代码。

     3、排序算法
        a、冒泡排序：
            每次从数组中找出最大值放在数组的后面去。
            即：让较大的数向右移动。
        b、选择排序：
            每轮选择当前位置，开始找出后面的较小值与该位置交换。（站在那儿不动，谁比我小，我就和谁交换。）
            即：让较小的数向左移动。


     */
    public static void main(String[] args) {

        bubbleSort();
        selectionSort();
        selectionSort2();
    }

    // 冒泡排序
    public static void bubbleSort() {
        System.out.println("--- 冒泡排序");
        int[] arr = {5, 2, 7, 1};
        // 轮数   每轮的比较次数
        //  1          3
        //  2          2
        //  3          1
        // 结束

        // 1、外循环需要冒泡几轮
        for (int i = 0; i < arr.length - 1; i++) {
            // i：轮数
            // j：每伦的次数
            // 2、内循环控制每轮比较几次
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {  // 比较交换位置
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }


    public static void selectionSort() {
        System.out.println("--- 选择排序");
        int[] arr = {5, 2, 7, 1};
        // 1、外循环控制比较的轮次
        for (int i = 0; i < arr.length - 1; i++) {
            // 2、内循环控制选择次数
            // 轮数   次数  j占位
            //  0      3    1、2、3
            //  1。    2。   2、3
            //  2。    1。   3
            for (int j = i + 1; j < arr.length; j++) {
                // j 当前位置 是否小于选中的位置
                if (arr[j] < arr[i]) {
                    int temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void selectionSort2() {
        System.out.println("--- 选择排序");
        int[] arr = {5, 2, 7, 1};
        // 1、外循环控制比较的轮次
        for (int i = 0; i < arr.length - 1; i++) {
            // 2、内循环控制选择次数
            // 定义一个值作为本轮最小值的索引
            int min = i;
            for (int j = i + 1; j < arr.length; j++) {
                // j 当前位置 是否小于选中的位置
                if (arr[j] < arr[min]) {
                    min = j;        // ⚠️：性能优化，减少交换数据次数。
                }
            }
            if (min != i) {
                int temp = arr[i];
                arr[i] = arr[min];
                arr[min] = temp;
            }
        }
        System.out.println(Arrays.toString(arr));
    }
}
