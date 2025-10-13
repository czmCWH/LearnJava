package com.czm.d1_exception_x;

public class Test2 {
    /*
     1、自定义异常
        Java 无法为这个世界上全部的问题都提供异常类来代表，如果企业自己的某种问题，想通过异常来表示，以便用异常来管理该问题，那就需要自己来定义异常类了。

     2、自定义运行时异常 --- 建议使用！！！
        定义一个异常类继承 RuntimeException.
        重写构造器。
        通过 throw new 异常类(xxx) 来创建异常对象并抛出。

        特点：如果未对异常处理，编译阶段不报错，提醒不强烈，运行时才可能出现!!

     3、自定义编译时异常
        定义一个异常类继承 Exception.
        重写构造器。
        通过 throw new 异常类(xxx)来创建异常对象并抛出。

        特点：如果未对异常处理，编译阶段就报错，提醒更加强烈!
        由于编译时异常写代码时干扰太强烈，因此不推荐使用！
     */
    public static void main(String[] args) {
        // 1、抛出 运行时异常
        System.out.println("--- 开始1");
        save(18);
        try {
            save(0);
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.out.println();
        }
        System.out.println("--- 结束2");
        System.out.println("----------");
        // 2、抛出 编译时异常
        System.out.println("--- 开始2");
        // ⚠️：由于 save2 抛出编译时异常，因此使用它时必须处理该异常。
//        save2(20);    // 编译时就报错
        try {
            save2(250);
        } catch (AgeIllegalException e) {
            e.printStackTrace();
            System.out.println();
        }
        System.out.println("--- 结束2");
    }

    // 1、使用自定义运行时异常
    public static void save(int age) {
        if (age <= 0 || age > 150) {
            throw new AgeIllegalRuntimeException("age 非法1");
        }
        System.out.println("年龄保存成功！");
    }

    // 2、使用自定义编译时异常
    public static void save2(int age) throws AgeIllegalException {
        // throw 用于在方法内部抛出创建的异常对象。
        // throws 用于在方法上，抛出方法内部的异常给方法调用者。
        if (age <= 0 || age > 150) {
            throw new AgeIllegalException("age 非法2");
        }
        System.out.println("年龄保存成功！");
    }


}
