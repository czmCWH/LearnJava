package com.czm.d3_file_recursion;

public class Test6 {
    /*
     1、递归算法
        案例：求n的阶乘，f(n) = 1*2*3*...*(n-2)*(n-1)*n ?

     2、递归算法3要素
        a、递归的公式：f(n) = f(n-1)*n
        b、递归的终结点：f(1)
        c、递归的方向必须走向终结点

     */
    public static void main(String[] args) {

        System.out.println(factory(5));
        System.out.println(f(5));
    }

    // 递归求阶乘
    public static int factory(int num) {
        if (num == 1) {
            return 1;
        }
        return num * factory(num - 1);
    }

    // 递归1到n的和
    public static int f(int num) {
        if (num == 1) {
            return 1;
        }
        return num + f(num - 1);
    }

}
