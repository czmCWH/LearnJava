package com.czm.d7_map_x;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data   // 包含了无参构造器、get、set、toString、hashCode、equals
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
    private String name;
    private double score;
    private String actor;
}
