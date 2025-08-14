# 一、微服务配置管理 - nacos
在微服务项目中，可能存在很多个微服务，并且每个微服务可能存在多个配置文件。这些配置文件很多都是重复的配置，
比如：MySql 数据库的配置、日志的配置、Swagger接口文档的配置，如果针对每个微服务进行修改时就非常的麻烦。

微服务配置问题：
1. 微服务重复配置过多，维护成本高；
2. 业务配置（用户登录超时时间、订单超时时长、购物车商品上限等）经常变动，每次修改都要重启服务；
3. 网关请求路由配置写死，如果变更要重启网关（网关是整个微服务的入口，如果重启其它微服务将都不可用）；

为了解决这个问题，可以使用 `配置管理服务`，把微服务中重复的、通用的配置、业务有关的配置 都交给它管理，当微服务启动时从 `配置管理服务` 读取配置信息即可，实现配置共享。
`配置管理服务` 可以监听这些配置的变更，如果发生变更它会推送这些配置到对应的微服务，这些微服务不需要重启，就可以立即生效。

> `Nacos` 组件不仅具备 注册中心的功能，还具备 `配置管理服务` 功能。 官网：<https://nacos.io/>

# 二、配置共享
## 步骤1、在 Nacos 服务器上添加配置信息
案例 - 把微服务 `application.yaml` 配置中的 `jdbc`、`MybatisPlus`、`日志`、`Swagger`、`OpenFeign` 等配置信息添加到 Nacos 配置管理中。
实现 - 登录 Nacos 控制台 <http://localhost:8848/nacos/> -> `配置管理` -> `配置列表` -> 点击 “➕” 添加 -> 配置案例见图 `/img/04-配置管理-Nacos`

Nacos 中添加的共享配置中可以指定变量，如下所示：
```yaml
spring:
  datasource:
    url: jdbc:mysql://${hm.db.host:localhost}:${hm.db.port:3306}/${hm.db.database}?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true&serverTimezone=Asia/Shanghai
    driver-class-name: com.mysql.cj.jdbc.Driver
    username: ${hm.db.un:root}
    password: ${hm.db.pw:123}
```
那么在使用该共享配置的微服务中，也需添加这些变量的值，如下所示：
```application.yaml
hm:
  db:
    database: hm-cart
    host: mysql
    pw: root
```
在 Nacos 控制台依次新建 `shared-jdbc.yaml`、`shared-log.yaml`、`shared-swagger.yaml` 配置。

## 步骤2、微服务拉取 Nacos 配置管理 中的共享配置
在 Spring Cloud 项目中，项目一启动首先会读取 `bootstrap.yaml` 引导配置文件，项目启动流程见图 `/img/04-配置管理-Nacos/02-SpringCloud项目启动流程.jpg`。
把 Nacos 服务的地址信息配置在 `bootstrap.yaml`中，使得项目一启动就能获取 共享配置。

在微服务模块中，基于 `NacosConfig` 拉取 Nacos 服务器中共享配置 代替 微服务项目的本地配置，实现步骤如下所示：

### 步骤1、引入依赖 
```pom
<!-- nacos 服务注册发现 -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
<!-- nacos 配置管理  -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
<!-- 读取 bootstrap 文件信息，然后拉取 Nacos 共享配置 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bootstrap</artifactId>
</dependency>
```

### 步骤2、在微服务模块中，新建 `/resources/bootstrap.yaml` 文件

```yaml
spring:
  application:
    name: cart-service  # 当前微服务名称，每个服务应保证不一样
  profiles:
    active: dev
  cloud:
    nacos: # 配置 nacos 注册中心地址
      server-addr: localhost:8848
      config:
        file-extension: yaml  # Nacos 中文件后缀名
        shared-configs: # Nacos 中定义的 共享配置文件
          - data-id: shared-jdbc.yaml   # 共享 mybatis 配置
          - data-id: shared-log.yaml    # 共享日志配置
          - data-id: shared-swagger.yaml  # 共享接口文档配置
```

上面 `bootstrap.yaml` 中配置的 `data-id` 对应的 Nacos 服务器配置列表中的配置文件，在微服务启动时会自动加载 nacos 共享配置文件 合并到 `application.yaml` 中，
因此我们需要对 `application.yaml` 中的文件进行修改，剔除与 Nacos 服务器配置列表中的配置文件 重复的内容，添加 Nacos 服务器配置列表中的配置文件 所需的配置变量。

> 代码实现：`cart-service/src/main/resources`
