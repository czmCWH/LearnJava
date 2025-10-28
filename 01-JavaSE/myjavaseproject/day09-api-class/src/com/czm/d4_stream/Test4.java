package com.czm.d4_stream;

import java.util.*;
import java.util.stream.Collectors;

public class Test4 {
    /*
     1、Stream 流常见的终结方法
        终结方法指的是调用完成后，不会返回新Stream了，没法继续使用流了

     2、收集 Stream 流
        收集stream流就是把 Stream 流操作后的结果转回到集合或者数组中去返回。
        Stream 流是方便操作集合/数组的手段；获取有用的 集合/数组 才是目的。

     */
    public static void main(String[] args) {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("【西游记】", 8.5, "吴承恩"));
        movies.add(new Movie("【水浒传】", 8.0, "施耐庵"));
        movies.add(new Movie("【三国演义】", 9.6, "罗贯中"));
        movies.add(new Movie("【红楼梦】", 9.9, "曹雪芹"));
        movies.add(new Movie("【西游记】", 8.5, "吴承恩"));
        movies.add(new Movie("【三国演义】", 9.6, "罗贯中"));

        System.out.println("----------------终结方法----------------");

        // 1、遍历方法
        System.out.println("--- 1、forEach 遍历方法：");
        movies.stream().forEach(System.out::println);

        // 2、获取 流个数
        long count = movies.stream().skip(2).count();
        System.out.println("--- 2、skip 忽略前2个，剩下几个 = " + count);

        // 3、最大、小值
        System.out.println("--- 3、最大、小值：");
        // 用 Optional 类型包一层，Optional可以存放 null，避免 null 指针异常。
        Optional<Movie> m = movies.stream().min((m1, m2) -> Double.compare(m1.getScore(), m2.getScore()));
        // 取出 Optional 对象里的值
        Movie min = m.get();
        System.out.println(min);
        Movie max = movies.stream().max((m1, m2) -> Double.compare(m1.getScore(), m2.getScore())).get();
        System.out.println(max);

        System.out.println();
        System.out.println("----------------收集 Stream 流----------------");
        System.out.println();

        List<String> list = new ArrayList<String>();
        list.add("张三丰");
        list.add("曹操");
        list.add("张帅");
        list.add("张无忌");
        list.add("张帅");

        System.out.println("--- 1、把 Stream 流中的数据收集到数组中：");
        // 方法1：collect(Collectors.toList())
//        List<String> newList1 = list.stream().filter(s1 -> s1.startsWith("张")).collect(Collectors.toList());
//        newList1.add("张道士");
//        System.out.println(newList1);

        // 方法2：JDK16 中的 toList() 方法，它返回一个不可变集合
        List<String> newList2 = list.stream().filter(s1 -> s1.startsWith("张")).toList();
//        newList2.add("张道士");    // 报错
        System.out.println(newList2);


        System.out.println();
        System.out.println("--- 2、把 Stream 流中的数据收集到 Set 集合中：");
        Set<String> st = list.stream().filter(s -> s.startsWith("张")).collect(Collectors.toSet());
        System.out.println(st);

        System.out.println();
        System.out.println("--- 3、把 Stream 流中的数据收集到 Array 数组中：");
        Object[] objArray = list.stream().filter(s -> s.startsWith("张")).toArray();
        System.out.println(Arrays.toString(objArray));

        System.out.println("--- 4、把 Stream 流中的数据收集到 Map 集合中：");

        List<Movie> movies2 = new ArrayList<>();
        movies2.add(new Movie("【西游记】", 8.5, "吴承恩"));
        movies2.add(new Movie("【水浒传】", 8.0, "施耐庵"));
        movies2.add(new Movie("【三国演义】", 9.6, "罗贯中"));
        movies2.add(new Movie("【水浒传】", 8.1, "施耐庵"));
        // ⚠️报错： Duplicate key 【水浒传】
//        Map<String,Double> mp = movies2.stream().collect(Collectors.toMap(m1 -> m1.getName(), m2 -> m2.getScore()));
//        System.out.println(mp);

        // 由于合并式，Map集合中 key 不能重复，但是 movies2 中存在重复 key，所以需要合并取出一个
        // Collectors.toMap(如何从元素提取 key，如何从元素提取 value，当 key 冲突时，合并函数‌如何合并 value)
        Map<String,Double> mp = movies2.stream().collect(Collectors.toMap(Movie::getName, Movie::getScore, (v1, v2) -> Double.max(v1, v2)));
        System.out.println(mp);     // {【三国演义】=9.6, 【水浒传】=8.1, 【西游记】=8.5}


        System.out.println("--- 5、把 Stream 流中的数据根据指定的 key 分组，收集到 Map 集合中：");
        // Collectors.groupingBy 是一个分组收集器，它会根据你提供的“分组依据”将元素分成若干组
        Map<String, List<Movie>> mp2 = movies2.stream().collect(Collectors.groupingBy(Movie::getName));
        System.out.println(mp2);
        /*
         {
         【三国演义】=[Movie{name='【三国演义】', score=9.6, actor='罗贯中'}],
         【水浒传】=[Movie{name='【水浒传】', score=8.0, actor='施耐庵'}, Movie{name='【水浒传】', score=8.1, actor='施耐庵'}],
         【西游记】=[Movie{name='【西游记】', score=8.5, actor='吴承恩'}]}
         */


    }
}
