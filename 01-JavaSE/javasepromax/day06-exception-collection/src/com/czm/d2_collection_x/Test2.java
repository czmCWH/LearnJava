package com.czm.d2_collection_x;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class Test2 {
    /*
     1、Collection 单列集合的常用方法
     */
    public static void main(String[] args) {
        Collection<String> cl = new ArrayList<>();
        cl.add("java");
        cl.add("java");
        cl.add("开心");
        cl.add("typescript");
        cl.add("kotlin");
        cl.add("java");
        cl.add("你好");
        System.out.println(cl);

        // 1、清空集合
//        cl.clear();
//        System.out.println(cl);

        // 2、判断集合是否为空
        System.out.println(cl.isEmpty());

        // 3、删除集合中的第一次出现的某个元素

        System.out.println("删除某个元素 = " + cl.remove("java"));
        System.out.println(cl);

        // 4、判断集合中是否包含某个元素
        System.out.println(cl.contains("java"));
        System.out.println(cl.contains("Java"));

        // 5、获取集合元素个数
        System.out.println(cl.size());

        // 6、把集合转数组，便于传递参数
        Object[] array = cl.toArray();
        array[0] = 12;
        System.out.println(Arrays.toString(array));

        // 扩展，使用方法引用，把集合转换为特定类型
        String[] strArray = cl.toArray(String[]::new);

        // 7、把集合数据叠加
        Collection<String> c1 = new ArrayList<>();
        c1.add("AA");
        c1.add("BB");
        Collection<String> c2 = new ArrayList<>();
        c2.add("11");
        c2.add("22");

        c1.addAll(c2);
        System.out.println(c1);
        System.out.println(c2);
    }
}
