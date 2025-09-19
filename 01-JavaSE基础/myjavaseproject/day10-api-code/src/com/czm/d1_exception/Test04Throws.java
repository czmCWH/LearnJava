package com.czm.d1_exception;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

public class Test04Throws {
    /*
      1、throws 向上抛出异常
       throws 的作用：将异常抛给上层方法。
       throws 后面可以使用`,`分割抛出多种类型的异常；如果抛出的异常类型存在父子关系，可以只保留父类型（一般不建议）。
       如果 throws 向上抛出非检查型的异常，上层方法不做任何处理，编译器也不会报错。

      2、throws 处理异常的流程：
            test02  产生了异常未进行处理，通过 throws 抛给上层方法处理。
              ⬇️
            test01  可以使用 try-catch 捕捉处理异常；或者使用 throws 继续向上层方法抛出异常。
              ⬇️
             main   main 方法中通过 throws 把异常抛给 JVM。
              ⬇️
             JVM    ⚠️ 如果异常未被程序处理，JVM 会接收异常，那么整个 Java 程序将终止运行。

      3、类继承中 throws 的要求
        a、当父类的方法没有 throws 异常时，子类的重写方法也不能有 throws 异常。
        b、当父类的方法有 throws 异常，则子类的重写方法有以下几种情况：
            1、没有 throws 异常；
            2、throws 跟父类一样的异常；
            3、throws 父类异常的子类型；

     */
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("--- 1");
        method01();
        System.out.println("--- 2");    // 2会打印，因为 method01 中 try-catch 捕捉处理了 test02 抛出的异常
        test01();
        System.out.println("--- 3");    // 3不会打印，因为 test02 抛出的异常未被处理，JVM 接收到异常后，程序终止运行。
    }

    public static void method01() {
        try {
            test02();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void test01() throws FileNotFoundException {
        test02();
    }

    public static void test02() throws FileNotFoundException {
//        PrintWriter out = new PrintWriter("/Users/chen/Desktop/11.txt");
        PrintWriter out = new PrintWriter("~/Desktop/11.txt");
        out.println("我是上帝！");
        out.close();
    }

    // ------------------ throws 向上抛出多种类型的异常
    public static void doTest01() {
        // 处理抛出的多个类型的异常
        try {
            test03();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            System.out.println("--- 抛出了 ClassNotFoundException：" + e.getMessage());
        }
    }

    public static void test03() throws FileNotFoundException, ClassNotFoundException {
//    public static void test03() throws Exception {    // 直接抛出父类型异常
        PrintWriter out = new PrintWriter("~/Desktop/22.txt");
        Class cls = Class.forName("Dog");
    }

    // ------------------- throws 与 类继承
    class Person {
        public void test1() {}
        public void test2() throws IOException {}
        public void test3() throws IOException {}
        public void test4() throws IOException {}
    }

    class Student extends Person {
        @Override
        public void test1() {}
        @Override
        public void test2() {}
        @Override
        public void test3() throws IOException {}
        @Override
        public void test4() throws FileNotFoundException {}     // FileNotFoundException 异常范围比 IOException 小
    }

    // ------------------- throws 向上层抛出非检查型异常
    public void method02() {
        method03();     // method02 对 method03 抛出的非检查型异常 不做任何处理，编译器没有报错。
    }
    public void method03() throws RuntimeException {
    }
}


