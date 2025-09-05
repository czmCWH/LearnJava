package com.czm.branch;

import java.util.Scanner;

public class Demo2Switch {
    public static void main(String[] args) {
        /*
         1、switch 分支
         是通过比较值来决定执行哪条分支。
         switch分支的执行流程：
            1、先执行表达式的值，再拿着这个值去与case后的值进行匹配。
            2、与哪个case后的值匹配为true，就执行哪个case块的代码，遇到 break 就跳出 switch 分支。
            3、如果全部case后的值与之匹配都是false，则执行default块的代码
         */
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入今天周几：");
        String weekDay = sc.nextLine();
        switch (weekDay) {
            case "周一":
                System.out.println("又到周一，人已宕机");
                break;
            case "周二":
                System.out.println("继续麻木营业，辞职的念头又闪现");
                break;
            case "周三":
                System.out.println("脑内狂吼，一起同归于尽吧");
                break;
            case "周四":
                System.out.println("再撑一天，明天就是自由的味道.");
                break;
            case "周五":
                System.out.println("下班前钟声响起，灵魂终于归位");
                break;
            default:
                System.out.println("心情贼溜，放飞自我，我最自由");
                break;
        }

        /*
         ⚠️if 与 switch 的比较，各自适合什么样的场景：
            1、if在功能上远远强大于switch。
            2、当前条件是区间的时候，应该使用if分支结构。
            3、当条件是与一个一个的值比较的时候，switch分支更合适：格式良好，性能较好，代码优雅
         */
    }
}
