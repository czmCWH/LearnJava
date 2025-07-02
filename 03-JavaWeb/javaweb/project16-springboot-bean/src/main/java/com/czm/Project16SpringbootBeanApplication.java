package com.czm;

import com.company.*;
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
//@ComponentScan({"com.czm", "com.company"})
@SpringBootApplication
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
}
