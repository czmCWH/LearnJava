package com.czm.d1_NestedClass;

/**
 * 静态嵌套类的特点
 */
public class Company {
    private static String doc = "内部文档";
    private static void usePatent() {
        System.out.println("--- 使用专利！");
    }

    /**
     * 定义一个静态嵌套类
     */
    public static class Person {
        public void startBusiness() {
            System.out.println("--- 静态嵌套类访问外部类的 private static 成员：");
            System.out.println(doc);
            usePatent();
        }
    }
}