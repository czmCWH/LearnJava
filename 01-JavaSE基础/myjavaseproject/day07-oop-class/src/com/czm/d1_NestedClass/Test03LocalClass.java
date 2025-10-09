package com.czm.d1_NestedClass;

public class Test03LocalClass {

    /*
     1、局部类（Local Class）
      局部类是指 定义在代码块中的类（可以定义在方法中、for循环中、if语句中、初始化块 等）。
      代码块是指 大括号内，并且大括号里面可以直接放可执行的代码。

     2、局部类的特点
      局部类中不能定义除编译时常量以外的任何 static 成员。

      局部类只能访问 final 或者 有效final 的局部变量。
        从 Java8 开始，如果局部变量没有被第二次赋值，就认定为是 有效 final。

      局部类可以直接访问外部类中的所有成员（即使被声明为 private）。
        局部类只有定义在实例相关的代码块中，才能直接访问外部类中的实例成员（实例变量、实例方法）。

     3、局部类的应用场景
      如果 定义的类 只有在 代码块中使用，则使用局部类。

     */

    public static void main(String[] args) {


    }

    // 初始化块
    {
        int age = 18;
        System.out.println("age = " + age);
    }

    // 静态初始化块
    static {
        System.out.println("静态初始化块");
    }

    public void test() {
        for (int i = 0; i < 10; i++) {
            // a 在 代码块内只赋值过一次，编译器认为其为 有效final
            int a = 10;
            int b = 10;
            b++;

            // 定义一个局部类
            class A {
                // 1、定义局部类的成员
//                static  int a = 10;   // 报错！无法定义 static 成员
                static final int a = 10;
                int age = 10;

                void test() {
                    age++;
                    System.out.println(age);
                }

                // 2、局部类访问代码块的变量
                void test2() {
                    System.out.println("--- a = " + a);
//                    System.out.println("--- b = " + b); // 访问 b 报错！因为 b 在代码块中被修改过。
                }

                // 3、局部类访问外面类的实例成员
                void test3() {
                    System.out.println("--- count = " + count);
                    doSomething();

                    System.out.println("--- desc = " + desc);
                    allDo();
                }
            }

            // 使用局部类
            A a1 = new A();
            a1.test();
            a1.test2();
        }
    }

    private int count = 100;
    private void doSomething() {
        System.out.println("--- do something ---");
    }
    private static String desc = "好好！";
    private static void allDo() {
        System.out.println("--- allDo ---");
    }
}
