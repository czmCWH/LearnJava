# 使用 Nacos 注册中心
部署好了 Nacos 注册中心就可以做服务治理了，服务治理包含2个部分：服务注册、服务发现。

1. 服务注册，是指所有的微服务在启动时都应提交自己的服务信息到 Nacos 注册中心；
2. 服务发现，服务调用者从 注册中心 拉取自己所需的服务信息；

## 1、服务注册
服务注册并不需要写代码，服务注册步骤如下：

### 步骤1、引入 `nacos discovery` 依赖

```pom
<!-- nacos 服务注册发现 -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
</dependency>
```

### 步骤2、配置 Nacos 地址

```yaml
spring:
  application:
    name: item-service  # 微服务名称
  cloud:
    nacos:
      server-addr: localhost:8848   # 配置 Nacos 服务器的地址
```

3、启动 java 应用后，它会自动完成 Nacos 服务注册。我们可以登录 Nacos 服务后台 <http://localhost:8848/nacos/>，查看我们注册的服务信息。
使用 `day03-微服务入门/img/05-IDEA 上实现多实例部署`，进行多服务本地部署，这样 Nacos 后台也可以看到详细信息，如图 `day03-微服务入门/img/06-Nacos后台查看服务.jpg`。

## 2、服务发现
消费者 需要连接 nacos 以拉取和订阅服务，因此服务发现的前两步与服务注册是一样，后面再加上服务调用即可:

前2步和《服务注册》通用：
* 步骤1、引入 `nacos discovery` 依赖
* 步骤2、配置 Nacos 地址

### 步骤3、服务发现
```java

// DiscoveryClient 是 SpringCloud 接口，现集成了 Nacon 组件后，它由 Nacon 组件去实现，用于 注册中心服务列表
private final DiscoveryClient discoveryClient;

public void getData() {
    // 2.1、根据 服务名称（item-service） 获取服务的实例对象
    List<ServiceInstance> instances = discoveryClient.getInstances("item-service");
    if (CollUtils.isEmpty(instances)) {
        return;
    }
    // 2.2、手写负载均衡，从服务列表中挑选一个实例
    ServiceInstance instance = instances.get(RandomUtil.randomInt(0, instances.size()));

    // instance.getUri();       主机名和端口号 合起来叫 Uri
    // ⚠️，服务调用者不需要写死服务提供者的端口和IP信息，而是每次通过 服务名称 动态获取它当前的 实例列表，还可以通过负载均衡从中获取一个可用的实例。

    // 2.3、利用 RestTemplate 发起 http 请求，得到 http 相应
    ResponseEntity<List<ItemDTO>>  response = restTemplate.exchange(
            instance.getUri() + "/items?ids={ids}",
            HttpMethod.GET,
            null,
            new ParameterizedTypeReference<List<ItemDTO>>() {
            },
            Map.of("ids", CollUtil.join(itemIds, ","))
    );

    // 2.2、解析响应
    if (!response.getStatusCode().is2xxSuccessful()) {
        // 查询失败
        return;
    }
    List<ItemDTO> items = response.getBody();
}
```