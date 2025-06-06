package com.czm.object;

public class ObjectDemo1 {
    /*
     1、面向对象编程
        计算机是用来处理数据的。
        开发一个一个的对象，把数据交给对象，再调用对象的方法来完成对数据的处理

     2、面向对象编程入门
        对象的数据结构：
            public class 类名 {
                1、变量，表示对象可以存储什么样的数据
                2、方法，描述对象有什么功能，以及对数据做那些处理。
            }
        创建对象的格式：
            类名 对象名 = new 类名()
        对象本质上是一种特殊的数据结构，用来存储一个个对象的数据。

     3、面向对象的好处
        凡事找对象的编程套路，更加符合人类思维习惯，编程也会更直观。
     */
    public static void main(String[] args) {
        // 1、通过类创建对象
        Student st1 = new Student();
        st1.name = "tom";
        st1.chinese = 99.5;
        st1.math = 98.5;
        st1.printAllScore();
        st1.printAverageScore();

        Student st2 = new Student();
        st2.name = "jan";
        st2.chinese = 83.5;
        st2.math = 88.5;
        st2.printAllScore();
        st2.printAverageScore();
    }
    /*
     4、对象在计算机中执行的原理
        程序会把类加载到方法区；
        栈内存的变量，通过 new 方法按照方法区的类模版在堆内存中开辟一个空间存放对象，该变量存储堆空间的地址；
        对象空间中还存放一个类地址，表示该对象是由那个类创建的；
        栈内存的对象变量通过地址找到堆空间的对象进行操作；
     */
}


/*
 5、类和对象的一般注意事项：
     a、类名建议用英文单词，首字母大写，满足驼峰模式，且要有意义，比如:Student、car...

     b、类中定义的变量也称为成员变量(对象的属性)，类中定义的方法也称为成员方法(对象的行为)

     c、成员变量本身存在默认值，同学们在定义成员变量时一般来说不需要赋初始值(没有意义)
        基本类型默认值：
            byte、short、char、int、long    0
            float、double                  0.0
            boolean                        false
        引用类型：
            数组、String                   null

     d、一个代码文件中，可以写多个class类，但只能一个用 public 修饰，且 public 修饰的类名必须成为代码文件名

     e、对象与对象之间的数据不会相互影响，但多个变量指向同一个对象时就会相互影响了

     f、如果某个对象没有一个变量引用它，则该对象无法被操作了，该对象会成为所谓的垃圾对象。
            Java存在自动垃圾回收机制，会自动清除掉垃圾对象，程序员不用操心。
 */