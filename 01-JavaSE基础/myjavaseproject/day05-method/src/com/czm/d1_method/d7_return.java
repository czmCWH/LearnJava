package com.czm.d1_method;

public class d7_return {

    /*
     return关键字在方法中单独使用
        return;可以用在无返回值的方法中，作用是：立即跳出并结束当前方法的执行
     */

    public static void main(String[] args) {

    }

    public static void divideTo(int a, int b) {
        if (b == 0) {
            System.out.println("--- 你的除数不能为0");
            return; // 跳出并立即结束当前方法的执行。卫语句拦截，(阿里巴巴开发指南!)
        }
        int c = a / b;
        System.out.println("---" + c);
    }

}
