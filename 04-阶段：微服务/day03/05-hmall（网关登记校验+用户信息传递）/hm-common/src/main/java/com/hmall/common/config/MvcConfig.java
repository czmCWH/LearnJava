package com.hmall.common.config;

import com.hmall.common.interceptors.UserInfoInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 1、要想自定义 SpringMVC 的拦截器生效，还需要添加实现 WebMvcConfigurer 接口的配置类；
 * 2、@Configuration 注解的配置类，要想生效，还需要被 Spring 应用所在的包扫描到；
 * 不同包下的配置类，如果想要被 Spring 扫描到，则需要在该包的 src/main/resources/META-INF/ 目录下的文件中记录这些配置类，
 * 早期这个文件名字叫 spring.factories，

 * 3、由于 用户登录信息获取的拦截定义在 common 模块中，该模块被所有模块继承。但是 Gateway 网关模块不属于 SpringMVC，而 common 模块
 * 中使用了 SpringMVC 的 API，这会导致 Gateway 网关模块启动时扫描到该类后报错。
 * 可通过添加 @ConditionalOnClass(DispatcherServlet.class) SpringMVC 自动装配条件注解，如果模块中不包含 SpringMVC 的核心类 DispatcherServlet
 * 则不添加此配置。
 *
 */

@Configuration
@ConditionalOnClass(DispatcherServlet.class)
public class MvcConfig implements WebMvcConfigurer {

    // 添加自定义拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInfoInterceptor());
    }
}
