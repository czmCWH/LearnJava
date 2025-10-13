package com.czm.d1_static_field_x;

public class User {
    public static int total;
    public User() {
        // 注意：访问当前类中的静态变量，类名可以省略。
        total++;
    }
}
