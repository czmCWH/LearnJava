package com.czm.filter;

import com.czm.exception.BusinessException;
import com.czm.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

/**
 * 登录校验 Filter
 */

@Slf4j
@WebFilter("/*")    // 配置拦截路径, /* 表示拦截所有请求
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("------ 登录校验拦截器 LoginCheckFilter doFilter ");
        // 强转 ServletRequest
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // 1、获取请求 url
        StringBuffer url = request.getRequestURL();
        log.info("--- url 全路径 = {} ", url);
        String uri = request.getRequestURI();
        log.info("--- uri 资源路径  = {} ", uri);

        // 2、判断请求URL中是否包含 /login，如果包含，则放行。
        if (uri.contains("login")) {
            // 放行
            filterChain.doFilter(servletRequest, servletResponse);
            // ⚠️⚠️⚠️ 结束当前请求，不要往下执行。因为后续的为 - 放行后操作，依然会执行。
            return;
        }

        // 3、获取请求头中的令牌（token）
        String token = request.getHeader("token");
        log.info("--- LoginCheckFilter doFilter token = {} ", token);

        // 4、判断令牌是否存在，如果不存在，响应 401
        if (token == null) {
            log.error("--- LoginCheckFilter doFilter  令牌为空！！！");
//            throw new BusinessException("未登录，请登录！");  // 如果直接抛出异常，前端接收报错如下：
            /*
            {
                "timestamp": "2025-06-30T06:30:23.297+00:00",
                "status": 500,
                "error": "Internal Server Error",
                "path": "/depts"
            }
             */
            // 直接设置状态码 401，返回
            response.setStatus(401);
            return;
        }

        // 5、解析token，如果解析失败，响应 401
        try {
            Claims  clamis = JwtUtils.parseJwt(token);
            log.info("--- LoginCheckFilter doFilter 令牌解析 = {}", clamis);
        } catch (Exception e) {

            log.error("--- LoginCheckFilter doFilter 令牌解析失败 = {} ", e.getMessage());
//            throw new BusinessException("校验失败，请重新登录！");
            // 直接设置状态码 401，返回
            response.setStatus(401);
            return;
        }

        // 6、放行
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
