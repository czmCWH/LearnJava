package com.czm.d2_reflect;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class Test4Method {

    // 通过反射获取成员方法

    @Test
    public void testGetMethods() throws Exception {
        // 1、反射第一步，先得到 Class 对象
        Class c = Cat.class;

        // 2、获取类的全部方法（包括：private 方法、static 方法）
        Method[] methods = c.getDeclaredMethods();
        //        c.getMethods()    // 基本不用，只能饿获取 public 修饰的方法

        // 3、遍历数组中每个方法对象
        for (Method method : methods) {
            System.out.println("方法名称 + 方法参数个数：" + method.getName() + " " + method.getParameterCount());
        }

        // 4、定位单个方法
        Method eat1 = c.getDeclaredMethod("eat");   // 获取无参的 eat 方法
        Method eat2 = c.getDeclaredMethod("eat", String.class); // 获取 String 类型参数的 eat 方法

        // --- 使用反射！
        // 获取到方法的作用：调用方法

        // 5、定位到方法后，调用方法
        Constructor con1 = c.getDeclaredConstructor();
        Cat cat = (Cat) con1.newInstance();

        // 调用方法：方法追着对象调用 --- 反射
        Object res1 = eat1.invoke(cat); // 唤醒 cat 对象的 eat1 方法执行，相当于 cat.eat();
        System.out.println("--- eat() 执行结果：" + res1);

        // 调用私有方法
        eat2.setAccessible(true);   // 绕过访问权限，暴力反射！
        Object res2 = eat2.invoke(cat, "龙猫");   // 唤醒 cat 对象的 eat2 方法执行，相当于 cat.eat(String);
        System.out.println("--- eat(String) 执行结果：" + res2);

    }
}
