package com.czm.d7_map;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// 用于实现排序

@Data   // 包含了无参构造器、get、set、toString、hashCode、equals
@NoArgsConstructor
@AllArgsConstructor
public class Movie1 implements Comparable<Movie1> {
    private String name;
    private double score;
    private String actor;

    @Override
    public int compareTo(Movie1 o) {
        return Double.compare(this.score, o.score);
    }
}
