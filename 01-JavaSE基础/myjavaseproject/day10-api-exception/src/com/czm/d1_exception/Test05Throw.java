package com.czm.d1_exception;

public class Test05Throw {
    /*
      1、throw
       throw 用于在程序执行过程中显式抛出一个异常对象。
       返回值一般代表有具体含义的值、有用的值，因此不建议使用 返回特定的值 来传递异常信息，而且返回值还会存在歧义。因此开发中直接使用 throw 抛出异常。

      2、throw 抛出异常对象类型的选择
       如果出现异常的错误不是很严重，在编写代码时多加注意就可以避免，而不必强制处理此异常，那么抛出 非检查型异常，如：IllegalArgumentException。
       如果异常问题很严重，而且编写代码过程中很难避免，希望开发者重视这个问题，那么抛出 检查型异常，如：Exception。

      3、使用异常的好处
        a、将错误处理代码与普通代码区分开；--- 即 try-catch 中，try 里面是普通代码，catch 中是异常代码。
        b、能将错误信息传播到调用堆栈中；--- 即异常可以向上一层一层传递，也可以在任意一层方法中处理掉，并且不占用返回值。
        c、能对错误类型进行区分和分组；--- 即在 try-catch 中，可以 catch 区分不同类型的异常，然后分别处理。

     */
    public static void main(String[] args) {
        System.out.println("--- 1");
        int age = 0;
        try {
            test1(age);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("--- 2");
        System.out.println(test2(6, 2));
        System.out.println("--- 3");
    }


    public static void test1(int age) throws Exception {
        if (age < 18) {
            throw new Exception("未成年人不允许使用");   // 抛出检查型异常对象，必须通过 throws 传递到上层方法
        } else {
            System.out.println(age);
        }
    }

    public static int test2(int a, int b) {
        if (b == 0) {
            throw new IllegalArgumentException("除数不能为0");   // 抛出 RuntimeException 非检查型异常，不必使用 throws
        } else {
            return a / b;
        }
    }

}
