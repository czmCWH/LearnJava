# 一、自定义网关过滤器 GlobalFilter - 实现登录校验功能
`hmall` 项目是基于 `JWT` 实现的登录校验，目前相关功能在 `hm-service` 模块。我们可以将其中的 JWT 工具拷贝到 `gateway 模块`，然后基于 `GlobalFilter` 自定义 `AuthGlobalFilter` 来实现登录校验。
```java
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
        // 1、获取请求 request
        ServerHttpRequest request = exchange.getRequest();

        // 2、判断路径是否需要登录校验
        if (isExclude(request.getPath().toString())) {
            // 路径被排除 - 直接放行
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
            // ⚠️⚠️⚠️ 用户无法通过异常知道是 token 校验失败，因此拦截异常，设置响应状态码 401，表示用户未登录，并终止请求
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();  // 设置请求完结终止
        }

        // 5、传递用户信息，即：把用户信息保存到请求头里，转发请求到微服务，
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
```

> 代码实现：`/day03/05-hmall（网关登记校验+用户信息传递）/hm-gateway/.../AuthProperties.java`