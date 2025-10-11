package com.czm.d2_reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class Test3Field {

    // 通过反射，获取类的成员变量，使用该成员变量进行赋值、取值操作。

    @Test
    public void testGetFields() throws Exception {

        // 1、反射第一步，必须先拿到类的 Class 对象
        Class c = Cat.class;

        // 2、获取类的所有成员变量（包括：private 变量、static 变量）
        Field[] fields = c.getDeclaredFields();

        // 3、遍历这个成员变量数组
        for (Field field : fields) {
            System.out.println("成员变量类型名 + 成员变量名：" + field.getType().getName() + " => " + field.getName());
        }

        // 4、定位单个成员变量
        Field fage = c.getDeclaredField("age");
        System.out.println(fage.getType().getName() + "=>" + fage.getName());

        // --- 使用反射！
        // 获取到成员变量：赋值、取值

        // 5、获取成员变量后：赋值、取值
        // 成员变量追着对象被访问 --- 反射
        Constructor con1 = c.getDeclaredConstructor();
        Cat cat = (Cat) con1.newInstance();

        Field fname = c.getDeclaredField("name");
        // 赋值
        fname.setAccessible(true);  // 绕过访问权限，暴力反射！
        fname.set(cat, "咖啡猫");
        // 取值
        String name = (String) fname.get(cat);
        System.out.println(name);

    }
}
