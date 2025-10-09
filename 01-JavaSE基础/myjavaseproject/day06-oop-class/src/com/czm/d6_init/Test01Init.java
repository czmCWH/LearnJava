package com.czm.d6_init;

public class Test01Init {
    /*
     1、成员变量的初始化
      编译器会自动为未初始化的成员变量设置初始值。

     2、成员变量设置初始值的方式：
      在成员变量声明中；
      在构造方法中；
      在初始化块中；

     3、类变量提供初始值的方式：
      在类变量声明中；
      在 静态初始化块 中；


     4、⚠️初始化块
      直接在类里面写一个大括号，叫做初始化块。特点如下：
       a、编译器会将初始化块复制到每个构造方法的头部执行。
       b、初始化块中的代码会先执行，执行完后再执行构造方法中的代码。
       c、每创建一个实例对象，就会执行一次初始化块。

     5、静态初始化块的特点
      当一个类被初始化的时候会执行 静态初始化块；
      当一个类第一次被主动使用时，JVM 会对类进行初始化；（即，只会执行一次）

     */

    // 初始化块
    {
        count = 10;
        System.out.println("--- 初始化块");
    }

    // 静态初始化块
    static {
        name = "abc";
        System.out.println("--- 静态初始化块");
    }

    public int count;

    public static String name;

    public static void main(String[] args) {
        Test01Init test = new Test01Init();
        System.out.println(test.count);
    }
}
