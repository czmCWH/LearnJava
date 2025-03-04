package com.czm.create;

public class MethodDemo5 {

    // 案例：比较两个int类型的数组是否一样，返回true或者false

    public static void main(String[] args) {
        int[] a1 = null;
        int[] a2 = new int[]{};
        System.out.println(compareTo(a1, a2));      // false

        int[] b1 = new int[]{11,22,33};
        int[] b2 = new int[]{11,22};
        System.out.println(compareTo(b1, b2));      // false

        int[] c1 = {1, 2, 3, 4, 5};
        int[] c2 = {1, 2, 3, 4, 5};
        System.out.println(compareTo(c1, c2));      // true

    }

    public static boolean compareTo(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {     // 卫语句
            return false;
        }
        if (arr1.length != arr2.length) {    // 卫语句
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }
}
