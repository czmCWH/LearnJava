package com.czm.d5_static_final;

/**
 * 被 final 修饰的类不允许被继承
 */
public final class Test03Final {

    /*
      1、final
       被 final 修饰的类不能被继承；
       被 final 修饰的方法不能被重写；
       被 final 修饰的变量只能进行一次赋值；

      2、常量 Constant
       常量的写法：常量的命名用下划线大写组成
        public static final int NOT_FOUND = -1;'

       编译器对常量的优化：
        如果将 基本类型 或 字符串 定义为常量，并且在编译时就能确定值，编译器会使用常量值替换各处的常量名（类似C语言的宏替换）。对于这种常量称为 编译时常量（compile-time constant）。
        编译时就能确定值，是指声明常量时就赋值一个的确定的值，而不是调用方法的返回值进行赋值。
        编译时常量定义如：
          public static final int age = 10;

     */

    // 1、被 final 修饰的方法不能被重写；
    public final void test() {
        final int count = 10;
    }

    /*
      2、final 修饰实例变量时，可以在声明时、初始化块、构造方法中进行赋值
     */
    public final int count1 = 10;
    public final int count2;
    {
        count2 = count1 + count1;
    }
    public final int count3;

    public Test03Final() {
        count3 = count1 + count2;
    }

    /*
      3、final 修饰静态变量时，可以在声明时、静态初始化块 中进行赋值
     */
    public static final int age = 10;
    public static final int age1;
    static {
        age1 = age;
    }


    public static void main(String[] args) {

    }

}
