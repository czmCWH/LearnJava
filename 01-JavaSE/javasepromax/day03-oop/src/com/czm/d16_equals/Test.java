package com.czm.d16_equals;

import java.util.Objects;

public class Test {
    /*
     1、⚠️⚠️⚠️相等性比较
        ==：引用类型比较地址，值类型比较值
        equals()：得看具体的类型是否重写此方法，如果没有重写则是地址比较，否则按照重写的逻辑
        Objects.equals()：对 equals 的一层包装，保证了空安全

            == 一般是比较地址；
            equals 一般通过重写来比较内容；
     */
    public static void main(String[] args) {
        System.out.println("============ 1、普通类比较 ==========");
        Car c1 = new Car();
        Car c2 = new Car();
        System.out.println("---- == 比较 = " + (c1 == c2));   // false
        System.out.println("---- equals() 比较 = " + (c1.equals(c2)));     // false
        System.out.println("---- Objects.equals()：比较 = " + (Objects.equals(c1, c2)));    // false

        System.out.println("============ 2、包装类比较 ==========");
        Integer t1 = 127;
        Integer t2 = 127;
        System.out.println("---- == 比较 = " + (t1 == t2));   // false
        System.out.println("---- equals() 比较 = " + (t1.equals(t2)));     // false
        System.out.println("---- Objects.equals()：比较 = " + (Objects.equals(t1, t2)));    // false
        System.out.println("------------------------包装类型超出混存池");
        Integer n1 = 128;
        Integer n2 = 128;
        System.out.println("---- == 比较 = " + (n1 == n2));   // false
        System.out.println("---- equals() 比较 = " + (n1.equals(n2)));     // false
        System.out.println("---- Objects.equals()：比较 = " + (Objects.equals(n1, n2)));


    }
}
