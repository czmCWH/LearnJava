package com.czm.d5_Lambda;

import java.util.function.Function;

public class Test04Function {

    /*
      Function 函数式接口应用案例：
    */

    public static void main(String[] args) {

        String[] strs = {"1", "2", "3", "4", "5"};
//        int result = sum(strs, new Function<String, Integer>() {
//            @Override
//            public Integer apply(String str) {
//                return Integer.parseInt(str);
//            }
//        });
        // 简写：
        int result = sum(strs, (str) -> {
            return Integer.valueOf(str);
        });
        // 简写：
//        sum(strs, Integer::valueOf);

        System.out.println("--- result = " + result);   // result = 15

        int result2 = sum(strs, (str) -> {
            return Integer.valueOf(str);
        }, (num) -> {
           return num * 10;
        });
        System.out.println("--- result2 = " + result2); // result2 = 150

    }

    static int sum(String[] strs, Function<String, Integer> f) {
        if (strs == null || f == null) return 0;
        int res = 0;
        for (String s : strs) {
            res += f.apply(s);  // ⚠️，Function.apply 表示接受一个值，处理完后返回一个值
        }
        return res;
    }

    static int sum(String[] strs, Function<String, Integer> f1, Function<Integer, Integer> f2) {
        if (strs == null || f1 == null || f2 == null) return 0;
        int res = 0;
        for (String s : strs) {
//            res += f1.andThen(f2).apply(s);  // ⚠️，Function.andThen 表示 f1 通过 apply 处理完返回的值，传递给 f2 使用 apply 处理。
            res += f2.compose(f1).apply(s);     // Function.compose 与 andThen 的执行顺序反过来，从右到左执行 apply 传递值。
        }
        return res;
    }
}
