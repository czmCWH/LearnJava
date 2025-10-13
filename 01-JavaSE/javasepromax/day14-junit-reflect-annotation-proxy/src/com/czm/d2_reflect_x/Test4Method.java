package com.czm.d2_reflect_x;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Test4Method {

    // 通过反射获取成员方法

    @Test
    public void testGetMethods() throws Exception {
        // 1、反射第一步，先得到 Class 对象
        Class c = Cat.class;

        // 2、获取类的全部方法
        Method[] methods = c.getDeclaredMethods();

        // 3、遍历数组中每个方法对象
        for (Method method : methods) {
            System.out.println(method.getName() + " " + method.getParameterCount());
        }

        // 4、定位单个方法
        Method eat1 = c.getDeclaredMethod("eat");
        Method eat2 = c.getDeclaredMethod("eat", String.class);

        // 5、定位到方法后，调用方法
        Constructor con1 = c.getDeclaredConstructor();
        Cat cat = (Cat) con1.newInstance();

        Object res1 = eat1.invoke(cat);
        System.out.println(res1);

        eat2.setAccessible(true);   // 暴力反射
        Object res2 = eat2.invoke(cat, "龙猫");
        System.out.println(res2);

    }
}
