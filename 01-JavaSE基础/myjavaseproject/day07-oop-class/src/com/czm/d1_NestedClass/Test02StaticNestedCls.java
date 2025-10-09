package com.czm.d1_NestedClass;

public class Test02StaticNestedCls {

    /*
      1、静态嵌套类（static nested class）
       静态嵌套类是指被 static 修饰的嵌套类。

       ⚠️ 静态嵌套类在行为上就是一个顶级类，只是定义的代码写在了另一个类中。

       对比一般的顶级类，静态嵌套类多了一些特殊权限：
        a、可以直接访问外部类中除实例变量、实例方法外的其它成员（即使被声明为 private）。

      2、嵌套类的应用场景
       如果 A类 只用在 类C 内部，可以考虑将 类A 嵌套到 类C 中，这样做的优点：
        封装性更好；
        程序包更加简化；
        增强可读性、维护性；

       如果 类A 需要经常访问 类C 的非公共成员，可以考虑将 类A 嵌套到 类C 中；
       另外也可以根据需要将 类A 隐藏起来，不对外暴露。

       如果需要经常访问非公共的实例成员，设计成内部类(非静态嵌套类)，否则设计成静态嵌套类。

       ⚠️ 总之，如果要设计为 嵌套类，能为 静态嵌套类 就不要设计为 非静态嵌套类。

      3、

     */

    public static void main(String[] args) {

        Company.Person p = new Company.Person();
        p.startBusiness();

    }
}
