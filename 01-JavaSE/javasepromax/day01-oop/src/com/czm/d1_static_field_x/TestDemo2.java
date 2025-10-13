package com.czm.d1_static_field_x;

public class TestDemo2 {
    /*
     1、static成员变量应用场景
        系统启动后，要求用户类可以记住自己创建了多少个用户对象了。
     */
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new User();
        }
        System.out.println("---总共创建了多少次 User 实例 = " + User.total);
    }
}
