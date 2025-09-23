package com.czm.d3_collections.Test04Set;

import java.util.*;

public class Test01HashSet {
    /*
      1、HashSet - 无序Set

      2、
      3、
     */
    public static void main(String[] args) {
        Set<String> st = new HashSet<>();
        st.add("Jack");
        st.add("Tom");
        st.add("Kate");
        st.add("Jack");
        st.add("Tom2");
        st.remove("Tom2");
        System.out.println("--- set = " + st);  // [Tom, Kate, Jack]，重复添加 Jack 无效
        // Set 是无序的，打印 Set 时的顺序与 元素的哈希值、添加顺序 有关系。

        System.out.println("\n--- 2、HashSet 的应用：数组去重");
        String[] str = {"Jack", "Tom", "Kate", "Jack", "Tom", "Kate"};
        Set<String> st2 = new HashSet<>();
        for (String s : str) {
            st2.add(s);
        }
        String[] str2 = st2.toArray(new String[0]);
        System.out.println("--- str2 = " + Arrays.toString(str2));  // str2 = [Tom, Kate, Jack]

        System.out.println("\n--- 3、HashSet 的遍历：");
        System.out.println("--- for-each 方式遍历：");
        // for-each 方式 等价于 迭代器方式遍历
        for (String s : st) {
            System.out.print(s + "、");
        }
        System.out.println();

        System.out.println("\n--- Iterator 迭代器方式遍历：");
        Iterator<String> it = st.iterator();
        while (it.hasNext()) {
            String s = it.next();   // 迭代器方式访问 set 元素的顺序与 打印 set 对象的顺序是一致的
            System.out.print(s + "、");
        }
        System.out.println();

        System.out.println("\n--- forEach 函数式接口遍历：");
        st.forEach((s) -> {
            System.out.print(s + "、");
        });




    }
}
