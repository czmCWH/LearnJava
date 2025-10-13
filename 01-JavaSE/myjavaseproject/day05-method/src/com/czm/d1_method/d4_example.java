package com.czm.d1_method;

public class d4_example {

    /*
     1、方法递归
      递归是一种算法，在程序设计语言中广泛应用，
      从形式上说：方法调用自身的形式称为方法递归(recursion)

     2、递归的形式
        直接递归：方法自己调用自己
        间接递归：方法调用其他方法，其他方法又回调方法自己

     */

    public static void main(String[] args) {
        int[] arr = new int[]{11, 33, 55, 88, 66};
        printArray(arr);
        printArray(null);

        int num = sum(4);
        System.out.println("num = " + num);
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
        if (n <= 1) return n;   // 结束递归的条件
        return sum(n - 1) + n;
    }

    /*
     3、递归案例1： 递归求阶乘
      递归算法3要素：
        a、递归的公式：f(n) = f(n-1)*n
        b、递归的终结点：f(1)
        c、递归的方向必须走向终结点
     */
    public static int factory(int num) {
        if (num == 1) {
            return 1;
        }
        return num * factory(num - 1);
    }

    /*
     4、递归案例2：- 猴子吃桃
        一个有n个桃子，猴子第一天吃 1/n + 1个桃子，之后每天吃 剩余桃子的一半 + 1个桃子，到了第10天只剩下1个，
        请问猴子一共有多少个桃子？
        f(x + 1) = f(x) - f(x)/2 -1;
        2f(x + 1) = 2f(x) - fx() - 2;
        f(x) = 2f(x+ 1) + 2;
        f(10) = 1;
     */
    public static int f(int num) {
        if (num == 10) {
            return 1;
        }
        return 2*f(num + 1) + 2;
    }

}
