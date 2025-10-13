package com.czm.d2_AbstractClass;

public class Test01AbstractClass {

    /*
     1、抽象方法（abstract method）
      抽象方法是指 被 abstract 修饰的方法。其特点如下：
        只有方法声明、没有方法实现（参数列表后面没有大括号，而是分号）。
        不能是 private 权限（因为定义抽象方法的目的让子类去实现）。
        只能是实例方法，不能是 static 方法。
        只能定义在抽象类、接口中。


     2、抽象类（abstract class）
      抽象类是指 被 abstract 修饰的类。其特点如下：
        可以定义抽象方法；
        不能实例化，但可以自定义构造方法；（如：此类实例化没有任何意义）
        子类必须实现抽象父类中的所有抽象方法（除非子类也是一个抽象类）；
        可以像非抽象类一样定义 成员变量、常量、嵌套类型、初始化块、非抽象方法 等；

     3、抽象类常见使用场景
      抽取子类的公共实现到抽象父类中，要求子类 `必须` 要单独实现的定义成抽象方法。

      抽象类不是一个必须技术，可用可不用，也可以用父子类继承实现。
      如果使用抽象类实现了多态，则是最佳实践！

     */

    public static void main(String[] args) {

        Circle circle = new Circle(10);
        circle.showArea();

        Rectangle rectangle = new Rectangle(10, 20);
        rectangle.showArea();

    }
}
