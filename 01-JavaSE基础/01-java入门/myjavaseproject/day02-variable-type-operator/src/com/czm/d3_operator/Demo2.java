package com.czm.d3_operator;

public class Demo2 {

    // 1、自增、自减案例

    public static void main(String[] args) {
        int m = 3;
        int n = 7;
        int rs = m++ + ++m - --m + ++n - n-- + m++ + 1;
//        3 + 5 - 4 + 8 - 8 + 4 + 1
        System.out.println(m); // 5
        System.out.println(n); // 7
        System.out.println(rs);// 9
    }
}
