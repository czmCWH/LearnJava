package com.czm.pkg;
// 导入其它包
import com.czm.pkg2.Car;

import java.util.Random;

public class Test1 {
    /*
     1、什么是包
        包是用来分门别类的管理各种不同程序的，类似于文件夹，建包有利于程序的管理和维护

        建包的语法格式：
	        package com.czm.javabean;
	        public class Student {

	        }
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
        com.czm.pkg2.Student s2 = new com.czm.pkg2.Student();
        s2.say();
    }
}
