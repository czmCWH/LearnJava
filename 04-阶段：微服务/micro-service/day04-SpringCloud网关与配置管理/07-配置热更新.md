# 一、Nacos 配置热更新

`配置热更新`：当修改 Nacos 配置文件中的配置时，微服务无需重启即可使配置生效。

要想使用 Nacos 实现配置热更新，需要一些前提条件：

## 条件1
`Nacos` 控制台配置列表中要有一个与微服务名有关的配置文件，命名规则如下：
```
[spring.application.name]-[spring.active.profile].[file-extension]
[微服务的名称]      [项目profile，环境变量(local、test、dev)，可选]    [文件后缀名，yaml]
```

Nacos 热更新配置文件命名规则来源于 微服务中配置的 `bootstrap.yaml`文件，当微服务启动时，另外还会自动加载 `bootstrap.yaml` 文件中相关属性来组成文件。

## 条件2
微服务中要以特定方式读取需要热更新的配置属性。

```java
// 方式1
@Data
@ConfigurationProperties(prefix = "hm.cart")
public class CartProperties {
    private int maxItems;
}

// 方式2，不推荐
@Data
@Refreshscope
public class CartProperties {
    @Value("${hm.cart.maxItems}")
    private int maxItems;
}
```

## 案例-实现购物车添加商品上限的配置热更新
需求：购物车模块中的限定商品数量目前是写死在业务中的，将其改为读取配置文件属性，并将配置交给 Nacos 管理，实现热更新。

* 步骤1，在`Nacos` 服务控制台配置列表中添加 `Data ID:cart-service.yaml` 配置，配置内容如下所示：

实现如图：`/img/04-配置管理-Nacos/02-热更新配置示例.jpg`
```cart-service.yaml
hm:
  cart:
    maxItems: 3
```

* 步骤2，在 `cart-service` 购物车模块中，购物车中商品的最大数量目前是写死的，需将其改为读取配置文件属性，并将配置交给 Nacos 管理来实现热更新。
```java
@Data
@Component
@ConfigurationProperties(prefix = "hm.cart")
public class CartProperties {
    private Integer maxItems;
}
```

* 步骤3，在 `cart-service` 模块中测试
```java
@Service
@RequiredArgsConstructor
public class CartServiceImpl extends ServiceImpl<CartMapper, Cart> implements ICartService {
    private final CartProperties cartProperties;

    private void checkCartsFull(Long userId) {
        Long count = lambdaQuery().eq(Cart::getUserId, userId).count();
//        if (count >= 10) {
        if (count >= cartProperties.getMaxItems()) {
            throw new BizIllegalException(StrUtil.format("用户购物车商品不能超过{}", cartProperties.getMaxItems()));
        }
    }
}
``` 

# 二、Nacos 配置管理的应用案例-动态路由
网关服务的路由是写死在 网关模块的配置文件中，即 `application.yaml` 文件中，当网关服务启动时加载该配置文件中的路由信息到内存中一个路由表。
当在项目运行过程中需要改网关的路由信息，那么就必须重启网关服务，这样会造成整个项目无法访问。需使用 Nacos 实现网关 动态路由 来解决此问题。

要实现 动态路由 首先要将 路由配置 保存到 Nacos 管理，当 Nacos 中的路由配置变更时，推送最新配置到网关，实时更新网关中的路由信息。
我们需要完成两件事情:
1. 监听 Nacos 配置变更的消息；
2. 当配置变更时，将最新的路由信息更新到网关路由表；

Nacos 官方文档介绍：<https://nacos.io/docs/latest/manual/user/java-sdk/usage/?spm=5238cd80.1f77ca18.0.0.4d31e37eyLQnOC#31-%E8%8E%B7%E5%8F%96%E9%85%8D%E7%BD%AE>

## 步骤1，在 hm-gateway 网关模块添加依赖
```pom
<!-- nacos 作为配置中心 -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
<!-- 读取 bootstrap 文件 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bootstrap</artifactId>
</dependency>
```

## 步骤2，在 hm-gateway 网关模块 添加 bootstrap.yaml 配置文件，配置连接 Nacos 服务信息
```bootstrap.yaml
spring:
  application:
    name: gateway  # 微服务名称，每个服务应保证不一样
  profiles:
    active: dev
  cloud:
    nacos: # 配置 nacos 注册中心地址
      server-addr: localhost:8848
      config:
        file-extension: yaml
        shared-configs:
          - data-id: shared-log.yaml
``` 

## 步骤3，在 Nacos 服务控制台，新增 `gateway-routes.json` 配置文件，配置内容如下：
```json
[
	{
		"id": "user-service",
		"uri": "lb://user-service",
		"predicates": [{
			"name": "Path",
			"args": {
				"_genkey_0": "/users/**",
				"_genkey_1": "/addresses/**"
			}
		}],
		"filters": []
	},
	{
		"id": "item-service",
		"uri": "lb://item-service",
		"predicates": [{
			"name": "Path",
			"args": {
				"_genkey_0": "/items/**",
				"_genkey_1": "/search/**"
			}
		}],
		"filters": []
	},
	{
		"id": "cart-service",
		"uri": "lb://cart-service",
		"predicates": [{
			"name": "Path",
			"args": {
				"_genkey_0": "/carts/**"
			}
		}],
		"filters": []
	},
	{
		"id": "trade-service",
		"uri": "lb://trade-service",
		"predicates": [{
			"name": "Path",
			"args": {
				"_genkey_0": "/orders/**"
			}
		}],
		"filters": []
	},
	{
		"id": "pay-service",
		"uri": "lb://pay-service",
		"predicates": [{
			"name": "Path",
			"args": {
				"_genkey_0": "/pay-orders/**"
			}
		}],
		"filters": []
	}
]
```

## 步骤4，在 hm-gateway 网关模块中清理 application.yaml 文件中 Nacos 配置信息 与 路由表信息

## 步骤5，在 hm-gateway 网关模块中实现 `DynamicRouteLoader.java` 动态路由加载器，编写监听 Nacos 动态路由、更新路由表功能
```java
@Slf4j
@Component
@RequiredArgsConstructor    // 用于实现构造方法，来进行 Spring 依赖注入
public class DynamicRouteLoader {

    // NacosConfigManager 用于获取 Nacos Config Service
    private final NacosConfigManager nacosConfigManager;
    // 用于更新 网关路由表
    private final RouteDefinitionWriter writer;

    // Nacos 控制台添加的动态路由配置文件信息
    private final String dataId = "gateway-routes.json";
    private final String group = "DEFAULT_GROUP";

    private final Set<String> routeIds = new HashSet<>();

    @PostConstruct  // ⚠️⚠️⚠️ @PostConstruct 注解表示当前 DynamicRouteLoader 类型的 Bean 初始化之后立即执行。
    public void initRouteConfigListener() throws NacosException {
        // 1、项目启动时，getConfigAndSignListener：先拉取一次配置，并且添加配置监听器
        // configInfo 为拉取的动态路由配置信息
        String configInfo = nacosConfigManager.getConfigService().getConfigAndSignListener(dataId, group, 5000, new Listener() {

            // 返回一个线程池，用于执行监听器的方法
            @Override
            public Executor getExecutor() {
                return null;
            }

            @Override
            public void receiveConfigInfo(String configInfo) {
                // 3、监听到配置变更，需要去更新路由表
                updateConfigInfo(configInfo);
            }
        });

        // 2、第一次读取到配置，也需要更新路由表
        updateConfigInfo(configInfo);
    }

    // 更新网关服务路由表信息
    public void updateConfigInfo(String configInfo) {
        log.debug("--- 监听到路由配置信息：{}", configInfo);
        // 1、解析配置信息，转为 RouteDefinition 路由对象
        List<RouteDefinition> routeDefinitions = JSONUtil.toList(configInfo, RouteDefinition.class);

        // 2、先删除旧的路由表
        for (String routeId : routeIds) {
            // 根据路由ID删除某个路由
            writer.delete(Mono.just(routeId)).subscribe();
        }
        routeIds.clear();

        // 3、更新路由表
        for (RouteDefinition routeDefinition : routeDefinitions) {
            // 3.1、更新路由信息到路由表，如果路由ID重复则覆盖
            writer.save(Mono.just(routeDefinition)).subscribe();
            // 3.2、记录路由ID，便于下一次更新时删除
            routeIds.add(routeDefinition.getId());
        }
    }
}
```

## 步骤6，重启 Gateway 网关服务，测试接口是否可以访问。

