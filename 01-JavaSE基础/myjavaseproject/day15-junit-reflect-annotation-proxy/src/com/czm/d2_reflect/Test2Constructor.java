package com.czm.d2_reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;

public class Test2Constructor {

    /*
     获取类的构造器，并对其进行操作。！！！

     1、为什么叫反射？
        因为 原来构造实例是：“new 构造器()”，即找到某个构造器来 new 对象；
        而使用反射是拿到构造器实例："构造器实例.newInstance()"，即通过构造器主动 new 对象；
        构造对象的方式反过来了，所以叫反射。
     */

    /**
     * 1、获取 类对象 的所有构造器
     */
    @Test
    public void testGetConstructors() {
        // 1、反射第一步，必须先得到这个类的 Class 对象
        Class c = Cat.class;

        // 2、获取类的全部构造器
//        Constructor[] constructors = c.getConstructors();     // 只能获取 public 修饰的构造器
        Constructor[] constructors = c.getDeclaredConstructors();   // 获取全部构造器（只要存在就能拿到）

        // 3、遍历构造器
        for (Constructor con : constructors) {
            // 获取 构造器名 + 参数个数
            System.out.println(con.getName() + "==> " + con.getParameterCount());
        }
    }

    /**
     * 2、获取 类对象的 某一个构造器
     */
    @Test
    public void testGetConstructor() throws Exception {
        // 1、反射第一步，必须先拿到这个类的 Class 对象
        Class c = Cat.class;

        // 2、获取类的某一个构造器

        // getConstructor 基本不用
//        c.getConstructor();  // 定位无参构造器（注意：只能获取 public 修饰的）
//        c.getConstructor(String.class, int.class);  // 定位到指定类型的有参构造器 （注意：只能获取 public 修饰的）

        Constructor con1 = c.getDeclaredConstructor();  // 定位无参构造器
        Constructor con2 = c.getDeclaredConstructor(String.class, int.class);    // 定位到指定类型的有参构造器

        // --- 使用反射！
        // 获取到构造器的作用：创建对象

        // 3、获取构造器的作用是用来创建对象
        Cat c1 = (Cat) con1.newInstance();  // con1 是一个无参构造器，所以不需要参数
        System.out.println(c1);

        // con2 是 Cat 的有参构造器，并且是 private。直接使用 con2 创建对象会报错！
        // ⚠️：setAccessible 用于禁止访问权限，称为：暴力反射，这样私有的构造器也可以访问！！！
        con2.setAccessible(true);
        Cat c2 = (Cat) con2.newInstance("咖啡猫", 3);
        System.out.println(c2);
    }
}
