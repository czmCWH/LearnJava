package com.czm.d3_file_recursion;

public class Test5 {
    /*
     1、方法递归
        递归是一种算法，在程序设计语言中广泛应用，
        从形式上说：方法调用自身的形式称为方法递归(recursion)

     2、递归的形式
        直接递归：方法自己调用自己
        间接递归：方法调用其他方法，其他方法又回调方法自己

     */
    public static void main(String[] args) {
        // 递归调用异常：StackOverflowError，栈内存溢出，因为递归造成死循环把栈内存占满了。
//        test();
    }

    public static void test() {
        System.out.println("---test");
        test();
    }
}
