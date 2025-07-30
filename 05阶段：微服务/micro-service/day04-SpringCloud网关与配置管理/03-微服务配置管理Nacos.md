# 微服务配置管理
在微服务项目中，可能存在很多个微服务，并且每个微服务可能存在很多的配置文件，比如：MySql 数据库的配置、日志的配置、Swagger接口文档的配置。
这些配置文件很多都是重复的配置，如果针对每个微服务进行修改时就非常的麻烦。

微服务配置问题：
1. 微服务重复配置过多，维护成本高；
2. 业务配置（用户登录超时时间、订单超时时长、购物车商品上限等）经常变动，每次修改都要重启服务；
3. 网关路由配置写死，如果变更要重启网关（网关是整个微服务的入口，如果重启其它微服务将都不可用）；

为了解决这个问题，可以使用 `配置管理服务`，把微服务中重复的、通用的配置、业务有关的配置 都交给它管理，
其它微服务启动时从 `配置管理服务` 读取配置信息即可，实现配置共享。
`配置管理服务` 可以监听这些配置的变更，如果发生变更它会推送这些配置到对应的微服务，这些微服务不需要重启，就可以立即生效。

> `Nacos` 不仅具备 注册中心的功能，还具备 `配置管理服务` 功能。

## 1、Nacos 配置文件共享

### 步骤一、在 Nacos 服务器上添加 配置
添加一些共享配置到 Nacos 中，包括：`jdbc`、`MybatisPlus`、`日志`、`Swagger`、`OpenFeign` 等配置。
直接在 Nacos 控制台 <http://localhost:8848/nacos/> 添加即可。

### 步骤二、拉取 Nacos 配置管理 中的共享配置

在微服务模块中，基于 `NacosConfig` 拉取 Nacos 服务器中共享配置 代替 微服务的本地配置，
依次新建 `shared-jdbc.yaml`、`shared-log.yaml`、`shared-swagger.yaml` 配置。

具体配置见：`/img/04-Nacos配置管理服务`

#### 步骤1、引入依赖 
```pom
<!-- nacos 服务注册发现 -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
<!-- nacos 配置管理服务 -->
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

#### 步骤2、在微服务模块中，新建 `/resources/bootstrap.yaml` 文件

```yaml
spring:
  application:
    name: cart-service  # 微服务名称，每个服务应保证不一样
  profiles:
    active: dev
  cloud:
    nacos: # 配置 nacos 注册中心地址
      server-addr: localhost:8848
      config:
        file-extension: yaml  # 文件后缀名
        shared-configs: # 共享配置
          - data-id: shared-jdbc.yaml   # 共享 mybatis 配置
          - data-id: shared-log.yaml    # 共享日志配置
          - data-id: shared-swagger.yaml  # 共享接口文档配置
```

上面 `bootstrap.yaml` 中配置的 `data-id` 对应的 Nacos 服务器配置列表中的配置文件，在微服务启动时会自动加载合并到 `application.yaml` 中，
因此我们需要对 `application.yaml` 中的文件进行修改，剔除与 Nacos 服务器配置列表中的配置文件 重复的内容，添加 Nacos 服务器配置列表中的配置文件 所需的配置变量。

> 代码实现：`cart-service/src/main/resources`


## 2、Nacos 配置热更新
配置热更新：当修改 Nacos 配置文件中的配置时，微服务无需重启即可使配置生效。
前提条件：
#### 条件1、`Nacos` 配置列表中要有一个与微服务名有关的配置文件，命名规则如下：
```
[spring.application.name]-[spring.active.profile].[file-extension]
[微服务的名称]      [项目profile，环境变量(local、test、dev)，可选]    [文件后缀名，yaml]
```

```cart-service.yaml
hm:
  cart:
    maxItems: 3
```

Nacos 热更新配置文件命名规则来源于 微服务中配置的 `bootstrap.yaml`文件，当微服务启动时，另外还会自动加载 `bootstrap.yaml` 文件中相关属性来组成文件。

#### 条件2、微服务中要以特定方式读取需要热更新的配置属性

案例实现：在 `cart-service` 购物车模块中，购物车中商品的最大数量目前是写死的，需将其改为读取配置文件属性，并将配置交给 Nacos 管理来实现热更新。

```java
@Data
@Component
@ConfigurationProperties(prefix = "hm.cart")
public class CartProperties {
    private Integer maxItems;
}
```

## 3、动态路由
网关服务的路由是写死在 网关模块的配置文件中，即 `application.yaml` 文件中，当网关服务启动时加载该配置文件中的路由信息到内存中一个路由表。
当在项目运行过程中需要改网关的路由信息，那么就必须重启网关服务，这样会造成整个项目无法访问。
这就需要在 网关中使用 Nacos 动态配置来实现 动态路由 来解决此问题。

要实现动态路由首先要将路由配置保存到 Nacos，当 Nacos 中的路由配置变更时，推送最新配置到网关，实时更新网关中的路由信息。
我们需要完成两件事情:
1. 监听 Nacos 配置变更的消息；
2. 当配置变更时，将最新的路由信息更新到网关路由表；

Nacos 官方文档介绍：<https://nacos.io/docs/latest/manual/user/java-sdk/usage/?spm=5238cd80.1f77ca18.0.0.4d31e37eyLQnOC#31-%E8%8E%B7%E5%8F%96%E9%85%8D%E7%BD%AE>

### 步骤1，在 Gateway 网关模块添加依赖
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


### 步骤2，添加 bootstrap.yaml 配置文件，配置 Nacos 服务相关信息
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

### 步骤3，清理 application.yaml 文件中 Nacos 配置信息 与 路由表信息

### 步骤4，新建 `DynamicRouteLoader.java` 动态路由加载器，编写监听动态路由、更新路由表功能

### 步骤5，在 Nacos 服务控制台，新增 `gateway-routes.json` 配置文件，配置内容如下：
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

### 步骤6，重启 Gateway 网关服务，测试接口是否可以访问。

