package com.czm.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;    // ⚠️ Filter 是 servlet 框架中的
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 自定义 Filter 过滤器入门
 */
@Slf4j
@WebFilter(urlPatterns = "/app/*")  // @WebFilter 注解，用于指定该过滤器拦截哪些请求接口
public class DemoFilter implements Filter {

    /**
     * 初始化方法，在 web 服务器启动的时候，创建 Filter 实例后自动调用，只调用一次。 --- 此方法不常用
     * 一般用于资源环境的准备工作。
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);    // 此方法有默认实现
        log.info("---  过滤器 1 DemoFilter init");
    }

    /**
     * 每次拦截到了请求时，会触发此方法，会被多次调用
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("---  过滤器 2 DemoFilter doFilter");
        log.info("--- doFilter 放行前 逻辑");
        // 1、请求合法 - 放行，让请求访问对应的资源
        // 如果过滤器中不执行放行操作，过滤器拦截到请求之后，就不会访问对应的资源，因此接口响应数据为空。
        filterChain.doFilter(servletRequest, servletResponse);
        log.info("--- doFilter 放行后 逻辑");
    }

    /**
     * 销毁方法，在 web 服务器正常关闭的时候会触发一次  --- 此方法不常用
     * 用于资源释放、环境清理
     */
    @Override
    public void destroy() {
        Filter.super.destroy();
        log.info("---  过滤器 3 DemoFilter destroy");
    }
}
