package com.czm.arraylist;

import java.util.ArrayList;

public class ArrayListDemo1 {
    /*
     1、什么是集合?
	    集合是一种容器，用来装数据的，类似于数组。
	    集合的种类非常多，比如：ArrayList

     2、有数组，为啥还学习集合?
	    数组定义完成并启动后，长度就固定了。
	    集合大小可变，开发中用的更多。
     */
    public static void main(String[] args) {
        // 1、创建一个 ArrayList 集合对象，它代表一个容器，其大小可变，数据可重复，有索引
//        ArrayList list = new ArrayList();
        // 集合中尽量存储相同类型的数据
        ArrayList<String> list = new ArrayList<>();
        list.add("java");
        list.add("java");
        // list 指向对象的地址，官方已经处理了通过地址直接找内容
        System.out.println(list);   // 打印：[java, java]

        // 2、根据索引插入位置
        list.add(2, "python");
        System.out.println("2、根据索引插入位置 =" + list);   // 打印：[java, java, python]

        // 3、根据索引获取数据
        String el = list.get(2);
        System.out.println("3、根据索引获取数据 =" + el);     // 打印：python

        // 4、获取集合中的元素的个数
        System.out.println("4、集合元素个数 = " + list.size());

        // 5、删除指定索引处的元素，返回被删除的元素
        String del = list.remove(2);
        System.out.println("5、删除指定索引处的元素，返回被删除的元素 = " + del);

        // 6、删除指定的元素，返回删除是否成功。默认只能删除第一个
        boolean res = list.remove("java");
        System.out.println("6、删除指定的元素，返回删除是否成功 = " + res);

        // 7、修改指定索引处的元素，返回被修改的元素
        String ch = list.set(0, "python");
        System.out.println("7、修改指定索引处的元素，返回被修改的元素 = " + ch);
    }
}
