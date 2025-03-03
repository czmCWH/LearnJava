package com.czm.operator;

public class OperatorDemo2 {
    public static void main(String[] args) {
        // 1、自增、自减案例
        int m = 3;
        int n = 7;
        int rs = m++ + ++m - --m + ++n - n-- + m++ + 1;
        System.out.println(m);
        System.out.println(n);
        System.out.println(rs);
    }
}
