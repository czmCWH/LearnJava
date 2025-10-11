package com.czm.d6_Lambda;

import java.util.Arrays;
import java.util.Comparator;

public class Test01Lambda {

    /*
     1、Lambda Expression
      Lambda 表达式是 Java 8 开始才有的语法。

      函数式接口（Functional Interface）：只包含1个抽象方法的接口，并定义的接口上面加上 @FunctionalInterface 注解来表示它是一个函数式接口。

      ⚠️ 作用：当匿名类实现的是函数式接口时，可以使用 Lambda 表达式进行简化！

     2、Lambda 表达式简化规则
      a、参数列表可以省略参数类型；
      b、当只有一条语句时，可以省略大括号、分号、return；
      c、当只有一个参数时，可以省略小括号；
      d、当没有参数时，不能省略小括号；

     3、Lambda 的使用注意
      Lambda 只能访问 final 或者 有效 final 的局部变量。
      Lambda 没有引入新的作用域。

     4、
     */

    public static void main(String[] args) {

        Integer[] array = {10, 1, 33, 2, 3, 22, 11, 22};
        Arrays.sort(array); // 默认是升序，小的放左边，大的放右边
        System.out.println(Arrays.toString(array));     // [1, 2, 3, 10, 11, 22, 22, 33]

        Arrays.sort(array, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });

        // Lambda 表达式简化 函数式接口类型
        Arrays.sort(array, (Integer o1, Integer o2) -> {
            return o2 - o1;
        });

        // Lambda 表达式简化 函数式接口类型
        Arrays.sort(array, ( o1, o2) -> o2 - o1);

        System.out.println("\n");
        // Lambda 没有引入新的作用域!
        OuterClass outer = new OuterClass();
        OuterClass.InnerClass inner = outer.new InnerClass();
        inner.doSomething();

    }
}
