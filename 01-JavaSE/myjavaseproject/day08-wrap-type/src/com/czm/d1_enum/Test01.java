package com.czm.d1_enum;

public class Test01 {
    /*
     1、枚举
        枚举是一种特殊的 类。枚举由一组预定义的常量构成。
        枚举语法：
	        修饰符 enum 枚举类名 {
		        名称1, 名称2...;
		        其它成员...
	        }

     ⚠️：编译器最终会把 枚举类型 生成一个 类，枚举的本质就是 类。

     2、定义枚举注意点：
        枚举类中的第一行，只能写一些合法的标识符(名称)，多个名称用逗号隔开。这些名称，本质是常量，每个常量引用枚举类的一个对象。
        枚举类中，从第二行开始，可以定义类的其他各种成员。

        枚举类的构造器 或 自定义的构造器 都是私有的，即构造器权限必须 无修饰符 或 private，因此，枚举类对外不能创建对象。
        Java 会主动调用构造方法初始化每一个常量，开发者不能主动调用构造方法。

        枚举都是 final(最终类)，不可以被继承。

        编译器为枚举类新增了几个方法，并且枚举类都是继承：java.lang.Enum 类的，从 enum 类也会继承到一些方法。

     3、枚举应用场景
     如果一个变量的取值只可能是固定的几个值，可以考虑使用枚举类型。
     ⚠️：使用枚举类实现单例设计模式，不存在线程安全
     */
    public static void main(String[] args) {
        System.out.println("-------------- 自定义枚举");
        // 首次获取枚举常量时，所有枚举常量都会自动初始化
        Season season = Season.SPRING;
        System.out.println(season.getMax());

        System.out.println("-------------- 枚举基类提供的方法");

        // 拿到枚举类的全部对象，以数组的形式返回
        A[] as = A.values();
        for (int i = 0; i < as.length; i ++) {
            A a = as[i];
            System.out.println(a);
        }
        A a2 = A.valueOf("Y");
        System.out.println("--- 拿到第2个对象 = " + a2);

        System.out.println("--- 枚举对象的索引 = "  + a2.ordinal());
    }

    // 自定义类实现枚举效果
    public static void testSeason(Season season) {
        // switch case 中使用枚举值时，可以直接写枚举对象值
        switch (season) {
            case SPRING:
                System.out.println("春天");
                break;
            case SUMMER:
                System.out.println("夏天");
                break;
            case AUTUMN:
                System.out.println("秋天");
                break;
            case WINTER:
                System.out.println("冬天");
                break;
        }
        if (season == Season.SPRING) {
            System.out.println("春天");
        } else if (season == Season.SUMMER) {
            System.out.println("夏天");
        } else if (season == Season.AUTUMN) {
            System.out.println("秋天");
        } else if (season == Season.WINTER) {
            System.out.println("冬天");
        }
    }
}
