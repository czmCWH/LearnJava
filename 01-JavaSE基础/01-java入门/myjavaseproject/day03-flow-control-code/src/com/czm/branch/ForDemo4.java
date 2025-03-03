package com.czm.branch;

public class ForDemo4 {
    public static void main(String[] args) {
        /*
         1、for 循环
         控制一段代码反复执行很多次。
         for循环格式：
            for(初始化语句；循环条件；迭代语句){
                // 循环体语句（可重复执行的代码）
            }
         for循环常见的应用场景：
         减少代码的重复编写，灵活的控制程序的执行。
         */
        for (int i = 0; i < 3; i++) {
            System.out.println("hello world!" + i);
        }

        for (int i = 1; i < 7; i += 2) {
            System.out.println("hello world!" + i);
        }

    }
}
