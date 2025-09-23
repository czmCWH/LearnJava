package com.czm.d5_Lambda;

import java.util.function.Consumer;

public class Test02Consumer {

    /*
      Consumer 函数式接口应用案例：
    */

    public static void main(String[] args) {
        System.out.println("--- 👉 1、Consumer 函数式接口的使用：");
        int[] list = new int[] {1, 2, 3, 4, 5};
//        forEach(list, new Consumer<Integer>() {
//            @Override
//            public void accept(Integer el) {
//                System.out.println("处理获取的 偶数：" + el);
//            }
//        });
        // 简写
        forEach(list, (Integer el) -> {
            System.out.println("处理获取的 偶数：" + el);
        });
        // 简写
//        forEach(list, System.out::println);

        System.out.println("\n--- 👉 2、Consumer - andThen：");
        forEach(list, (el) -> {
            System.out.printf("--- 第1次处理 %d：", el);
        }, (el) -> {
            System.out.println("--- 第2次处理：" + el);
        });
    }

    static void forEach(int[] list, Consumer<Integer> consumer) {
        if (list == null) return;
        for (int i = 0; i < list.length; i++) {
            if ((list[i] & 1) == 0) consumer.accept(list[i]);   // ⚠️ Consumer.accept 把内部的值传递给外部操作
        }
    }

    static void forEach(int[] list, Consumer<Integer> c1, Consumer<Integer> c2) {
        if (list == null) return;
        for (int n : list) {
            if ((n & 1) == 0) {
                c1.andThen(c2).accept(n);
                // 等价于
//                c1.accept(n);
//                c2.accept(n);
            }
        }
    }
}
