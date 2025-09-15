package com.czm.string;

public class Test03Intern {
    public static void main(String[] args) {
        /*
         1、String 的 intern 方法
             A.intern 方法的作用：
                如果 SCP 中存在与 A 内容一样的字符串对象C 时，就返回 C；
                否则，将 A 加入到 SCP 中，返回 A；
         */
        int a = 1, b = 2, c = 3;
        String s1 = String.format("%d%d%d", a, b, c);
        String s2 = String.format("%d%d%d", a, b, c);
        String s3 = s1.intern();    // SCP 中不存在 s1 内容一样的对象，将 s1 指向的对象加入到 SCP 中。此时，s1、s3 都指向 SCP 中的对象。
        String s4 = s2.intern();    // SCP 中存在 s2 内容一样的对象，返回常量池中的 "123" 对象。s4 指向 SCP 中的对象

        String s5 = "123";  // 直接从 SCP 中获取对象。

        System.out.println(s1 == s2);   // false，s1 与 s2 指向了不同的对象。
        System.out.println(s1 == s3);   // true
        System.out.println(s1 == s4);   // true
        System.out.println(s1 == s5);   // true
    }
}
