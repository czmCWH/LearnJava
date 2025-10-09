package com.czm.d3_Interface;

/**
 * 定义一个接口 - 接口相关的特点
 */
public interface TeachAbility {
    void teachCourse(Child child);
//    public abstract void teachCourse2(Child child);   // teachCourse2 与 teachCourse 的定义完全等价！
    void teachSport(Child child);

    static final int course = 100;
//    int course2 = 100;    // course2 与 course 的定义完全等价！

    class A {

    }

    // 定义默认方法
    default void teachMusic() {
        System.out.println("--- interface Music ---");
    }
}
