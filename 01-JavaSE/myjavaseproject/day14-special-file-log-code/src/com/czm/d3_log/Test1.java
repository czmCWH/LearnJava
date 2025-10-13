package com.czm.d3_log;

public class Test1 {
    /*
      1、日志技术
        可以将系统执行的信息，方便的记录到指定的位置(控制台、文件中、数据库中)可以随时以开关的形式控制日志的启停，
        无需侵入到源代码中去进行修改。

      2、日志技术的体系结构
        a、日志框架：例如：java.util.logging、Log4j、Logback、等其他框架
        b、日志接口：设计日志框架的一套标准，日志框架需要实现这些接口。
            Commons Logging(JCL)
            Simple Logging Facade for Java(SLF4J])

        因为对 Commons Logging 接口不满意，就推出了 SLF4J；
        因为对 Log4j 的性能不满意，有人就搞了 Logback。

        Logback 是基于 SLF4J 的日志规范实现的框架。目前主要是用这个框架

      3、Logback 日志框架 <https://logback.qos.ch/>
        logback 分为三个模块：logback-core，logback-classic 和 logback-access。
            a、logback-core：基础模块，是其他两个模块依赖的基础(必须有)
            b、logback-classic：完整实现了 SLF4J API 的模块(必须有)
            c、logback-access：与 Tomcat 和 Jetty 等 Servlet 容器集成，提供 HTTP 访问日志功能。(可选，以后再接触)

        ⚠️想使用 Logback 日志框架，至少需要在项目中整合如下三个模块：
            SLF4J API 日志接口、logback-core，logback-classic
     */
}
