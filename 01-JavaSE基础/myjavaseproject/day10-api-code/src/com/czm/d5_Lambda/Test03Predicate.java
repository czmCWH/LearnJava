package com.czm.d5_Lambda;

import java.util.function.Predicate;

public class Test03Predicate {

    /*
      Predicate 函数式接口应用案例：
    */

    public static void main(String[] args) {

        System.out.println("--- 👉 Predicate 函数式接口的使用：");
        int[] nums = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15};
        String joinStr = join(nums, (el) -> {
            return (el & 1) == 0;
        });
        System.out.println("--- nums to joinStr = " + joinStr);     // 2_4_6_8_10_12_14

        String joinStr2 = join(nums, (el) -> {
            return (el & 1) == 0;
        }, (el) -> {
            return (el % 3) == 0;
        });
        System.out.println("--- nums to joinStr2 = " + joinStr2);   // 6_12
    }

    static String join(int[] list, Predicate<Integer> p) {
        if (list == null || p == null) return null;
        StringBuilder sb = new StringBuilder();
        for (int el : list) {
            if (p.test(el)) {   // ⚠️ Predicate.test 接收外面的条件作为判断条件
                sb.append(el).append("_");
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    // Predicate - and / or / negate
    static String join(int[] list, Predicate<Integer> p1, Predicate<Integer> p2) {
        if (list == null || p1 == null || p2 == null) return null;
        StringBuilder sb = new StringBuilder();
        for (int el : list) {
            if (p1.and(p2).test(el)) {   // ⚠️ p1 和 p1 的 test 必须同时为 true
                sb.append(el).append("_");
            }
//            if (p1.or(p2).test(el)) {   // ⚠️ p1 和 p1 的 test 至少一个为 true
//                sb.append(el).append("_");
//            }
//            if (p1.and(p2).negate().test(el)) {   // ⚠️ 对 p1 和 p2 的 test 结果取反
//                sb.append(el).append("_");
//            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
