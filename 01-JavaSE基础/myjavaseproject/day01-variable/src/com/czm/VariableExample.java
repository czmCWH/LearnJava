package com.czm;

public class VariableExample {
    public static void main(String[] args) {

        // 1、字符的存储原理，存储的是字符的ASCII码编号的二进制
        System.out.println('a' + 1);    // 打印：98

        int asciiValue = 97;
        char character = (char) asciiValue;
        System.out.println("ASCII码 " + asciiValue + " 对应的字符是: " + character);

        // 2、使用加号('+')进行字符串的拼接
        int age = 18;
        String name = "jack";
        double height = 1.5;
        System.out.println("my name is " + name + " and age is " + age + " and height is " + height);

    }
}
