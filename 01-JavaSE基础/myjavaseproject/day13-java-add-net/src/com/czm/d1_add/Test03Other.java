package com.czm.d1_add;

public class Test03Other {

    /*
      1、双括号初始化
       任何对象都可以使用 双括号初始化，即初始化时立即使用对象进行一些操作。

       优点：代码简介
       缺点：本质就是多生成了一个匿名类，把双括号内的代码放在匿名类的构造方法里执行。可能会影响 equals 方法的正常使用。
       建议：尽量不使用！

     */

    public static void main(String[] args) {

        System.out.println("--- 1、使用双括号初始化：");
        Person p = new Person("张三") {{
            run();
        }};

        System.out.println("\n--- 2、使用双括号的副作用：");
        // Person 重写了 equals 方法，使得根据成员属性判断是否相等
        Person p1 = new Person("张三");
        Person p2 = new Person("张三");
        Person p3 = new Person("张三") {{

        }};

        System.out.println("p1 == p2 = " + p1.equals(p2));  // true
        System.out.println("p1 == p3 = " + p1.equals(p3));  // false

        System.out.println("p1 instanceof Person = " + (p1 instanceof Person)); // true
        System.out.println("p3 instanceof Person = " + (p3 instanceof Person)); // true

    }
}
