package com.czm.d3_file_recursion;

public class Test7 {
    /*
     1、递归案例 - 猴子吃桃
        一个有n个桃子，猴子第一天吃 1/n + 1个桃子，之后每天吃 剩余桃子的一半 + 1个桃子，到了第10天只剩下1个，
        请问猴子一共有多少个桃子？
        f(x + 1) = f(x) - f(x)/2 -1;
        2f(x + 1) = 2f(x) - fx() - 2;
        f(x) = 2f(x+ 1) + 2;
        f(10) = 1;


     */
    public static void main(String[] args) {
        System.out.println("第 1 天的桃子个数 = " + f(1));
        System.out.println("第 2 天的桃子个数 = " + f(2));
    }

    public static int f(int num) {
        if (num == 10) {
            return 1;
        }
        return 2*f(num + 1) + 2;
    }
}
