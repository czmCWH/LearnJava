package com.czm.d1_exception;

import com.czm.d1_exception.sub.WrongAgeException;

public class Test06Exception {
    /*
      1、自定义异常
      开发中自定义的异常类型，基本都是以下2中做法：
        a、继承自 Exception
            它是检查型异常，使用起来代码会稍微复杂；
            表示开发者需重视这个异常、认真处理这个异常；
        b、继承自 RuntimeException
            它是非检查型异常，使用起来代码更简洁；
            不严格要求开发者去处理这个异常，但编写代码时需避免；

     */
    public static void main(String[] args) {

        test1(18);
        test1(17);

    }

    public static void test1(int age) {
        if (age < 18) {
            throw new WrongAgeException(age, 18);
        } else {
            System.out.println("--- age = " + age);
        }
    }
}




