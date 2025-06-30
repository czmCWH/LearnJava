package com.czm.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 实现 HandlerInterceptor 接口定义 Spring 拦截器类
 * 重写拦截器的方法。
 */

@Slf4j
@Component
public class DemoInterceptor implements HandlerInterceptor {
    /**
     * 目标资源方法执行前 执行。true：放行；false：拦截请求不放行；
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("------ DemoInterceptor preHandle");
        return true;
    }

    /**
     * 目标资源方法执行后 执行，
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("------ DemoInterceptor postHandle");
    }

    /**
     * 实图渲染完毕执行，最后执行。（很少使用！！！）
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        log.info("------ DemoInterceptor afterCompletion");
    }
}
