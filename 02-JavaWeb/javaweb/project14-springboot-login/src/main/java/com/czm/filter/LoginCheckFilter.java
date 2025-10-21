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
 * 自定义Filter，用于统一登录校验
 * 1、所有的请求，拦截到了之后，都需要校验令牌吗?
 *  不是，例如：注册请求、登录请求等；
 * 2、拦截到请求后，什么情况下才可以放行，执行业务操作?
 *  有令牌，且令牌校验通过(合法)；
 *  否则都返回未登录错误结果；
 *  ⚠️，如果不使用 @WebFilter 注解则自定义的 Filter 不生效
 */
@Slf4j
@WebFilter("/*")    // @WebFilter 注解用于配置拦截路径, /* 表示拦截所有请求
public class LoginCheckFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("------ 登录校验拦截器 LoginCheckFilter doFilter ");
        // 强转 ServletRequest
        HttpServletRequest request = (HttpServletRequest) servletRequest;   // 用于获取请求数据
        HttpServletResponse response = (HttpServletResponse) servletResponse;   // 用于设置响应数据

        // 1、获取请求 url
        StringBuffer url = request.getRequestURL();
        log.info("--- url 全路径 = {} ", url);
        String uri = request.getRequestURI();
        log.info("--- uri 资源路径  = {} ", uri);

        // 2、判断是否是登录请求，如果请求路径中包含 /login，说明是登录操作，则放行。
        if (uri.contains("/login")) {
            // 放行！
            filterChain.doFilter(servletRequest, servletResponse);
            // 结束当前请求，不要往下执行。因为后续的为 - 放行后操作，依然会执行。
            return;
        }

        // 3、获取请求头中的令牌（token）
        String token = request.getHeader("token");
        log.info("--- token = {} ", token);

        // 4、判断令牌是否存在，如果不存在，则未登录，响应 401 状态码
        if (token == null || token.isEmpty()) {
            log.error("--- doFilter  令牌为空，响应401！！！");
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
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 5、解析token，如果解析失败，响应 401
        try {
            Claims  clamis = JwtUtils.parseJwt(token);
            log.info("--- doFilter 令牌解析成功 = {}", clamis);
        } catch (Exception e) {
            log.info("--- doFilter 令牌非法，响应401 ");
//            throw new BusinessException("校验失败，请重新登录！");
            // 直接设置状态码 401，返回
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }

        // 6、校验通过，放行
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
