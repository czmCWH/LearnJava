package com.czm.object03this;

public class Test {
    /*
     1、对象的 this 变量
        this 是一个指向当前对象的引用。

     2、this 的用途
        访问当前类中定义的成员变量，避免变量名称冲突问题的；
        调用当前类中定义的方法（⚠️包括构造方法）；

      只能在构造方法中使用 this 调用其它构造方法。

     3、this 的本质
        是一个隐藏的、位置最靠前的方法参数。
        st1.printMy(); 等价于 方法区 > printMy(st1);
     */
    public static void main(String[] args) {
        Student st1 = new Student();
        System.out.println(st1);
        st1.printMy();
    }
}
