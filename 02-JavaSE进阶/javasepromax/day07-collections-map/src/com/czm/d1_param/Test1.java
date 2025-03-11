package com.czm.d1_param;

import java.util.Arrays;

public class Test1 {
    /*
     1、可变参数
        是一种特殊形参，它定义在方法、构造器的形参列表里。
        语法格式是：数据类型...参数名称;

     2、可变参数的特点和好处
        特点:
            可以不传数据给它可以传一个或者同时传多个数据给它，也可以传一个数组给它。
        好处：
            常常用来灵活的接收数据。

     3、可变参数注意点
        a、可变参数在方法内部就是一个数组；
        b、可变参数在形参列表中只能出现一个
        c、可变参数必须放在形参列表的最后面！
     */
    public static void main(String[] args) {

        // 案例，求任意个数的整数的和
        sumInt(1,3,6,9);
    }

    public static void sumInt(int...nums) {
        // 本质：可变参数在方法内部就是一个数组
        System.out.println(nums.length);
        System.out.println(Arrays.toString(nums));

    }
}
