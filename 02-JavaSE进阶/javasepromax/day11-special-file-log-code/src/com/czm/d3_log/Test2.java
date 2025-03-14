package com.czm.d3_log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test2 {
    /*
     1、Logback 日志框架的实现步骤
        a、导入 Logback 日志框架到项目中 <https://logback.qos.ch/>
            slf4j-api 接口、logback-core，logback-classic
        b、将 Logback 框架的核心配置文件 logback.xml 直接拷贝至 src 目录(必须是 src 下)
        c、创建 Logback 框架提供的 Logger 对象，然后用 Logger 对象调用其提供的方法就可以记录系统的日志信息

     2、核心配置文件 logback.xml
        用于对 Logback 日志框架进行控制。

     3、Logback 的日志级别
        日志级别指的是日志信息的类型，日志都会分级别，常见的日志级别如下(优先级依次升高）：
            OGGER.trace	追踪，指明程序运行轨迹
            OGGER.debug	调试，实际应用中一般将其作为最低级别，而 trace 则很少使用
            OGGER.info	输出重要的运行信息，数据连接、网络连接、10操作等等，使用较多
            OGGER.warn	警告信息，可能会发生问题，使用较多
            OGGER.error	错误信息，使用较多
        只有日志的级别是大于或等于核心配置文件配置的日志级别，才会被记录，否则不记录、即：logback.xml 文件中的  <root level="ALL"><root>
     */

    // 1、创建一个 Logback 框架的 Logger 日志对象，来记录日志。
    public static final Logger LOGGER = LoggerFactory.getLogger("Test2.class");

    public static void main(String[] args) {

//        while (true) {    // 通过死循环，让日志文件拆分
            try {
                // LOGGER.info 用于记录关键日志
                LOGGER.info("--- 开始做除法 ---");
                divide(10, 0);
                LOGGER.info("--- 做除法结束了 ---");
            } catch (Exception e) {
                LOGGER.error("除法执行失败了" + e.getMessage(), e);
            }
//        }

    }

    public static void divide(int a, int b) {
        // LOGGER.debug 记录调试日志
        LOGGER.debug("参数a:{}", a);
        LOGGER.debug("参数b:{}", b);
        int c = a / b;
        LOGGER.info("结果c是:" + c);
    }
}
