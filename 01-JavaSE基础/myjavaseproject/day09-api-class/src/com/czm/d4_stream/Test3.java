package com.czm.d4_stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test3 {
    /*
     1、Stream 流常见的中间方法
        中间方法指的是调用完成后会返回新的 Stream 流，可以继续使用(支持链式编程)。
     2、
     3、
     */
    public static void main(String[] args) {
        // 1、获取集合的 Stream 流
        Collection<String> list = new ArrayList<>();
        Collections.addAll(list, "诸葛亮", "刘备", "孙策", "司马懿", "孙权", "刘表");

        // 1、过滤方法
        list.stream().filter(s -> s.startsWith("刘")).forEach(System.out::println);

        // 2、准备一个集合，排序
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("【西游记】", 8.5, "吴承恩"));
        movies.add(new Movie("【水浒传】", 8.0, "施耐庵"));
        movies.add(new Movie("【三国演义】", 9.6, "罗贯中"));
        movies.add(new Movie("【红楼梦】", 9.9, "曹雪芹"));
        movies.add(new Movie("【西游记】", 8.5, "吴承恩"));
        movies.add(new Movie("【三国演义】", 9.6, "罗贯中"));

        System.out.println("--- sorted 排序 方法1，生序排序");
        // 注意⚠️：对于自定义类型，需实现 Comparable 接口指定比较规则。
        movies.stream().sorted().forEach(System.out::println);

        System.out.println("--- sorted 排序 方法2，降序排序");
        movies.stream().sorted(new Comparator<Movie>() {
            @Override
            public int compare(Movie o1, Movie o2) {
                return Double.compare(o2.getScore(), o1.getScore());
            }
        }).forEach(System.out::println);

        // 3、截取前几个
        System.out.println("---  3、limit 截取前2几个：");
        movies.stream().sorted((o1, o2) -> Double.compare(o2.getScore(), o1.getScore())).limit(2).forEach(System.out::println);

        // 4、跳过前几个
        System.out.println("--- 4、skip 跳过前 2 几个：");
        movies.stream().sorted((m1, m2) -> Double.compare(m2.getScore(), m1.getScore())).skip(2).forEach(System.out::println);

        // 5、去重复
        System.out.println("--- 5、distinct 去重复：");
        // 注意⚠️：需重写对象的 equals 和 hashCode 方法
        movies.stream().distinct().forEach(System.out::println);

        // 6、map 转换，把流上的数据转换成新的数据
        System.out.println("--- 6、map 转换，把流上的数据转换成新的数据");
        movies.stream().map(s -> s.getName() + " " + s.getScore()).distinct().forEach(System.out::println);

        // 7、合并流，把2个流合并起来
        System.out.println("--- 7、合并流，把2个流合并起来：");
        Stream<String> s1 = Stream.of("AA", "BB", "CC");
        Stream<String> s2 = Stream.of("张三", "李斯", "王五");
        Stream<String> allS = Stream.concat(s1, s2);
        System.out.println(allS.collect(Collectors.toList()));
    }
}
