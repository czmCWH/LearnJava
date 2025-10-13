package com.czm.d3_Interface;

/**
 * 定义一个接口 - 分析接口中可以定义的内容
 */
public interface TeachAbility {
    // 1、接口中定义抽象方法可以省略 public abstract
    void teachCourse(Child child);
//    public abstract void teachCourse2(Child child);   // teachCourse2 与 teachCourse 的定义完全等价！

    void teachSport(Child child);

    // 2、接口中定义常量，可以省略 public static final，默认会加上
    static final int course = 100;
//    int course2 = 100;    // course2 与 course 的定义完全等价！

    // 3、定义嵌套类
    class A {

    }

    // 4、定义默认方法
    default void teachMusic() {
        System.out.println("--- interface Music ---");
    }

    // 5、定义私有方法，JDK 9开始才支持
    //  只能在当前接口内部的默认方法、私有方法中调用。
//    private void getMoney();

}
