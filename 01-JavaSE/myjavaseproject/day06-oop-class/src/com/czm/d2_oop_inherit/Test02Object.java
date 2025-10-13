package com.czm.d2_oop_inherit;

import java.util.Objects;

public class Test02Object {
    /*
     1、Object 类
      0bject 类是 Java 中所有类的基类，因此，Java 中所有类的对象都可以直接使用 Object 类中提供的一些方法。
      Object 类常见方法：
        toString()	返回对象的字符串表示形式。
        equals()	判断两个对象是否相等。默认比较对象的地址是否相等，可以重写此方法制定比较规则。

      比较两个对象的地址是否一样可以直接用 “==” 比较，完全没有必要用 equals 去比较。
      0bject 提供的 equals 的意义是为了让子类重写来自定义比较规则。

     2、Objects 工具类
      Objects 是一个工具类，提供了很多操作对象的静态方法给我们使用。
         Objects.equals()，先做非空判断，再比较两个对象的地址。它是对 equals 的一层包装，保证了空安全。
         Objects.isNull()，判断对象是否为null，为null返回true,反之
         Objects.nonNull()，判断对象是否不为null，不为null则返回true，反之.

     */

    public static void main(String[] args) {
        Person p1 = new Person(18);
        // 1、打印实例时会调用 toString 方法
        System.out.println(p1);
        System.out.println(p1.toString());

        Person p2 = new Person(18);
        System.out.println(p1.equals(p2));  // false

        System.out.println(Objects.equals(p1, p2));     // false

        int a = 127;
        Integer b = 127;
        System.out.println(a == b);     // true
        System.out.println(Objects.equals(a, b));   // true

        Integer n1 = 128;
        Integer n2 = 128;
        System.out.println("---- == 比较 = " + (n1 == n2));   // false
        System.out.println("---- equals() 比较 = " + (n1.equals(n2)));     // true
        System.out.println("---- Objects.equals()：比较 = " + (Objects.equals(n1, n2)));   // true

    }
}
