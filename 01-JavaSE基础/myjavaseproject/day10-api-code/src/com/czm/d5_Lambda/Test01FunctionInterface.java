package com.czm.d5_Lambda;

import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.sql.SQLOutput;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Test01FunctionInterface {

    /*
      1、Lambda Expression
      Lambda 表达式是 Java 8 开始才有的语法。

      2、Functional Interface
      函数式接口（Functional Interface）：只包含1个抽象方法的接口，并且需要在接口定义上面加上 @FunctionalInterface 注解。
      当 匿名类 实现的是 函数式接口 时，可以使用 Lambda 表达式进行简化。

      3、java.util.function 包提供的常用函数式接口
        Supplier，提供者，提供一个值
        Consumer，消费者，接收一个值来处理
        Predicate，预期判断，接收一个值返回一个条件状态
        Function，转换，接收一个值返回任意类型的值
     */

    public static void main(String[] args) {

        System.out.println("--- 👉 1、Supplier 函数式接口的使用：");
        // 如下方法存在问题，参数2 未被使用，但是 makeStr 方法依然被调用，有什么办法让
//        getContentString("abc", makeStr());

        // 使用 Supplier 函数式接口后，参数1 满足条件后，参数2将不会被执行
        getContentString2("abc", new Supplier<String>() {
            @Override
            public String get() {
                return makeStr();
            }
        });
        // 简写匿名类实现的函数式接口：
        getContentString2("abc", () -> makeStr());
    }

    // ---------- 1、Supplier 函数式接口解析
    static String getContentString(String s1, String s2) {
        if (s1 != null && s1.length() > 0) return s1;
        if (s2 != null && s2.length() > 0) return s2;
        return null;
    }

    static String getContentString2(String s1, Supplier<String> s2) {
        if (s1 != null && s1.length() > 0) return s1;
        String str = s2.get();  // ⚠️ Supplier.get 获取外部的值
        if (str != null && str.length() > 0) return str;
        return null;
    }

    static String makeStr() {
        System.out.println("--- 执行了 makeStr！");
        return String.format("%d %d %d", 1, 2, 3);
    }

}
