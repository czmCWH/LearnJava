package com.czm.d1_oop_class;

public class Test02Constructor {
    /*
     1、构造器、构造方法（Constructor）
        构造方法，也叫构造器，能够更加方便地创建一个对象，每次创建对象时，对象会立即调用构造器。
        构造器的名称必须与类名一样，并且不能有返回值声明。
        构造器可以重载。

     2、构造器注意事项：
        类在设计时，如果没有自定义构造器，Java编译器会自动为它提供无参数的默认构造方法。
        一旦自定义了构造器，默认构造器就不会存在，⚠️建议开发者写一个无参数构造器。

     3、构造器的作用
        创建对象时，同时为对象赋值。
     */
    public static void main(String[] args) {
        Person s = new Person();
        Person s1 = new Person("哈哈", 18);
    }
}
