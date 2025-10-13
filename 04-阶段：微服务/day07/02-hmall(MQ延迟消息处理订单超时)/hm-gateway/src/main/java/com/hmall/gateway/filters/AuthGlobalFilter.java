package com.hmall.gateway.filters;

import com.hmall.gateway.config.AuthProperties;
import com.hmall.gateway.utils.JwtTool;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * ⚠️ 登录授权校验 网关过滤器
 */

@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {

    private final AuthProperties authProperties;

    private final JwtTool jwtTool;

    // Spring 框架提供的路径匹配工具类
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 处理登录校验逻辑
        // 1、获取请求
        ServerHttpRequest request = exchange.getRequest();

        // 2、判断是否需要登录校验
        if (isExclude(request.getPath().toString())) {
            return chain.filter(exchange);
        }

        // 3、获取token
        String token = null;
        List<String> headers = request.getHeaders().get("authorization");
        if (headers != null && !headers.isEmpty()) {
            token = headers.get(0);
        }
        // 4、校验并解析 token
        Long userId = null;
        try {
            userId = jwtTool.parseToken(token);
        } catch (Exception e) {
            // ⚠️⚠️⚠️拦截，设置响应状态码 401，表示用户未登录
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();  // 设置请求完结终止
        }

        // 5、传递用户信息，即：把用户信息保存到请求头里，然后传递到下一个微服务，
        System.out.println("---- userId : " + userId);
        String userInfo = userId.toString();
        ServerWebExchange swe = exchange.mutate()
                .request(builder -> builder.header("user-info", userInfo))
                .build();

        // 6、放行请求，请求会依次进入过滤器链
        return chain.filter(swe);
    }

    private boolean isExclude(String path) {
        // authProperties.getExcludePaths()     // 从 application.yaml 中获取路径信息
        for (String pathPattern: authProperties.getExcludePaths()) {
            // 请求路径匹配判断
            if (antPathMatcher.match(pathPattern, path)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int getOrder() {
        return 0;
    }


}
