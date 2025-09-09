package com.czm;

public class d4_example {



    public static void main(String[] args) {
        int[] arr = new int[]{11, 33, 55, 88, 66};
        printArray(arr);
        printArray(null);
    }

    // 1、数组案例，打印输出数组的内容
    public static void printArray(int[] arr) {      // 参数 Arr 接收的是实际参数的地址
        if (arr == null || arr.length == 0) {   // ⚠️：卫语句风格代码，做拦截
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

    // 2、递归调用 - 案例
    // 如果递归调用没有终止，将会一直消耗栈空间。最终导致栈内存溢出（Stack Overflow）。
    // 所以必须要有一个明确的结束递归的条件，也叫做边界条件、递归基。
    public static int sum(int n) {
        if (n <= 1) return n;
        return sum(n - 1) + n;
    }
}
