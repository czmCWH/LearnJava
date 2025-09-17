package com.czm.d1_exception;

import com.czm.d1_exception.sub.Dog;

public class Test02TryCatch {

    /**
     1、try-catch，捕获异常
     try-catch 处理完异常后，代码会往后执行，不会影响程序的正常执行。
     注意：catch 中捕获异常子类型的异常必须写在父类型的前面。因为如果父类型在子类型的前面，会直接匹配到子类型的异常。

     2、异常对象.printStackTrace(); 打印异常的堆栈跟踪信息，（常用！）
        printStackTrace 是使用 System.err.println 打印的，控制台输出颜色为红色。
        System.err.println 打印内容很多行时，其内容在控制台中显示会交错出现在 System.out.println 的内容中。

     3、Java 7+ 单个 catch 可以捕获多种类型的异常
        如果并列的几个异常类型之间存在父子关系，保留父类型即可。
        这里的变量 e 是隐式 final 的，即不可以被修改。因为此处的 e 的类型不确定，具体能赋值什么对象没办法确定，所以是 final。
     */

    public static void main(String[] args) {
        System.out.println("--- 代码开始");
        try {

//            Object obj = "123.4";
//            Double d = (Double) obj;
            Object obj = 123.4;
            Double d = (Double) obj;

            Class cls = Class.forName("com.czm.d1_exception.sub.Dog");
            Dog dog = (Dog) cls.newInstance();
            System.out.println(dog);

        } catch (ClassCastException e) {
            System.out.println("--- 抛出了类型不匹配异常：ClassCastException");
        } catch (ClassNotFoundException e) {
            System.out.println("---- 抛出了 类不存在 异常：ClassNotFoundException");
        } catch (InstantiationException e) {
            // 打印异常的堆栈跟踪信息
            e.printStackTrace();
            System.out.println("---- 抛出了 没有无参构造方法 异常：InstantiationException");
        } catch (IllegalAccessException e) {
            System.out.println("--- 抛出了 没有权限访问构造方法 异常：IllegalAccessException");
        } catch (Throwable e) {     // 统一拦截所有异常
            System.out.println("--- 父类型异常：Throwable");
        }
        System.out.println("--- 代码结束");

        System.err.println("err 红色打印");


        try {
            Class cls = Class.forName("com.czm.d1_exception.sub.Dog");
            Dog dog = (Dog) cls.newInstance();
            System.out.println(dog);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {      // 捕获多种类型的异常
            System.out.println("---- 抛出了 类不存在 异常：ClassNotFoundException");
            System.out.println("---- 抛出了 没有无参构造方法 异常：InstantiationException");
            System.out.println("--- 抛出了 没有权限访问构造方法 异常：IllegalAccessException");
        }

        try {
            Integer[] nums = {1, 2, null, 4, 5};
            /*
             Integer 在自动拆箱为 int 时，会调用 Integer 对象的 intValue()方法；
             由于 nums[2]为 nu11，使用 nu11 调用方法会抛出一个异常；
             异常类型：java.lang.NullPointerException，如果没有主动去处理这个异常，所以导致程序终止运行；
             */
            for (int num : nums) {
                System.out.println(num);
            }
        } catch (NullPointerException e) {
            System.out.println("--- 抛出空指针异常：" + e.getMessage());
        }


    }
}
