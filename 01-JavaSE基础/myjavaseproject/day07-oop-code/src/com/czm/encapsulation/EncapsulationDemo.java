package com.czm.encapsulation;

public class EncapsulationDemo {
    public static void main(String[] args) {
        /*
         1、什么是封装?
	        就是用类设计对象处理某一个事物的数据时，应该把要处理的数据，以及处理这些数据的方法，设计到一个对象中去。

         2、封装的设计规范：
	        合理隐藏、合理暴露

	     3、开发中设计类时，成员变量一般都需要私有，成员方法大部分暴露。
         */

        Person p = new Person();
        p.name = "康康";
        p.setAge(18);
        System.out.println(p.getAge());

    }
}
