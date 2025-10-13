package com.czm.d12_extends_constructor_x;

public class Test {
    /*
     1、子类构造器的特点
        子类的全部构造器，都会先调用父类的构造器，再执行自己的构造器。

     2、子类构造器是如何实现调用父类构造器的
        默认情况下，子类全部构造器的第一行代码都是 super()(写不写都有)，它会调用父类的无参数构造器。
        如果父类没有 无参数构造器，则我们必须在子类构造器的第一行手写super(.…)，指定去调用父类的有参数构造器。

     3、为什么调用子类构造器时会先调用父类的构造器？
        目的是初始化父类的部分数据。

     */
    public static void main(String[] args) {
        Wolf w = new Wolf();
        Wolf w2 = new Wolf("哈哈");
    }
}
