package com.czm.branch;

public class Demo11BreakContinue {
    public static void main(String[] args) {
        /*
         1、跳转关键字
            break：跳出并结束当前所在循环的执行
            continue：用于跳出当前循环的当次执行，直接进入循环的下一次执行
         注意事项
            break：只能用于结束所在循环，或者结束所在 switch 分支的执行。
            continue：只能在循环中进行使用。
         */
        for (int i = 0; i < 5; i++) {
            System.out.println("打工第"+i+"年");
            if (i == 3) {
                System.out.println("结束了，赚了200万");
                break;
            }
        }
        System.out.println("---------------------");
        for (int j = 1; j <= 4; j++) {
            if (j == 3) {
                System.out.println("今天休息");
                continue;
            }
            System.out.println("拖地 " + j);
        }

    }
}
