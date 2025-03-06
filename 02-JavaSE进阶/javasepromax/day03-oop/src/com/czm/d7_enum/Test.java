package com.czm.d7_enum;

public class Test {
    /*
     1、枚举
        枚举是一种特殊的类。
        枚举语法：
	        修饰符 enum 枚举类名 {
		        名称1, 名称2...;
		        其它成员...
	        }

     2、枚举注意点：
        枚举类中的第一行，只能写一些合法的标识符(名称)，多个名称用逗号隔开。这些名称，本质是常量，每个常量都会记住枚举类的一个对象。
        枚举类的构造器都是私有的(写不写都只能是私有的)，因此，枚举类对外不能创建对象。
        枚举都是 final(最终类)，不可以被继承。
        枚举类中，从第二行开始，可以定义类的其他各种成员。
        编译器为枚举类新增了几个方法，并且枚举类都是继承:java.lang.Enum类的，从enum类也会继承到一些方法。

     3、枚举应用
     ⚠️：使用枚举类实现单例设计模式，不存在线程安全

     3、
     */
    public static void main(String[] args) {


        System.out.println("--------------枚举基类提供的方法");

        // 拿到枚举类的全部对象，以数组的形式返回
        A[] as = A.values();
        for (int i = 0; i < as.length; i ++) {
            A a = as[i];
            System.out.println(a);
        }
        A a2 = A.valueOf("Y");
        System.out.println("--- 拿到第2个对象 = " + a2);

        System.out.println("--- 枚举对象的索引 = " + a2.ordinal());
    }
}
