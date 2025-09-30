package com.czm.d2_oop_inherit;

import java.lang.annotation.Annotation;

public class Test01 {

    /*
      1、继承 Inheritance
       Java 中任何类都继承自 java.lang.Object，一般称它为基类。
       子类中可以定义跟父类同名的成员变量，注意在子类中的同名成员变量使用不同的内存存储（不建议这样做）。

       子类对象的内存中，除自身的成员变量，还有父类的成员变量。

      2、方法重写 Override
       重写：子类的方法签名（方法名+参数类型）与父类一样，也叫做覆盖、复写。

       注意点：
        a、子类 override 的方法权限必须 >= 父类的方法权限
        b、子类 override 的方法返回值类型必须 <= 父类的方法返回值类型

      3、super 常见用途
       访问父类中定义的成员变量；
       调用父类中定义的方法（⚠️ 包括父类构造方法）；
         只能在当前类的构造方法中，使用 super 调用父类的构造方法。

      4、构造方法的细节
       子类的构造方法 必须 先调用父类的构造方法，再执行后面的代码。
       如果子类的构造方法没有显式调用父类的构造方法，编译器会自动调用父类无参的构造方法（若此时父类没有无参的构造方法，编译器将报错）

     */

    public static void main(String[] args) {
        Student stu = new Student();
        // 1、子类访问父类的属性、方法
        stu.age = 20;
        stu.walk();
        stu.no = 1;
        stu.study();

        // 2、同名的成员变量
        /*
          sameOne = 22
          this.sameOne = 22
          super.sameOne = 11
         */
        stu.printSameOne();

        // 3、重写父类的方法
        stu.eat();




    }
}
