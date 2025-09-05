package com.czm.create;

public class ArrayDemo2 {
    public static void main(String[] args) {
        /*
         1、动态初始化数组
         定义数组时先不存入具体的元素值，只确定数组存储的数据类型和数组的长度
         语法格式：
            数据类型[] 数组名 = new 数据类型[长度]

         2、动态初始化数组元素默认值规则:
            基本类型：
                byte、short、char、int、long ： 0
                float、double：0.0
                boolean：false
            引用类型：
                类、接口、数组、String：null

         */
        String[] names = new String[10];
        double[] scores = new double[10];
        int[] ages = new int[10];
        boolean[] flags = new boolean[10];
        System.out.println("----- 数组元素默认值");
        System.out.println("String[] = " + names[0]);
        System.out.println("double[] = " + scores[0]);
        System.out.println("int[] = " + ages[0]);
        System.out.println("boolean[] + " + flags[0]);

        System.out.println("------ 访问动态类型数组");
        names[0] = "张三";
        scores[0] = 90;
        System.out.println(names[0]);
        System.out.println(scores[0]);

        /*
         3、静态初始化数组 与 动态初始化数组 的区别：
            动态初始化：适合开始不确定具体元素值，只知道元素个数的业务场景；（常用）
            静态初始化:：适合一开始就知道要存入哪些元素值的业务场景；
         */
    }
}
