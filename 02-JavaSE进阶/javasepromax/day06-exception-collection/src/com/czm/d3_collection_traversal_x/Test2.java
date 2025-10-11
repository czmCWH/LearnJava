package com.czm.d3_collection_traversal_x;

import java.util.ArrayList;
import java.util.Collection;

public class Test2 {
    /*
     Collection 单列集合的常用方法

     1、案例：遍历对象数组

        数组在内存中的存储方式：
            在堆内存中开辟一个空间存放数组对象；
            创建一个 Movie 对象存放到堆内存中；
            每次把 Movie 对象添加到集合中时，是添加 Movie 对象的地址，而是它的内容；
     */
    public static void main(String[] args) {
        Collection<Movie> movies = new ArrayList<>();
        movies.add(new Movie("《三国演义》", "罗贯中", 9.0));
        movies.add(new Movie("《红楼梦》", "曹雪芹", 9.0));
        movies.add(new Movie("《水浒传》", "施耐庵", 9.0));

        // 3、遍历集合中的每个对象
        for (Movie movie : movies) {
            System.out.println(movie);
        }

    }
}
