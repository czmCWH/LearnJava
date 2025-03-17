package com.czm.d2_reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;

public class Test2Constructor {

    /*
     获取类的构造器，并对其进行操作。！！！

     1、为什么叫反射？
        因为 原来构造实例是：“nwe 构造器()”，现在是拿到构造器实例："构造器实例.newInstance()"
        构造对象的方式反过来了，所以叫反射。
     */

    @Test
    public void testGetConstructors() {
        // 1、反射第一步，必须先得到这个类的 Class 对象
        Class c = Cat.class;

        // 2、获取类的全部构造器
//        Constructor[] constructors = c.getConstructors();     // 只能拿到 public 修饰的
        Constructor[] constructors = c.getDeclaredConstructors();   // 可以拿到全部类型的构造器

        // 3、遍历构造器
        for (Constructor con : constructors) {
            System.out.println(con.getName() + "==> " + con.getParameterCount());
        }
    }

    @Test
    public void testGetConstructor() throws Exception {
        // 1、反射第一步，必须先拿到这个类的 Class 对象
        Class c = Cat.class;

        // 2、获取类的某个构造器
        Constructor con1 = c.getDeclaredConstructor();  // 定位无参构造器
        Constructor con2 = c.getDeclaredConstructor(String.class, int.class);    // 定位到有参构造器

        // 3、得到构造器用于初始化返回对象
        Cat c1 = (Cat) con1.newInstance();
        System.out.println(c1);

        // 由于 Cat 的有参构造器是 private，所以执行如下方法会报错
        // ⚠️：可以通过设置 setAccessible 来禁止访问权限，称为：暴力反射，这样私有的构造器也可以访问！！！
        con2.setAccessible(true);
        Cat c2 = (Cat) con2.newInstance("咖啡猫", 3);
        System.out.println(c2);
    }
}
