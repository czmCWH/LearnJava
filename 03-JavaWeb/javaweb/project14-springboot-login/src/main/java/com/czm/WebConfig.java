package com.czm;

import com.czm.interceptor.DemoInterceptor;
import com.czm.interceptor.LoginCheckInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 声明一个配置类，用于注册拦截器
 */
@Slf4j
@Configuration  // @Configuration 也是 @Component 衍生注解，表示声明当前类是一个配置类。（即自动生成 Bean 对象，放到 Spring 容器中管理。）
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private DemoInterceptor demoInterceptor;

    @Autowired
    private LoginCheckInterceptor loginCheckInterceptor;

    /**
     * 注册自定义拦截器，设置拦截路径
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("------ 注册拦截器");
//        registry.addInterceptor(demoInterceptor)
//                .addPathPatterns("/**")     // 设置需要拦截的路径
//                .excludePathPatterns("/login");     // 设置排除拦截的路径

        registry.addInterceptor(loginCheckInterceptor);
//                .addPathPatterns("/**")     // 设置需要拦截的路径，如果不设置则拦截所有
//                .excludePathPatterns("/login");     // 设置排除拦截的路径
    }
}
