# 一、分布式事务
下单业务，前端请求首先进入 `trade-service`(订单服务)，根据前端提交的订单信息创建订单并写入数据库。然后订单服务调用购物车服务和商品服务：
* `cart-service`(购物车服务) 负责清理购物车信息；
* `item-service`(商品服务) 负责扣减商品库存；

在执行下单业务过程中，如果 `item-service` 出现异常，那么清理 清理购物车 业务执行成功，造成了业务执行不一致。

## 什么是分布式事务
在分布式系统中，如果一个业务需要多个服务合作完成，而且每一个服务都有事务，多个事务必须同时成功或失败，这样的事务就是分布式事务。
其中的每个服务的事务就是一个分支事务。整个业务称为 全局事务。

> 分布式事务解决思路：解决分布式事务，各个子事务之间必须能感知到彼此的事务状态，才能保证状态一致。

# 二、分布式事务组件 - Seata
`Seata` 是 2019 年1月份 蚂蚁金服 和 阿里巴巴 共同开源的分布式事务解决方案。致力于提供高性能和简单易用的分布式事务服务，
为用户打造一站式的分布式解决方案。官网地址: <https://seata.apache.org/zh-cn/>，其中的文档、播客中提供了大量的使用说明、源码分析。

- `Seata` 事务管理中有三个重要的角色:
  - `TC(Transaction Coordinator)`- 事务协调者：维护全局和分支事务的状态，协调全局事务提交或回滚。
  - `TM (Transaction Manager)`- 事务管理器：定义全局事务的范围、开始全局事务、提交或回滚全局事务。
  - `RM (Resource Manager)`- 资源管理器：管理分支事务，与TC交谈以注册分支事务和报告分支事务的状态。

Seata 架构见图：`/img/05-分布式事务Seata/01-Seata架构.png`。

TC 是事务协调中心，它是一个独立的微服务，需要单独部署。部署 TC 服务注意事项：
1. TC 需要基于 mysql 数据库保存信息；
2. TC 需要基于 Nacos 注册中心与微服务建立连接；


# 三、基于 docker 部署 TC 服务
TC 服务 在运行过程中需要管理 全局事务、分支事务 记录它们的信息，为了保证这些信息数据安全，因此需要基于数据库进行存储。

## 步骤1，准备 TC 服务的数据库表
在 mysql 容器中执行 `/资料/seata-tc.sql` 文件，创建 `seata` 数据库 和 如下4张数据库表：
* branch_table，记录分支事务的信息
* distributed_lock，基于锁，保证线程安全
* global_table，记录全局事务的信息
* lock_table，基于锁，保证线程安全

## 步骤2，准备 Seata 运行时的配置文件
找到 `/笔记2/day05/资料/seata/` 目录，拷贝到 docker 的宿主机目录下，查看其中的 `application.yml` 文件的配置信息，作用之一是让微服务能找到 TC 服务地址，
此文件的相关信息注释查看 `/file/seata/application.yml`。

## 步骤3，通过 Docker 部署 TC 服务
```shell
$ pwd
/Users/chen/seata

$ ls
--network		-e			META-INF		application.example.yml	logback
--privileged=true	-p			README-zh.md		application.yml		logback-spring.xml
-d			-v			README.md		banner.txt		lua

$ docker run --name seata \   # 配置容器名称
-p 8099:8099 \  # 微服务与 seata TC 服务连接的端口
-p 7099:7099 \  # seata 控制台接口
-e SEATA_IP=10.0.1.65 \   # 部署到的主机IP地址，注意不能写 localhost，否则使用的是 seata 容器自己的ip地址
-v /Users/chen/seata:/seata-server/resources \    # 挂载 seata 目录，使得 自定义的 seata 配置文件生效
--privileged=true \   # 本地文件授权
--network hm-net \    # 容器的网络，需要和 nacos 容器保持一致
-d \
seataio/seata-server:1.6.0
```

> 测试是否部署成功：访问 <http://localhost:7099>，并输入账号密码：`admin`，就可以登录成功 `seata` 控制台了。


# 四、在微服务集成 Seata

## 步骤1，在所有参与事务微服务项目（`cart-service、item-service、trade-service`）中引入 Seata 依赖：

```pom
<!-- nacos 配置管理 -->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
</dependency>
<!-- 读取 bootstrap 文件 -->
<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-bootstrap</artifactId>
</dependency>

<!-- seata 客户端，分布式事务-->
<dependency>
    <groupId>com.alibaba.cloud</groupId>
    <artifactId>spring-cloud-starter-alibaba-seata</artifactId>
</dependency>
```

## 步骤2，在微服务中添加 application.yml 中添加配置，让微服务找到TC服务地址

因为 `TM、RM` 都需要和 `TC` 服务建立连接、进行数据交互，所以需要让微服务能找到 `TC 服务`的地址。
上面我们已经把 seata 服务添加到 nacos 注册中心了，因此不需要直接配置 TC服务 的地址，而是如下所示从 nacos 注册中心获取：

```application.yml
seata:
  registry: # TC服务注册中心的配置，微服务根据这些信息去注册中心获取tc服务地址
    type: nacos # seata 服务使用的注册中心类型 nacos
    nacos:
      server-addr: localhost:8848 # nacos地址
      namespace: "" # namespace，默认为空
      group: DEFAULT_GROUP # 分组，默认是DEFAULT_GROUP
      application: seata-server # seata服务名称
      username: nacos   # 连接 nacos 的用户名和密码
      password: nacos
  tx-service-group: hmall # 事务组名称 - 即集群
  service:
    vgroup-mapping: # 事务组与tc集群的映射关系
      hmall: "default"
```
由于这些配置所有分布式事务参与者都需要，所以抽取为一个共享配置，放到 nacos 配置列表中，新建 `shared-seata.yaml`配置。
然后在微服务项目中添加 `bootstrap.yaml` 文件，并添加 `-data-id: shared-seata.yaml` 配置来获取 nacos 配置列表中中的信息，。

## 步骤3，我们查看微服务添加到 seata 是否成功

重启集成 Seata 的微服务，并启动连接 Seata 微服务，输入命令查看 seata 服务运行的日志：
```shell
$ docker logs seata
```

从 seata 服务运行的日志信息里，我们可以看到 `RM register success`、`TM register success` 等相关信息，说明微服务中集成 seata 客户端成功。
上面的准备工作我们部署了 TC 服务，并在需要使用分布式事务的微服务中集成了 Seata 客户端，接下来就可以使用 seata 解决分布式事务的问题。


# 五、使用 Seata 解决分布式问题

Seata 解决分布式事务提供了不同的模式，不同模式的原理和使用场景不一样，其中使用频率最高的是 `XA、AT` 模式。

## 1、XA 模式
XA 规范 是 X/0pen 组织定义的分布式事务处理(DTP, Distributed Transaction Processing)标准。
XA 规范 描述了 全局事务TM 与 分支事务RM 之间的接口，几乎所有主流的 `关系型数据库` 都对 XA 规范 提供了支持。 

> Seata 的 XA 模式如图 `/img/05-分布式事务Seata/02-XA模式实现原理.jpg`

- XA 模式的优点： 
  - 事务的强一致性，满足事务`ACID`原则。
  - 常用数据库都支持，实现简单，并且没有代码侵入。

- XA 模式的缺点
  - 因为一阶段需要锁定数据库资源，等待二阶段结束才释放，性能较差。
  - 依赖 `关系型数据库` 实现事务。

### 1、实现 XA 模式
Seata 的 starter 已经完成了 XA模式 的自动装配，实现非常简单，步骤如下:

#### 步骤1，修改微服务(每个参与事务的微服务)的 `application.yml` 文件，开启XA模式：

```application.yml
seata:
  data-source-proxy-mode: XA
```

#### 步骤2，给发起全局事务的入口方法添加 `@GlobalTransactional` 注解

如下例子中是 `trade-service` 订单服务中提交购物车时：
```java
//    @Transactional
@GlobalTransactional
public Long createOrder(OrderFormDTO orderFormDTO) {
    //...
}
```

在 `cart-service` 购物车服务清理购物车：
```java
@Transactional
public void removeByItemIds(Collection<Long> itemIds) {
    //...
}
```

在 `item-service` 商品服务扣减商品库存：
```java
@Transactional
public void deductStock(List<OrderDetailDTO> items) {
    //...    
}
```
#### 步骤3，重启服务并测试，把购买商品库存设置为0 进行测试。
我们可以看到控制台打印：`rm handle branch rollback process:......,branchType=XA`


## 2、AT 模式
Seata 主推的是 `AT模式`，`AT模式` 同样是分阶段提交的事务模型，不过却弥补了 `XA模型` 中 资源锁定周期过长 的缺陷。

> AT 模式实现原理：`/img/05-分布式事务Seata/03-AT模式实现原理.jpg`

AT 模式 与 XA 模式对：
1. XA 模式一阶段不提交事务，锁定数据库资源，性能较差；AT 模式一阶段直接提交数据库，不锁定资源，性能较好。 
2. XA 模式依赖数据库机制实现回滚；AT 模式利用数据库快照实现数据库回滚； 
3. XA 模式强一致事务；AT 模式最终一致（也就是说数据可能会短暂不一致）；

> 如果业务对 一致性要求、性能要求 的强度，如果业务对 性能要求比较高，允许出现短暂的数据不一致性，则选择 AT 模式。目前大多数项目选择 AT 模式。

### 实现 AT 模式
#### 步骤1，首先，在 `每个关联分布式事务的微服务` 对应的数据库中添加 `/资料/seata-at.sql`数据库表

如下所示，我们需要在 `hm-trade、hm-cart、hm-item` 3个 数据库 中执行如下sql:
`undo_log` 数据库表，用于保存事务执行过程中的数据库快照信息。

```sql
CREATE TABLE IF NOT EXISTS `undo_log`
(
    `branch_id`     BIGINT       NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(128) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8mb4 COMMENT ='AT transaction mode undo table';
```

#### 步骤2，修改每个微服务的 `applicaiton.yml` 文件中事务模式模式修改为 AT 模式(默认是AT模式)
```application.yml
seata:
  data-source-proxy-mode: AT
```

#### 步骤3，重启与分布式事务管理的微服务（即`trade-service、cart-service、item-service`）
测试提交订单，在 `cart-service` 服务会输出：`branchType=AT ......Rollbacked result: PhaseTwo_Rollbacked` 信息，则验证成功！