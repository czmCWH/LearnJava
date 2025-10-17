package com.czm;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// @slf4j 注解，如果导入了 lombok 依赖则可以使用此注解，会自动生成: private final Logger log = LoggerFactory.getLogger(LogTest.class);
public class LogTest {
    // Logger：日志记录对象
    // getLogger(name = 日志记录器的名字)、 getLogger(Class = 记录那个类中的日志)
    private final Logger log = LoggerFactory.getLogger(LogTest.class);

    // 直接点击右侧允许标记，即可运行测试
    @Test
    public void testLog() {
//        System.out.println("开始计算...");
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
        log.info("计算结果 sum = " + sum);
        // 占位符的方式输出
        log.info("计算结果 sum = {}", + sum);   // 占位符的方式
        log.info("结束计算。");
    }
}
