package com.czm.object06demo;

import java.util.Scanner;

// 2、业务操作类
public class MovieOperator {
    private Movie[] movies;
    // 通过有参构造器初始化数据
    public MovieOperator(Movie[] movies) {
        this.movies = movies;
    }

    // 业务操作类的方法
    public void showAllMoviesInfo() {
        System.out.println("===全部电影信息如下===");
        for (int i = 0; i < movies.length; i++) {
            Movie m = movies[i];
            System.out.println(m.getId() + "\t" + m.getName() + "\t" + m.getPrice() + "\t" + m.getActor());
        }
    }

    public void getMovieById() {
        System.out.println("请输入查询电影ID：");
        Scanner s = new Scanner(System.in);
        int id = s.nextInt();
        System.out.println("===查找电影信息如下===");
        for (int i = 0; i < movies.length; i++) {
            Movie m = movies[i];
            if (m.getId() == id) {
                System.out.println(m.getId() + "\t" + m.getName() + "\t" + m.getPrice() + "\t" + m.getActor());
                return;
            }
        }
        System.out.println("===查无此电影");
    }
}
