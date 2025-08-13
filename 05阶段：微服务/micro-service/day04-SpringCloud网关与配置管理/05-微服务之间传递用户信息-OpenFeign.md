# 一、微服务之间通过 OpenFeign 传递用户信息
微服务项目中的业务有些需要多个微服务共同合作完成，微服务之间的请求是通过 `OpenFeign` 客户端发送的，并没有做请求头处理，所以请求头中不会有用户信息。
因此需要实现 OpenFeign 客户端远程调用时也携带用户信息，这就需要 OpenFeign 拦截器来实现。

`OpenFeign` 中提供了一个拦截器接口 `RequestInterceptor`，所有由 `0penFeign` 发起的请求都会先调用拦截器处理请求;
其中的 `RequestTemplate` 类中提供了一些方法可以让我们修改请求头；

我们把 `OpenFeign` 发送远程请求客户端封装在 `hm-api`模块，因此可以把拦截器请求处理操作放在此模块，这样其它模块引入 `hm-api 模块` 就自带了拦截器，代码基本实现如下：

```java
public class DefaultFeignConfig {
    /**
     * 自定义 OpenFeign 的日志级别
     */
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    /**
     * 通过定义匿名内部类的方式，添加 OpenFeign 拦截器
     * 用于在 OpenFeign 发送请求时，请求头中添加用户信息
     */
    @Bean
    public RequestInterceptor userInfoRequestInterceptor() {
        return new RequestInterceptor() {
            @Override
            public void apply(RequestTemplate requestTemplate) {
                // 此处 UserContext 可以拿到 用户信息
                // 因为进入微服务的请求是经过网关过滤器添加了用户信息到 UserContext，在微服务处理业务逻辑时发送 Feign 请求，首先进入 Feign 的拦截器，所以可以从 UserContext 中获取用户信息
                Long userId = UserContext.getUser();
                if (userId != null) {
                    requestTemplate.header("user-info", userId.toString());
                }
            } 
        };
    }
}
```

## 注意
要想使 OpenFeign 的配置类生效，需要在启用 OpenFeign 的模块中启动类上添加配置，如下代码所示：
```java
@EnableFeignClients(basePackages = "com.hmall.api.clients", defaultConfiguration = DefaultFeignConfig.class)
@SpringBootApplication
public class CartApplication {
   // ...
}
```