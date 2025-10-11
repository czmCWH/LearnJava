package com.czm.d2_reflect;

import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Objects;

public class Test5Frame {

    /*
     1、反射的作用
       a、基本作用：可以得到一个类的全部成分然后操作。
       b、可以破坏封装性（即可以访问 private 成员）。
       c、可以绕过泛型约束。
       d、最重要的用途是：适合做 java 的框架，基本上，主流的框架都会基于反射设计出一些通用的功能。

     2、反射案例：对于任意一个对象，该框架都可以把对象的字段名和对应的值，保存到文件中去

     */

    public static void main(String[] args) throws Exception {
        Student stu = new Student("张帅", 19, '男',175, "打篮球");
        Teacher tch = new Teacher("牛老师", 28);

        ObjectFrame.saveObject(stu);
        ObjectFrame.saveObject(tch);
    }

    // Junit 单元测试，对相对路径支持不好，会导致报错！！！！！！！
//    @Test
//    public void testSave() throws Exception {
//        Student stu = new Student("张帅", 19, '男',175, "打篮球");
//        Teacher tch = new Teacher("牛老师", 28);
//
//        ObjectFrame.saveObject(stu);
//        ObjectFrame.saveObject(tch);
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

        // 触发 lsit 集合对象的 add 方法执行
        m.invoke(list, 19.99);  // 翻墙
        m.invoke(list, true);   // 翻墙

        System.out.println(list);   // [张三, 李四, 19.99, true]
    }

}
