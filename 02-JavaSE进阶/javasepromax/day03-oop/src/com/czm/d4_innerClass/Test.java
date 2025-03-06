package com.czm.d4_innerClass;

public class Test {
    /*
     1、匿名内部类【重点】
        所谓匿名就是一种特殊的局部内部类，指的是程序员不需要为这个类声明名字。

     2、定义匿名内部类语法
        new 类或接口(参数列表) {
            类体(一般是方法重写);
        }

     3、⚠️⚠️⚠️匿名内部类的特点、作用：
        特点：匿名内部类本质就是一个子类，并会立即创建出一个子类对象。
        作用：用于更方便的创建一个子类对象。在开发中为了快速得到一个对象使用，则使用匿名内部类。

        匿名内部类常用于简化代码
     */
    public static void main(String[] args) {
        // 1、定义匿名内部类
        // 匿名内部类的名称：当前类名&索引
        Animal d = new Animal() {
            @Override
            public void cry() {
                System.out.println("--- 匿名内部类创建的对象，狗，汪汪汪！");
            }
        };
        d.cry();

        Dog d2 = new Dog();
        d2.cry();

    }
}
