package com.czm.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * Interceptor 拦截器
 *  - 实现 HandlerInterceptor 接口定义 Spring 拦截器类
 */
@Slf4j
@Component  // 由于 Interceptor 是 Spring 提供的，所以需要使用 @Component 注解交给 IOC 管理
public class DemoInterceptor implements HandlerInterceptor {
    /**
     * 目标资源方法 (即，Controller 中的方法) 执行前 执行。
     *      true：放行；false：拦截请求不放行；
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("------ DemoInterceptor preHandle");
        return true;
    }

    /**
     * 目标资源方法 (即，Controller 中的方法) 执行后 执行，
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("------ DemoInterceptor postHandle");
    }

    /**
     * 视图渲染完毕执行，最后执行。--- 不常用
     *  是指早期前后端未分离开发时，后端需要渲染前端的视图。
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        log.info("------ DemoInterceptor afterCompletion");
    }
}
