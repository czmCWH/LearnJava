package com.czm.d2_reflect;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class Test5ReflectFrame {

    /*
     1、反射的作用 ⚠️
       a、基本作用：可以在运行时得到一个类的全部成分然后操作。
       b、可以破坏封装性（即可以访问 private 成员）。--- 很突出！
       c、可以绕过泛型约束。--- 很突出！
       d、最重要的用途是：适合做 java 的框架，基本上，主流的框架都会基于反射设计出一些通用的技术功能。

     2、反射案例：对于任意一个对象，该框架都可以把对象的字段名和对应的值，保存到文件中去
      实现步骤：
        1、定义一个方法，可以接受任意对象
        2、没收到一个对象后，使用反射获取该对象的 Class 对象，然后获取全部的成员变量。
        3、变量成员变量，然后提取成员变量在该对象中的具体值。
        4、把成员变量、和其值，写出到文件中去即可。

     */

    public static void main(String[] args) throws Exception {
        Student stu = new Student("张帅", 19, '男',175, "打篮球");
        Teacher tch = new Teacher("牛老师", 25);

        SaveObjectFrame.saveObject(stu);
        SaveObjectFrame.saveObject(tch);
    }

    // Junit 单元测试，对相对路径支持不好，会导致报错！！！！！！！
//    @Test
//    public void testSave() throws Exception {
//        Student stu = new Student("张帅", 19, '男',175, "打篮球");
//        Teacher tch = new Teacher("牛老师", 28);
//
//        SaveObjectFrame.saveObject(stu);
//        SaveObjectFrame.saveObject(tch);
//    }

    /**
     * 反射的作用：可以绕过泛型约束
     */
    @Test
    public void testArrayList() throws Exception {
        ArrayList<String> list = new ArrayList<>();
        list.add("张三");
        list.add("李四");

        // 放射第一步，获取类对象
        Class c = list.getClass();

        // 获取 add 方法成员
        Method m = c.getDeclaredMethod("add", Object.class);

        // 触发 list 集合对象的 add 方法执行
        m.invoke(list, 19.99);  // 翻墙技术，把不同类型加入到泛型约束数组内
        m.invoke(list, true);   // 翻墙技术

        // Java 很多技术无底线，

        System.out.println(list);   // [张三, 李四, 19.99, true]
    }

}
