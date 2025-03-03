package com.czm.branch;

public class ForForDemo10 {
    public static void main(String[] args) {
        /*
         1、循环嵌套
         循环嵌套的特点：
            外部循环每循环一次，内部循环会全部执行完一轮，
         */
        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 4; j++) {
                System.out.println("我爱你" + j);
            }
            System.out.println("------------");
        }

        // 打印4行5列星星
        for (int j = 1; j <= 4; j++) {
            for (int k = 1; k <= 5; k++) {
                System.out.print("🌟");
            }
            // 换行
            System.out.println();
        }

    }
}
