package com.hmall.gateway.filters;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 自定义 GatewayFilter 不是直接实现 GatewayFilter，而是实现 AbstractGatewayFilterFactory。
 * 请学习 <https://www.bilibili.com/video/BV1S142197x7?spm_id_from=333.788.player.switch&vd_source=f97692c2f656607aeb97ee92b4310d9e&p=63>
 */
public class PrintAnyGatewayFilterFactory extends AbstractGatewayFilterFactory<Object> {
    @Override
    public GatewayFilter apply(Object config) {
        return new OrderedGatewayFilter(new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                System.out.println("--- print any gateway filter");
                return chain.filter(exchange);
            }
        }, 1);
    }
}
