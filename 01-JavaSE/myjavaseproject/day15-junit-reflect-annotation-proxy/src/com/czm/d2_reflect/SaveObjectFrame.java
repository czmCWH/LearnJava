package com.czm.d2_reflect;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;

/**
 * 反射案例：保存任意对象的字段和其数据到文件中
 * 反射最重要的作用是，做框架的通用技术！
 */
public class SaveObjectFrame {
    public static void saveObject(Object obj) throws Exception {

        // 打印流，保存数据到文件
        PrintWriter ps = new PrintWriter(new FileOutputStream("day15-junit-reflect-annotation-proxy/src/obj.txt", true));

        // 1、反射第一步，先得到 Class 对象
        Class c = obj.getClass();

        // 2、获取类名
//        String className = c.getName();   // 获取的是：包名.类名
        String className = c.getSimpleName();   // 只获取类名
        ps.println("===================" + className + "===============");

        // 3、提取这个 Class 的全部字段
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            // 获取字段名
            String name = field.getName();
            field.setAccessible(true);  // 暴力反射每个字段
            // 成员变量获取对象的值
            String value = field.get(obj) + "";
            // 打印到文件中
            ps.println(name + "=" + value);
        }

        ps.close();
    }
}
