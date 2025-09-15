package com.czm.api_package;
// 导入其它包
import com.czm.api_package2.Car;

import java.util.Random;

public class Test {
    /*
     1、包 package
       a、Java 中的包就是其它编程语言中的命名空间，包的本质是文件夹，主要有如下作用：
            将不同的类进行组织管理、访问控制。
            解决命名冲突；

       b、包的命名建议
            为保证包名的唯一性，一般包名称都是以公司域名的倒写开头，比如：com.baidu.*。
            包名称需全小写（以避免与某些类名或者接口名冲突）。

       c、类的第一句代码必须使用 package 声明自己属于那个包
            比如：
                  package com.czm.model;
                  public class Dog {

                  }

     2、包名称细节
        如果公司域名有非法字符（包名称不能使用），建议添加下划线(_)来使包名合法化。如下所示：
                域名                  包名
        my-name.example.org     org.example.my_name             // 中划线不合法
            example.int              int_.example               // int 关键字
         123name.example.com   com.example._123name             //123name 以数字开头

     3、使用不同包下的类
        要想正常使用一个类，必须得知道这个类的具体位置(在哪个包)，有 3 种常见方式来使用一个类：
        a、使用类的全名，如下所示：
            com.czm.pkg2.Student s2 = new com.czm.pkg2.Student();

        b、使用 import 导入指定的类名，如下所示：
            import java.util.Random;

        c、使用 import 导入整个包的所有类，如下所示：
            import com.czm.pkg2.*;

     4、导入包的细节
        a、为了方便，Java 编译器会为每个源文件自动导入如下2个包：
            import java.lang.*;      java.lang 包提供了 Java 开发中最常见的一些类型。
            import 当前源文件所在包.*;      这样同包下的类可以直接相互访问。

        b、import aa.bb.*;
            此方式导入的包仅仅 import 了直接存放在 aa.bb 包中的类型。并不包含 import aa.bb.xx.*;

     5、IDEA 导入包的快捷键：
        cmd + shift + O

     */
    public static void main(String[] args) {
        // 程序中使用包的注意事项：

        // 1、如果当前程序中，要调用自己所在包下的其他程序，可以直接调用。(同一个包下的类，互相可以直接调用)
        Student s1 = new Student();

        // 2、如果当前程序中，要调用其他包下的程序，则必须在当前程序中导包，才可以访问!
        //	       导包格式:import 包名.类名;
        Car c1 = new Car();

        // 3、如果当前程序中，要调用Java提供的程序，也需要先导包才可以使用;但是 Java.lang 包下的程序是不需要我们导包的，可以直接使用。
        // 如：java.lang.String
        Random r = new Random();
        System.out.println(r.nextInt(100));

        // 4、如果当前程序中，要调用多个不同包下的程序，而这些程序名正好一样，此时默认只能导入一个程序，另一个程序必须带包名访问。
        com.czm.api_package2.Student s2 = new com.czm.api_package2.Student();
        s2.say();
    }
}
