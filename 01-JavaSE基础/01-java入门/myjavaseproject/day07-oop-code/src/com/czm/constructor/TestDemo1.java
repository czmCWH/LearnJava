package com.czm.constructor;

public class TestDemo1 {
    /*
     1、什么是构造器
        构造器定义是类里面特殊的方法，构造器的名称必须与类名一样，不能有返回值声明。
        每次创建对象时，对象会立即调用构造器。

     2、构造器注意事项：
        类在设计时，如果不写构造器，Java是会为类自动生成一个无参构造器的，
        一旦定义了有参数构造器，java就不会帮我们的类自动生成无参构造器了，此时就建议自己手写一个无参数构造器出来

     3、构造器的作用
        创建对象时，同时为对象赋值。
     */
    public static void main(String[] args) {
        Student s = new Student();
        Student s1 = new Student("哈哈", 18);
    }
}
