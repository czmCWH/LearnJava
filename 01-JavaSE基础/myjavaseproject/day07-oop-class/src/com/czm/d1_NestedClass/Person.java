package com.czm.d1_NestedClass;

/**
 * 1、嵌套类的定义，及其特点
 */
public class Person {

    private String name;

    public String getName() {
        return name;
    }

    public Person(String name) {
        this.name = name;
    }

    // ⚠️ 静态嵌套类
    static class StaticNestedClass {

    }

    // ⚠️ 非静态嵌套类（内部类）
    class InnerClass {

    }

    // 内部类的特点
    public class Hand {
        private int no;
        private int weight;

//        public static String tag1 = "Hand";   // 内部类中定义 static 成员非法，会报错
        public static final String tag = "Hand";    // 内部类中可以定义 编译时常量

        public Hand(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }

        /**
         * 内部类中访问外部类的私有成员
         */
        public void printName() {
            System.out.println("--- name: " + name);
        }
    }
    /**
     * 外部类中访问内部类的私有成员
     */
    public void printHand(Hand hand) {
        System.out.println("--- hand no = " + hand.no);
    }

}
