package com.czm.d1_operator;

public class d5_ternary {
    public static void main(String[] args) {
        /*
         1、三元运算符
         格式: 条件表达式 ? 值1 ∶ 值2;
         执行流程: 首先计算关系表达式的值，如果值为true，返回 值1，如果为 false，返回 值2
         */
        double score = 99.5;
        String result = score >= 60 ? "考试通过" : "考试挂科";
        System.out.println(result);

        int a = 110;
        int b = 10;
        int c = 200;
        int max = a > b ? a > c ? a : c : b > c ? b : c;
        System.out.println(max);

        System.out.println("---------------------");
        /*
         2、运算符优先级
         在表达式中，哪个运算符先执行后执行是要看优先级的，例如 “*、/” 的优先级高于 ”+、-"
         注意：&& 的优先级高于 ||
         */
        System.out.println(10 > 3 || 10 < 5 && 10 > 5);
        System.out.println((10 > 3 || 10 < 5) && 10 > 5);
    }
}
