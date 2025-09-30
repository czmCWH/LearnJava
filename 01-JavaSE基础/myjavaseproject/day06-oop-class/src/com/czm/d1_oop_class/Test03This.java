package com.czm.d1_oop_class;

public class Test03This {
    /*
     1、对象的 this 变量
        this 是一个指向当前对象的引用。

     2、this 的常见用途
        访问当前类中定义的成员变量，避免变量名称冲突问题的；
        调用当前类中定义的方法（⚠️包括构造方法）；

        在当前类，只能在构造方法中使用 this 调用本类其它构造方法。
        如果在构造方法中调用了其它构造方法。构造方法调用语句必须是构造方法中的第一条语句。
        构造方法中调用其它构造方法是为了利用重复的代码。

     3、this 的本质
        是一个隐藏的、位置最靠前的方法参数。
     */
    public static void main(String[] args) {
        User user = new User();
        System.out.println(user);
        // printMy 等价于 public void printMy(User this)。
        // 调用 printMy 方法本质，去方法区找到 printMy 方法，并传入 user。
        user.printMy();
    }
}
