package com.czm;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
    private final Logger log = LoggerFactory.getLogger(LogTest.class);

    @Test
    public void testLog() {
        log.info("开始计算...");
        int sum = 0;
        try {
            int[] nums = {1,2,3,4,5,6,7,8,9};
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
            }
        } catch (Exception e) {
            log.info("程序出错！");
        }
        log.info("计算结果为：" + sum);
        log.info("结束计算。");
    }
}
