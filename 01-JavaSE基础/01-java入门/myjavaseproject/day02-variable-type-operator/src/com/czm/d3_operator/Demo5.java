package com.czm.d3_operator;

public class Demo5 {
    public static void main(String[] args) {
        /*
         1、逻辑运算符
         把多个条件放在一起运算，最终返回布尔类型的值:true、false.
         &，逻辑与，例如：2>1 & 3>2，多个条件必须都是true，结果才是true;有一个是false，结果就是false
         |，逻辑或，例如：2>1 | 3>5，多个条件中只要有一个是true，结果就是true
         !，逻辑非，例如：!(1>2)，逻辑取反，!true == false，!false == true
         ^，逻辑异或，例如：2>1 ^ 3>1，前后条件的结果相同，就直接返回false，前后条件的结果不同，才返回true

         && 短路与，例如：2>10 && 3>2，判断结果与“&”一样，过程不同：左边为 false，右边则不执行。
         || 短路或，例如：2>1 || 3<5，判断结果与“|”一样，过程不同：左边为 true，右边则不执行，

         注意:实际开发中、常用的逻辑运算符还是:&&、‖、!
         */
        double height = 160.0;
        char sex = '女';
        System.out.println(height >= 160 & sex == '女');     // true

        System.out.println(height >= 160 ^ sex == '女');     // false
        System.out.println(height >= 160 ^ sex == '男');     // true

        int i = 10;
        int j = 22;
        System.out.println(i < 5 & ++j > 33);
        System.out.println(j);  // 打印：23
        int m = 10;
        int n = 22;
        System.out.println(m < 5 && ++n > 33);
        System.out.println(n);  // 打印：22
    }
}
