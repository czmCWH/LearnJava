package com.czm.d6_interface;

public class Test {
    /*
     1、接口
        Java 提供了一个关键字 interface，用这个关键字我们可以定义出一个特殊的结构：接口。
        ⚠️：JDK8之前，接口中只能定义成员变量和成员方法。
            public interface 接口名 {
	            // 成员变量(即：常量)
	            // 成员方法(即：抽象方法)
            }

     2、接口用来被类实现，称为：⚠️实现类
        接口不能创建对象，接口是用来被类实现(implements)的，实现接口的类称为 实现类。
            修饰符 class 实现类 implements 接口1, 接口2, 接口3...... {
            }
        ⚠️：实现类名通常喜欢加 Impl，如：类名Impl

     3、接口的好处
        弥补了类单继承的不足，一个类同时可以实现多个接口。
        （继承相当爸爸；接口相当于干爹，可以有多个干爹，但只能有一个爸爸。）
        让程序可以面向接口编程，这样程序员就可以灵活方便的切换各种业务实现(更利于程序的解耦合)
     */
    public static void main(String[] args) {
        StudentImpl st = new StudentImpl();
        st.run();
        st.go();
        st.eat();

        A st1 = new StudentImpl();
        B st2 = new StudentImpl();

    }
}
