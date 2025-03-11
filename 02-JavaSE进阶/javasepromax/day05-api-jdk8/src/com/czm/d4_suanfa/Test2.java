package com.czm.d4_suanfa;

public class Test2 {
    /*
     1、二分查找（折半查找）
        前提：数组中的数据必须是排序好的。
        核心思想：每次排除一半的数据，查询数据的性能明显提高极多!

     2、
     */
    public static void main(String[] args) {
        int[] arr = { 2, 10, 34, 45, 56, 78, 78, 88, 97, 106};
        System.out.println("---查找 45 的索引：" + searchDataIndex(arr, 45));
        System.out.println("---查找 88 的索引：" + searchDataIndex(arr, 88));
        System.out.println("---查找 100 的索引：" + searchDataIndex(arr, 100));

    }
    public static int searchDataIndex(int[] arr, int target) {
        // 1、定义首尾指针
        int left = 0;
        int right = arr.length - 1;
        // 2、开始二分法查找
        while (left <= right) {
            int mid = (left + right) / 2;
            if (target > arr[mid]) {
                // 往右边找
                left = mid + 1;
            } else if (target < arr[mid]) {
                // 往左边找
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
