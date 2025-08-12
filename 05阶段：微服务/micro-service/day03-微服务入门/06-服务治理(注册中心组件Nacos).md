# 一、微服务远程调用时存在的问题
在微服务项目中，为了满足请求并发压力，往往把一个服务部署多个，每个服务的容器称为实例，形成负载均衡的集群，即多实例部署。
而使用 `RestTemplate` 进行远程服务调用时，需要对方服务的`url`路径（端口号和请求资源路径），将会存在如下问题：

- 服务调用者进行远程调用时，并不知道服务的地址，因为被调用的服务可能还未部署； 
- 就算知道所有服务的地址但是不知道该调用那个服务； 
- 如果服务调用者挑中一个去访问，这时服务挂掉了，或者有新的服务启动了，服务调用者无法感知服务的变更。

> 以上这些问题称为：服务治理的问题，需要用到 `注册中心` 技术。

# 二、服务治理 - 注册中心技术

- 注册中心的原理
    - `注册中心` 核心作用做 `微服务治理`，原理见图：`/img/04-服务治理-注册中心原理.jpg`

- 服务治理中的三个角色：
  1. 服务提供者：暴露服务接口，供其它服务调用； 
  2. 服务消费者：调用其它服务提供的接口； 
  3. 注册中心：记录并监控微服务各实例状态，推送服务变更信息；

## 注册中心解决的问题
* 1、消费者如何知道提供者的地址？

服务提供者 会在启动时注册自己信息到 注册中心，消费者 可以从 注册中心 订阅和拉取服务信息。


* 2、消费者如何得知服务状态变更？ 

服务提供者 通过心跳机制向 注册中心 报告自己的健康状态，当心跳异常时 注册中心 会将异常服务剔除，并通知订阅了该服务的 消费者。


* 3、当提供者有多个实例时，消费者该选择哪一个？

消费者可以通过负载均衡算法，从多个实例中选择一个


# 三、注册中心组件 - Nacos
`Nacos` 是目前国内企业中占比最多的注册中心组件之一。它是阿里巴巴的产品，目前已经加入 `SpringCloudAlibaba` 中。 官网：<https://nacos.io/>

其它常用的注册中心组件：
- `Eureka`：它集成在 `Spring Cloud Netflix` 中，一般用于 Java 应用。
- `Consul`：它集成在 Spring Cloud 中，不限制微服务语言。

注册中心组件都遵循了 Spring Cloud 中的 API 规范，因此在业务开发使用上没有太大差异。`Nacos` 国产中文文档，而且还具备“配置管理”功能，因此使用它来学习。


## 1、基于 Docker 部署 Nacos 注册中心服务

### 步骤1、准备 nacos 所需数据库
连接 `mysql` 容器的数据库执行 `/资料/nacos.sql`，Nacos 使用这些数据库表存储数据。

### 步骤2、准备 nacos 运行配置文件
找到 `/资料/nacos/custom.env` 配置文件，里面配置的是 nacos 与 mysql 连接的信息，根据自己的 `mysql 容器`的信息，进行修改：

```env
PREFER_HOST_MODE=hostname
MODE=standalone
SPRING_DATASOURCE_PLATFORM=mysql    # 用的那种数据库类型 
MYSQL_SERVICE_HOST=mysql        # 数据库容器的 IP地址 or 数据库容器名称（⚠️，使用数据库容器名称时，创建 nacos 容器时需保证它和数据库容器在同一网络）
MYSQL_SERVICE_DB_NAME=nacos     # 使用的数据库
MYSQL_SERVICE_PORT=3306
MYSQL_SERVICE_USER=root     # 数据库用户名
MYSQL_SERVICE_PASSWORD=123  # 数据库密码
MYSQL_SERVICE_DB_PARAM=characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=Asia/Shanghai
```

### 步骤3、开始执行命令部署 `nacos`
```shell
# 1、添加 nacos 镜像
$ docker pull nacos/nacos-server:v2.1.0-slim
$ docker images
REPOSITORY           TAG           IMAGE ID       CREATED       SIZE
mysql                latest        fbbd5ec94758   2 days ago    939MB
nacos/nacos-server   v2.1.0-slim   4bc73adbaa2e   3 years ago   314MB

# 2、根据 custom.env 配置文件部署 nacos
$ docker run -d \
--name nacos \
--env-file ./custom.env \
-p 8848:8848 \
-p 9848:9848 \
-p 9849:9849 \
--network hm-net \
--restart=always \
nacos/nacos-server:v2.1.0-slim

# --env-file ./custom.env，表示读取 ./custom.en 文件中的环境变量。
# --restart=always ，表示 nacos 服务开机自启。

# 查看 nacos 当前运行日志是否成功
$ docker logs -f nacos
```
访问 <http://localhost:8848/nacos/> 是否跳转到 nacos 登录页面，如果是，则部署成功。
使用账号/密码：`nacos/nacos` 登录 nacos 控制台，然后就可以在控制台管理服务了。

