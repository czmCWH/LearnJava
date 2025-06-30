package com.czm.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;    // ⚠️ Filter 是 servlet 框架中的
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * Filter 过滤器实现类
 */

@Slf4j
@WebFilter(urlPatterns = "/app/*")
public class DemoFilter implements Filter {

    /**
     * 初始化放啊，在 web 服务器启动的时候会触发一次
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);    // 此方法有默认实现
        log.info("---  过滤器 1 DemoFilter init");
    }

    /**
     * 每次拦截到了请求，会触发此方法，会被多次调用
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("---  过滤器 2 DemoFilter doFilter");
        log.info("--- doFilter 放行前 逻辑");
        // 1、放行
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("--- doFilter 放行后 逻辑");
    }

    /**
     * 销毁方法，在 web 服务器正常关闭的时候会触发一次
     */
    @Override
    public void destroy() {
        Filter.super.destroy();
        log.info("---  过滤器 3 DemoFilter destroy");
    }
}
