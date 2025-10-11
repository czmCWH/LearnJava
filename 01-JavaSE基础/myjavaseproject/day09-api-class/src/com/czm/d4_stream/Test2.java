package com.czm.d4_stream;

import java.util.*;
import java.util.stream.Stream;

public class Test2 {
    /*
     1、集合/数组 获取 Stream 流

        a、获取当前集合对象的 Stream 流：
            Collection 提供 default Stream<E> stream();

        b、获取数组的 Stream 流：
            Arrays.stream(数组对象)：Arrays 类的静态方法，获取当前数组的 Stream 流。
            Stream.of(可变参数)：Stream 类的静态方法，获取当前接收数据的stream流。

     */
    public static void main(String[] args) {
        System.out.println("------- 1、获取集合的 Stream 流");
        Collection<String> list = new ArrayList<>();
        Collections.addAll(list, "孙悟空", "猪八戒", "唐僧", "沙和尚");
        Stream<String> s1 = list.stream();
        System.out.println(s1.count());

        System.out.println("------- 2、获取 Map 集合的 Stream 流");
        Map<String,Integer> mp = new HashMap<>();
        // a、获取键流
        Stream<String> mps1 = mp.keySet().stream();
        // b、获取值流
        Stream<Integer> mp2 = mp.values().stream();
        // c、获取键值对流
        Stream<Map.Entry<String, Integer>> mp3 = mp.entrySet().stream();

        System.out.println("------- 3、获取 数组的 Stream 流");
        String[] names = {"诸葛亮", "曹操", "孙策", "刘备"};
        // 方法1:
        Stream<String> s2 = Arrays.stream(names);
        // 方法2:
        Stream<String> s3 =Stream.of(names);
    }
}
