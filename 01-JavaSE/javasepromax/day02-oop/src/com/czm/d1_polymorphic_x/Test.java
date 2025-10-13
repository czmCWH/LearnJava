package com.czm.d1_polymorphic_x;

public class Test {
    /*
     1、什么是多态?
        多态是在 继承/实现 情况下的一种代码现象，表现为：对象多态、行为多态。
	        对象多态：比如：人对象，可以是 老师、爸爸、儿子...
	        行为多态：同样的行为在不同的对象下表现不同的状态，比如：动物对象表现不同的特征，奔跑速度：兔子快、乌龟慢...

     2、多态的前提
	    有继承/实现关系；
	    存在父类引用子类对象；
	    存在方法重写；

     3、多态的一个注意事项
        ⚠️：多态是对象、行为的多态，Java中的属性(成员变量)不谈多态

     4、使用多态的好处
        在多态形式下，右边对象是解耦合的，更便于扩展和维护。
        定义方法时，使用父类类型的形参，可以接收一切子类对象，扩展性更强、更便利。

     5、多态下存在一个问题
        多态不能直接调用子类独有的功能。可以通过 强制类型转换解决。

     6、强制类型转换
        语法：子类 变量名=(子类) 父类变量;

        存在继承/实现时，就可以进行强制类型转换，编译阶段不会报错。
        但是，运行时，如果发现对象的真实类型与强转后的类型不同会报错(ClassCastException)
        强转时，java 建议使用 instanceof 关键字，判断当前对象的真实类型，再进行强转：
            p instanceof Student
     */
    public static void main(String[] args) {
        Animal a1, a2;
        // 对象多态
        a1 = new Cat();
        a2 = new Dog();
        // 行为多态
        a1.cry();
        a2.cry();

        // 成员变量不谈多态
        System.out.println(a1.name);    // 打印：动物名称
        System.out.println(a2.name);    // 打印：动物名称

        // 多态作为形参
        testCry(a1);
        testCry(a2);

        // 多态调用 子类独有功能，需要强制类型转换
        if (a1 instanceof Cat) {    // instanceof 判断对象是某个类型
            Cat c1 = (Cat) a1;
            c1.catEatFish();
        }

    }

    public static void testCry(Animal a) {
        a.cry();
    }
}

