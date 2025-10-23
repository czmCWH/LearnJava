package com.czm;

import com.company.*;
import com.czm.utils.*;
import org.dom4j.io.SAXReader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

@EnableHeaderConfig     // 用于封装 @Import 注解
//@Import(MyImportSelector.class)     // 导入接口实现类
//@Import(HeaderConfig.class)       // 导入配置类
//@Import({HeaderParser.class, HeaderGenerator.class, TokenParser.class})
//@ComponentScan(basePackages = {"com.czm", "com.company"})     // 一旦指定了扫描的包，默认的扫描包将不生效，因此也需要添加默认的 com.czm 包。
@SpringBootApplication  // 具备组件的扫描功能，默认扫描范围是启动类所在包及其子包。
public class Project16SpringbootBeanApplication {

    public static void main(String[] args) {
        SpringApplication.run(Project16SpringbootBeanApplication.class, args);
    }

    /**
     * @Bean 注解只能作用于方法上。程序启动时会自动执行此方法，并把方法返回值交给 IOC 容器管理，成为 IOC 容器的 bean 对象。
     */
//    @Bean
//    public SAXReader getSAXReader() {
//        return new SAXReader();
//    }

//    @Bean
//    public HeaderParser getHeaderParser() {
//        return new HeaderParser();
//    }

    @Bean
    public AliyunOSSOperator aliyunosSOperator(AliyunOSSProperties ossProperties) {  // ⚠️ AliyunOSSProperties 必须是一个 Bean，此处会自动完成依赖注入
        return new AliyunOSSOperator(ossProperties);
    }
}

