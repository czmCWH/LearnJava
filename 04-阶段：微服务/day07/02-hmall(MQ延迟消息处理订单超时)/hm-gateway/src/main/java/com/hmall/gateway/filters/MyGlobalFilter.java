package com.hmall.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义 网关 GlobalFilter 过滤器
 */

@Component
public class MyGlobalFilter implements GlobalFilter, Ordered {

    /**
     * filter 方法是过滤器逻辑的核心方法
     *
     * @param exchange 请求上下文。包含整个过滤器链内共享数据，例如：request、response 等。
     * @param chain 过滤器链。当前过滤器执行完后，要调用过滤器链种的下一个过滤器。
     * @return 表示一个回调函数，等转发到服务接收请求处理完返回后，再调用该回调函数。
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // TODO 模拟登录校验逻辑
        // 1、获取请求
        ServerHttpRequest request = exchange.getRequest();
        // 2、处理过滤器业务
        HttpHeaders headers = request.getHeaders();
        System.out.println("---- 网关 headers ----" + headers);
        // 3、放行请求，请求会依次进入过滤器链
        return chain.filter(exchange);
    }

    // 返回过滤器执行优先级，值越小，优先级越高
    @Override
    public int getOrder() {
        /*
         网关在进行路由转发过程中，最后执行的一个过滤器是 NettyRoutingFilter。因此需要保证我们自定义的过滤器在 NettyRoutingFilter 之前执行。
         通过实现 Ordered 接口来指定自定义过滤器的执行顺序，getOrder 返回值越小越先执行。
         NettyRoutingFilter 的优先级为 Int 类型的最大值，此处返回 0 比它小，这样 MyGlobalFilter 会先执行。
         */
        return 0;
    }
}
