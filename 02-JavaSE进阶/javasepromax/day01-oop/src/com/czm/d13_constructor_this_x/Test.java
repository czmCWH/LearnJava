package com.czm.d13_constructor_this_x;

public class Test {

    /*
     1、this 调用兄弟构造器
        任意类的构造器中，是可以通过this(.)去调用该类的其他构造器的。

     2、this(..)和super(...)使用时的注意事项:
        ⚠️：this(.)、super(...) 都只能放在构造器的第一行，因此，有了this(...)就不能写super(..)了，反之亦然。
        因为这样会多次调用父类的构造器
     */
    public static void main(String[] args) {
        Student s = new Student("孙悟空", 500, "花果山");

        Studert s1 = new Student("不上学");

        System.out.println(s1);
    }
}
