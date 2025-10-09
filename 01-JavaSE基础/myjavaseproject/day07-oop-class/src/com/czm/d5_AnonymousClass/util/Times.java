package com.czm.d5_AnonymousClass.util;

/**
 * 工具类 - 统计代码执行的时间
 */
public class Times {

    public interface Block {
        void execute();
    }

    public static void test(Block block) {
        long start = System.currentTimeMillis();
        block.execute();
        long end = System.currentTimeMillis();
        double time = (end - start) / 1000.0;
        System.out.println("耗时：" + time + "s");

    }
}
