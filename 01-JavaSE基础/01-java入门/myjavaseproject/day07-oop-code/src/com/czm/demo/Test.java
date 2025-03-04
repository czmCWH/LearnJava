package com.czm.demo;

public class Test {
    /*
     1、案例：查询电影买票信息
	        展示全部电影信息；根据编号查询电影；
     2、实现步骤：
        1、设计电影类：创建电影对象，封装电影数据
        2、准备电影数据，存储一个一个电影
        3、分层思想：把电影数据交给电影操作业务对象处理
     */
    public static void main(String[] args) {
        // 1、准备好数据
        Movie[] movies = new Movie[5];
        movies[0] = new Movie(12, "好看1", 35.5, "演员1");
        movies[1] = new Movie(1,"好看2", 45.5, "演员2");
        movies[2] = new Movie(5, "好看3", 39.5, "演员3");
        movies[3] = new Movie(9, "好看4", 55.5, "演员4");
        movies[4] = new Movie(6,"好看5", 26.5, "演员5");

        // 2、业务操作对象
        MovieOperator operator = new MovieOperator(movies);
        operator.showAllMoviesInfo();
        operator.getMovieById();
    }
}
