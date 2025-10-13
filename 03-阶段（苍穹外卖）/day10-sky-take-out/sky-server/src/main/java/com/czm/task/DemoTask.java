package com.czm.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * 自定义定时任务类
 */

@Slf4j
//@Component
public class DemoTask {

    /**
     * 每隔5秒打印一次 日志打印
     */
//    @Scheduled(cron = "0/5 * * * * ?")    // 先注释，避免一直运行
    public void printLog() {
        log.info("---【执行定时任务 {}】", LocalDate.now());
    }
}
