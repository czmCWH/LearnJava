package com.czm.interceptor;

import com.czm.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 登录校验拦截器
 */
@Slf4j
@Component
public class LoginCheckInterceptor implements HandlerInterceptor {

    /**
     * 目标资源方法 (即，Controller 中的方法) 执行前 执行。
     *    true：放行；false：拦截请求不放行；
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1、获取请求 url
        StringBuffer url = request.getRequestURL();
        log.info("--- LoginCheckInterceptor url 全路径 = {} ", url);
        String uri = request.getRequestURI();
        log.info("--- LoginCheckInterceptor uri 资源路径  = {} ", uri);

        // 2、判断请求URL中是否包含 /login，如果包含，则放行。
        if (uri.contains("/login")) {
            // 放行
            log.info("--- 登录请求，放行！");
            return true;
        }

        // 3、获取请求头中的令牌（token）
        String token = request.getHeader("token");
        log.info("--- LoginCheckInterceptor doFilter token = {} ", token);

        // 4、判断令牌是否存在，如果不存在，说明用户未登录，返回错误信息，响应 401状态码
        if (token == null) {
            // 直接设置状态码 401，返回
            log.info("--- LoginCheckInterceptor 401 令牌不存在！");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            // 不放行
            return false;
        }

        // 5、解析token，如果解析失败，响应 401
        try {
            Claims  clamis = JwtUtils.parseJwt(token);
            log.info("--- LoginCheckInterceptor doFilter 令牌解析 = {}", clamis);
        } catch (Exception e) {

            log.error("--- LoginCheckInterceptor doFilter 令牌解析失败 = {} ", e.getMessage());
            // 直接设置状态码 401
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            // 不放行
            return false;
        }
        // 6、放行
        // 放行
        return true;
    }
}
