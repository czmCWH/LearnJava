package com.czm.create;

public class MethodDemo4 {

    // 数组案例，打印输出数组的内容

    public static void main(String[] args) {
        int[] arr = new int[]{11, 33, 55, 88, 66};
        printArray(arr);
        printArray(null);
    }
    // 参数 Arr 接收的是实际参数的地址
    public static void printArray(int[] arr) {
        if (arr == null || arr.length == 0) {   // 卫语句风格代码，做拦截
            System.out.println(arr);
            return;
        }
        System.out.print("[");
        for (int i = 0; i < arr.length; i++) {
//            if (i == arr.length - 1) {
//                System.out.print(arr[i]);
//            } else {
//                System.out.print(arr[i] + ", ");
//            }
            System.out.print(i == arr.length - 1 ? arr[i] : arr[i] + "," );
        }
        System.out.print("]");
        System.out.println();
    }

}
