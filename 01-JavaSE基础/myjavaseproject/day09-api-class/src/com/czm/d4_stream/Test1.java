package com.czm.d4_stream;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Test1 {
    /*
     1、Stream（⚠️重点）
        也叫 Stream 流，是 jdk8 开始新增的一套API (java.util.stream.*)，可以用于操作集合或者数组的数据。

        优势：
        Stream 流大量的结合了 Lambda 的语法风格来编程，提供了一种更加强大，
        更加简单的方式操作集合或者数组中的数据，代码更简洁，可读性更好。

     2、Stream 流的使用步骤
        数据源(集合/数组/...) => 过滤 => 排序 => 去重 => (...) => 获取结果
            步骤1，Stream 流代表一条流水线，并能与数据源建立连接。
            步骤2，调用流水线 的各种方法对数据进行 处理、计算。
            步骤3，获取处理的结果，遍历、统计处理完后收集到一个新集合中返回。
     */
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("张三丰");
        list.add("曹操");
        list.add("张无忌");
        list.add("张帅");

        // 案例：查找处理 姓张，名字是3个字的元素，并组成数组返回

        // 方式1：通过增强for循环遍历
        List<String> newList = new ArrayList<>();
        for (String s : list) {
            if (s.startsWith("张") && s.length() == 3) {
                newList.add(s);
            }
        }
        System.out.println("--- newList = " + newList);

        // 方式2：使用 Stream 流改变
        List<String> newList2 = list.stream()
                                    .filter(s -> s.startsWith("张") && s.length() == 3)
                                    .collect(Collectors.toList());
        System.out.println("--- newList2 = " + newList2);

    }
}
