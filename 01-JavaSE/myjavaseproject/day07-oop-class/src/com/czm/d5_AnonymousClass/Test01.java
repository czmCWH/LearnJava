package com.czm.d5_AnonymousClass;

import java.lang.Runnable;

public class Test01 {
    /*
     1、匿名类 Anonymous Class --- 【重点】
      当 接口、抽象类 的实现类，在整个项目中只用过一次，可以考虑使用匿名类。这样可以省略类的定义、简化代码。

      定义匿名内部类语法：
        new 类或接口(参数列表) {
            类体(一般是方法重写);
        }
      匿名内部类本质就是一个子类，并会立即创建出一个子类对象。

     2、匿名类的使用注意
      a、匿名类不能定义除编译时常量以外的任何 static 成员。

      b、匿名类只能访问 final 或者 有效final 的局部变量。

      c、匿名类可以直接访问外部类中的所有成员（即使被声明为 private）。
        匿名类只有在实例相关的代码块中使用，才能直接访问外部类中的实例成员（实例变量、实例方法）。

      d、匿名类不能自定义构造方法，可以有初始化块

     3、匿名类应用场景
      通常作为一个对象参数传递给方法。

     */

    public static void main(String[] args) {

        // 1、定义一个匿名类，并使用
        Runnable person = new Runnable() {
            @Override
            public void run() {
                System.out.println("--- Person run 1");
            }
        };
        person.run();

        new Runnable() {
            @Override
            public void run() {
                System.out.println("---Animal run 2");
            }
        }.run();

        // 2、如下 eat 参数接收的某个只使用一次，因此可以使用匿名类
        eat(new Eatable() {

            @Override
            public String name() {
                return "苹果";
            }

            @Override
            public int energy() {
                return 300;
            }
        });

    }

    public static void eat(Eatable e) {
        System.out.println("--- eat " + e.name() + " - energy：" + e.energy());
    }
}
