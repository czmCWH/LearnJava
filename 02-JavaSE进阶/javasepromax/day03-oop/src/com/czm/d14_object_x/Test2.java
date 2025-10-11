package com.czm.d14_object_x;

import java.util.Objects;

public class Test2 {
    /*
     1、问题1
     Java 源码中为啥比较两个对象是否相等，要用 0bjects.equals 方法，而不是用对象自己的 equals 方法来比较呢???

     2、Objects 工具类
     Objects 是一个工具类，提供了很多操作对象的静态方法给我们使用。

     3、Objects 方法
         Objects.equals()，无做非空判断，再比较两个对象

         Objects.isNull()，判断对象是否为null，为null返回true,反之

         Objects.nonNull()，判断对象是否不为null，不为null则返回true，反之.

     */
    public static void main(String[] args) {
        // 1、问题1
        Student t1 = null;
        Student t2 = new Student("小红", 18, 96.3);
//        System.out.println(t1.equals(t2));    // 报错，因为 t1 为 null，调用方法抛出空指针异常。
        System.out.println(Objects.equals(t1, t2));     // 因此使用 Objects.equals 更安全，更可靠

        System.out.println("-------- Objects 其它方法 ");
        System.out.println(Objects.isNull(t1));
        System.out.println(Objects.nonNull(t2));
    }
}
