# 一、单体架构
`单体架构`：将业务的所有功能集中在一个项目中开发，打成一个包部署。

- 优点：
  1. 架构简单； 
  2. 部署成本低； 
  
- 缺点：
  1. 团队协作成本高（工程复杂、开发人员多时，很容易产生代码冲突）；
  2. 系统发布效率低（随着业务功能增多，打包所需时间长，部署时间长）； 
  3. 系统可用性差
        （系统各功能之间会产生影响，比如：有些模块并发量高，如登录/查询；有些模块并发不高但很重要，如订单；
        当某些模块并发高时耗尽 Tomcat 服务器资源，导致其它模块访问时卡顿或者卡死，造成很大的损失。）；

- 总结：
  - 单体架构适合开发功能相对简单，规模较小的项目，

# 二、微服务架构

`微服务架构`，是服务化思想指导下的一套最佳实践架构方案。适用于大型复杂的项目。

`服务化`，就是把单体架构中的功能模块拆分为多个独立的项目。每个功能模块都是一个小的服务，所以叫微服务。

- 单体项目目拆分为微服务架构要求如下：
  1. 粒度小（按业务功能模块拆分，每一个项目负责完整的功能，单一职责）；
  2. 团队自治（每个拆分的项目有一个单独完整团队开发维护）；
  3. 服务自治（每个拆分的项目分别编译打包部署，每个服务都有自己独立的数据库）；

大型复杂的项目采用微服务架构后，可能会有成百上千个服务，这导致项目部署运维复杂度，不同服务之间需要跨模块开发，这些问题需要使用微服务技术栈来解决。

# 三、SpringCloud

`SpringCloud` 是目前国内使用最广泛的微服务框架。官网地址: <https://spring.io/projects/spring-cloud>

`SpringCloud` 集成了各种微服务功能组件，并基于 `SpringBoot` 实现了这些组件的自动装配，从而提供了良好的开箱即用体验

## 1、SpringCloud 与 SpringBoot 的版本兼容关系

`SpringCloud` 基于 `SpringBoot` 实现了微服务组件的自动装配，从而提供了良好的开箱即用体验。
但对于 SpringBoot 的版本也有要求：见图 `/img/02-SpringCloud/02-SpringCloud版本.jpg`

> `SpringBoot 3.0.0` 需要 `JDK17+`。

* SpringCloud 中包含许多组件，集成这些组件时是否需要指定版本呢？

我们可以在项目中导入 `spring-cloud-dependencies` pom 配置信息，`spring-cloud-dependencies` 中已经指定了它内部集成组件的版本，
所以使用 SpringCloud 中的任意组件时不需要指定其版本。
```xml
<dependencyManagement>
  <dependencies>
    <!--spring cloud-->
    <dependency>
      <groupId>org.springframework.cloud</groupId>
      <artifactId>spring-cloud-dependencies</artifactId>
      <version>${spring-cloud.version}</version>
      <type>pom</type>
      <scope>import</scope>
    </dependency>
  </dependencies>
</dependencyManagement>
```

## 2、SpringCloud 技术栈

SpringCloud 是一个技术栈，集成了其它公司开发的微服务组件，这些组件用于解决微服务开发中的各种问题。那么为什么其它公司开发的产品需要被 Spring 集成？
1、Spring 是全球 java 开发使用最广泛的框架；
2、SpringBoot 自动装配和依赖管理，使得构建项目非常的简单方便；
各种微服务组件 通过 Spring 框架整合，使得它们可以开箱即用。

SpringCloud 是微服务组件标准的制定者，其它微服务组件必须来遵守它，比如：Spring Cloud Alibaba。这样其它微服务组件使用上差别不是很大，只是依赖不一样。

1～6 可以解决企业微服务开发中绝大多数问题。

1. 服务拆分 
2. 远程调用 --- OpenFeign：基于 Nacos 使用 
3. 服务治理 --- 服务注册 + 服务发现 ---> 注册中心组件（Nacos)
4. 请求路由 --- Spring Cloud Gateway：路由转发、身份校验 
5. 身份认证 --- Gateway 过滤器 -> SpringMVC 拦截器 -> OpenFeign 拦截器
6. 配置管理 --- Nacos 共享配置、配置热更新
7. 服务保护 --- Sentinel
8. 分布式事务 --- Seata 基于 mysql+Nacos
9. 异步通信 
10. 消息可靠性 
11. 延迟消息 
12. 分布式搜索 
13. 倒排索引 
14. 数据聚合