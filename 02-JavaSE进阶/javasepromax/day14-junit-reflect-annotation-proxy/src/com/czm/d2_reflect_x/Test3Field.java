package com.czm.d2_reflect_x;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class Test3Field {

    // 通过反射，获取类的成员变量，使用该成员变量进行赋值、取值操作。

    @Test
    public void testGetFields() throws Exception {

        // 1、反射第一步，必须先拿到类的 Class 对象
        Class c = Cat.class;

        // 2、获取类的所有成员变量
        Field[] fields = c.getDeclaredFields();

        // 3、遍历这个成员变量数组
        for (Field field : fields) {
            System.out.println(field.getType() + " => " + field.getName());
        }

        // 4、定位某个成员变量
        Field fname = c.getDeclaredField("name");

        // 5、获取成员变量后：赋值、取值
        Constructor con1 = c.getDeclaredConstructor();
        Cat cat = (Cat) con1.newInstance();

        fname.setAccessible(true);
        fname.set(cat, "咖啡猫");

        String name = (String) fname.get(cat);
        System.out.println(name);

    }
}
