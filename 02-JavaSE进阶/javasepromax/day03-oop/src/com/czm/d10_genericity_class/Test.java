package com.czm.d10_genericity_class;

public class Test {
    /*
     1、定义泛型类语法
        修饰符 class 类名<类型变量1,类型变量2... > {
            // 类的实现
        }
        ⚠️：类型变量建议用大写的英文字母，常用：E、T、K、V等。
     2、
     */
    public static void main(String[] args) {
        MyArrayList<String> myList = new MyArrayList<>();
        myList.add("好好学习");
        myList.remove("不读书");
    }
}
