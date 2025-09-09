package com.czm.d1_operator;
// 1、导入java包
import java.util.Scanner;

public class ScannerDemo {
    public static void main(String[] args) {
        // 2、创建扫描器对象
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入你的年龄：");
        // 3、使用扫描器接受键盘输入的内容，直到用户按了回车键，才往下执行
        int age = scanner.nextInt();
        System.out.println("你的年龄是：" + age);

        System.out.println("请输入你的姓名：");
        String name = scanner.next();
        System.out.println("欢迎，" + name);

    }
}
