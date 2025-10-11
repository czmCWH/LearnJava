package com.czm.d2_reflect;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;

public class ObjectFrame {

    // 保存任意对象的字段和其数据到文件中

    public static void saveObject(Object obj) throws Exception {

        //
        PrintWriter pw = new PrintWriter(new FileWriter("day14-junit-reflect-annotation-proxy/src/obj.txt", true));

        // 1、反射第一步，先得到 Class 对象
        Class c = obj.getClass();

        // 获取类名
//        String className = c.getName();   // 获取的是：包名.类名
        String className = c.getSimpleName();   // 只获取类名
        pw.println("===================" + className + "===============");

        // 2、提取这个 Class 的全部字段
        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            field.setAccessible(true);
            String value = field.get(obj) + "";  // 成员变量获取对象的值
            pw.println(name + "=" + value);
        }

        pw.close();

    }
}
