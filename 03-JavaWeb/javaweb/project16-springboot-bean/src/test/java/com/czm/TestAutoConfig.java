package com.czm;
import com.company.HeaderGenerator;
import com.company.HeaderParser;
import com.company.TokenParser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

/**
 * 测试类：用于测试 Spring boot 的自动配置！
 */

@SpringBootTest
public class TestAutoConfig {
    @Autowired
    private ApplicationContext appContext;

    @Test
    public void testGetTokenParser() {
        TokenParser tokenParser = appContext.getBean(TokenParser.class);
        System.out.println("--- tokenParser ---" + tokenParser);

        /*
         1、测试获取第三方依赖中使用 @Component 声明的 TokenParser 的 Bean 对象失败！
         org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.company.TokenParser' available

         要想能获取到 Bean 对象，必须 IOC 容器内有此对象，这需要启动类能扫描到此类。
         定义此类添加 @Component 注解，并且此类在启动类所在的包或者子包内，才能被启动类扫描到。

         原因：@Component 声明的 TokenParser 和 Project16SpringbootBeanApplication启动类 不在同一个包下。
         如何解决第三方 Bean 扫描不到？这就需要用到 Spring 的自动配置，有如下几种方案：

         方案一：使用 @ComponentScan 注解指定扫描的包
         @ComponentScan({"com.czm", "com.company"})
         使用了  @ComponentScan 后，会打破启动类默认的扫描路径（即启动类所在的包或者子包），因此使用 @ComponentScan 时也要添加启动类所在的包。

         项目中通常需要用到许多依赖，如果用此方案，则使用繁琐、性能低。

         方案二：@Import导入。使用 @Import 导入的类会被 Spring 加载到 IOC 容器中，导入形式主要有以下几种：
         1、导入普通类：
            @Import({HeaderParser.class, HeaderGenerator.class, TokenParser.class})

         2、导入配置类：
            @Import(HeaderConfig.class)
            或者多个配置类：@Import({HeaderConfig.class, ...})

         3、导入 ImportSelector 接口实现类

         4、使用自定义 @EnableXxx 注解来封装 @Import 注解（推荐）

         */
    }

    @Test
    public void testGetHeaderParser() {
        HeaderParser headerParser = appContext.getBean(HeaderParser.class);
        System.out.println("--- headerParser ---" + headerParser);

        /*

         org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.company.HeaderParser' available


         */
    }

    @Test
    public void testGetHeaderGenerator() {
        HeaderGenerator headerGenerator = appContext.getBean(HeaderGenerator.class);
        System.out.println("--- headerGenerator ---" + headerGenerator);
    }



}
